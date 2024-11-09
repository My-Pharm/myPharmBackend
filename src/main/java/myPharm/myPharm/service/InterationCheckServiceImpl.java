package myPharm.myPharm.service;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.alert.AlertResDto;
import myPharm.myPharm.domain.entity.AlertEntity;
import myPharm.myPharm.domain.entity.IngredientEntity;
import myPharm.myPharm.domain.entity.MedboxEntity;
import myPharm.myPharm.domain.entity.ProhibitEntity;
import myPharm.myPharm.repository.ProhibitRepository;
import myPharm.myPharm.repository.RelationRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterationCheckServiceImpl implements InteractionCheckSercive{
    private final RelationRepository relationRepository;
    private final DataService dataService;
    private final ProhibitRepository prohibitRepository;

    public List<AlertResDto> checkInteraction(List<MedboxEntity> medboxEntities) throws ParseException {

        List<AlertResDto> alertList = new ArrayList<>();//리턴값

        Map<String,String> medIngre = new HashMap();//성분 /약이름// ingredient테이블 medicine테이블 조인해서 가져옴 약기준으로 성분가져와서 반대로넣기

        Map<String,String> prohibitRelation = new HashMap();//안되는성분 /약성분//릴레이션테이블에서 가져옴

        Set<String> amount = new HashSet<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String formattedDate = sdf.format(new Date());

        // 한국 시간으로 변환된 날짜 문자열을 LocalDate로 변환
        LocalDate curDate = sdf.parse(formattedDate).toInstant()
                .atZone(ZoneId.of("Asia/Seoul"))
                .toLocalDate();

        medboxEntities = medboxEntities.stream()
                .filter(medbox -> {
                    LocalDate endDate = medbox.getEndDate().toInstant()
                            .atZone(ZoneId.of("Asia/Seoul"))
                            .toLocalDate();
                    return endDate.isEqual(curDate) || endDate.isAfter(curDate);
                })
                .collect(Collectors.toList());



        for(MedboxEntity medbox:medboxEntities){

            List<IngredientEntity> IngreList = relationRepository.findIngredientsByMedicineName(medbox.getMedicine().getMedicineName());


            for(IngredientEntity ingredient:IngreList) {

                if(medIngre.containsKey(ingredient.getIngredientName())){
                    amount.add(medbox.getMedicine().getMedicineName());
                }
                medIngre.put(ingredient.getIngredientName(), medbox.getMedicine().getMedicineName());

                //안되는거 뽑아옴
                List<String> prohibitIngredient = dataService.searchprohibit(ingredient.getIngredientName()).getProhibitedIngredients();


                for(String ingre :prohibitIngredient){

                    prohibitRelation.put(ingre,ingredient.getIngredientName());//안되는성분 //내약성분

                }
            }

        }
        for(String key:medIngre.keySet()){
            String content = "";
            boolean flag=false;
            for(String key2: prohibitRelation.keySet()){
                if(key.contains(key2)){
                    flag=true;
                    content+=medIngre.get(prohibitRelation.get(key2))+", ";
                }
            }
            if(flag) {
                content = content.substring(0, content.length() - 2);

                content+=" 과(와) 함께 드실 수 없습니다.";
                alertList.add(new AlertResDto(medIngre.get(key), "병용금지", content));
            }
        }
        for(String amo: amount){
            alertList.add(new AlertResDto(amo, "용량주의", amo+"와 같은 성분의 약을 함께 드시고 계십니다."));
        }


        return alertList;

    }
}
