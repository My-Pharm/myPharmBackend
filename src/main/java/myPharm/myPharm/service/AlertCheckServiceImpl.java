package myPharm.myPharm.service;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.entity.AlertEntity;
import myPharm.myPharm.domain.entity.IngredientEntity;
import myPharm.myPharm.domain.entity.MedboxEntity;
import myPharm.myPharm.repository.AlertRepository;
import myPharm.myPharm.repository.RelationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlertCheckServiceImpl {
    private final AlertRepository alertRepository;
    private final RelationRepository relationRepository;
    public List<String[]> alertCheck(List<MedboxEntity> medboxEntities){

        //List<String[]> alertList = new ArrayList<>();//리턴값
        List<String[]> alertList = new ArrayList<>();
        for(MedboxEntity medbox: medboxEntities){
            List<IngredientEntity> IngreList = relationRepository.findIngredientsByMedicineName(medbox.getMedicine().getMedicineName());

            for(IngredientEntity ingre: IngreList){
                List<AlertEntity> alertEntityList = alertRepository.findByIngredientName(ingre.getIngredientName());
                for(AlertEntity alertEntity: alertEntityList){
                    alertList.add(new String[]{alertEntity.getTypeName(),medbox.getMedicine().getMedicineName(),alertEntity.getContent()});
                }
//                alertList.addAll(alertRepository.findByIngredientName(ingre.getIngredientName()));
            }

        }

        return alertList;

    }
}
