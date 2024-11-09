package myPharm.myPharm.service;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.alert.AlertResDto;
import myPharm.myPharm.domain.dto.medbox.MedboxResDto;
import myPharm.myPharm.domain.entity.AlertEntity;
import myPharm.myPharm.domain.entity.IngredientEntity;
import myPharm.myPharm.domain.entity.MedboxEntity;
import myPharm.myPharm.repository.AlertRepository;
import myPharm.myPharm.repository.RelationRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertCheckServiceImpl {
    private final AlertRepository alertRepository;
    private final RelationRepository relationRepository;
    public List<AlertResDto> alertCheck(List<MedboxEntity> medboxEntities) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String formattedDate = sdf.format(new Date());

        // 한국 시간으로 변환된 Date 객체
        Date curDate = sdf.parse(formattedDate);
        medboxEntities = medboxEntities.stream()
                .filter(medbox -> medbox.getEndDate().after(curDate) || medbox.getEndDate().equals(curDate))
                .collect(Collectors.toList());

        //List<String[]> alertList = new ArrayList<>();//리턴값
        List<AlertResDto> alertList = new ArrayList<>();
        for(MedboxEntity medbox: medboxEntities){
            List<IngredientEntity> IngreList = relationRepository.findIngredientsByMedicineName(medbox.getMedicine().getMedicineName());

            for(IngredientEntity ingre: IngreList){
                List<AlertEntity> alertEntityList = alertRepository.findByIngredientName(ingre.getIngredientName());
                for(AlertEntity alertEntity: alertEntityList){
                    alertList.add(new AlertResDto(medbox.getMedicine().getMedicineName(),alertEntity.getTypeName(),alertEntity.getContent()));
                    }
            }

        }

        return alertList;

    }
}
