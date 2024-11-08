package myPharm.myPharm.service;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.entity.IngredientEntity;
import myPharm.myPharm.domain.entity.MedboxEntity;
import myPharm.myPharm.repository.ProhibitRepository;
import myPharm.myPharm.repository.RelationRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InterationCheckServiceImpl implements InteractionCheckSercive{
    private final RelationRepository relationRepository;
    private final ProhibitRepository prohibitRepository;
    private final DataService dataService;

    public List<String[]> checkInteraction(List<MedboxEntity> medboxEntities){

        List<String[]> alertList = new ArrayList<>();//리턴값
        Map<String,String> medIngre = new HashMap();//성분 /약이름// ingredient테이블 medicine테이블 조인해서 가져옴 약기준으로 성분가져와서 반대로넣기
        Map<String,String> prohibitRelation = new HashMap();//안되는성분 /약성분//릴레이션테이블에서 가져옴



        for(MedboxEntity medbox:medboxEntities){

            List<IngredientEntity> IngreList = relationRepository.findIngredientsByMedicineName(medbox.getMedicine().getMedicineName());
            for(IngredientEntity ingredient:IngreList) {
                medIngre.put(ingredient.getIngredientName(), medbox.getMedicine().getMedicineName());
                //안되는거 뽑아옴
                List<String> prohibitIngredient = dataService.searchprohibit(ingredient.getIngredientName()).getProhibitedIngredients();
                //List<IngredientEntity> prohibitIngredient = prohibitRepository.findByIngredient1_IngredientNameOrIngredient2_IngredientName();//변경예정    //in기준
                for(String ingre :prohibitIngredient){
                    prohibitRelation.put(ingre,ingredient.getIngredientName());//안되는성분 //내약성분
                }
            }


        }
        for(String key:medIngre.keySet()){
            if(prohibitRelation.containsKey(key)){

                alertList.add(new String[]{medIngre.get(prohibitRelation.get(key)), medIngre.get(key)});
            }
        }


        return alertList;

    }
}
