package myPharm.myPharm.service;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.alert.AlertResDto;
import myPharm.myPharm.domain.dto.ingredient.IngredientResDto;
import myPharm.myPharm.domain.dto.prohibit.ProhibitResDto;

import myPharm.myPharm.domain.entity.AlertEntity;
import myPharm.myPharm.domain.entity.IngredientEntity;
import myPharm.myPharm.domain.entity.MedicineEntity;
import myPharm.myPharm.domain.entity.ProhibitEntity;
import myPharm.myPharm.repository.AlertRepository;
import myPharm.myPharm.repository.MedicineRepository;
import myPharm.myPharm.repository.ProhibitRepository;
import myPharm.myPharm.repository.RelationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DataService {

    private final RelationRepository relationRepository;
    private final AlertRepository alertRepository;
    private final ProhibitRepository prohibitRepository;

    public IngredientResDto searchingredient(String medicineName) {
        List<IngredientEntity> ingredients = relationRepository.findIngredientsByMedicineName(medicineName);

        return new IngredientResDto(ingredients);
    }

    public AlertResDto searchAlert(String ingredientName) {

        List<AlertEntity> alertEntities = alertRepository.findByIngredientName(ingredientName);

        List<String> contents = alertEntities.stream()
                .map(AlertEntity::getContent)
                .collect(Collectors.toList());

        return new AlertResDto(contents);
    }

    public ProhibitResDto searchprohibit(String ingredientName) {
        List<ProhibitEntity> prohibitedEntities = prohibitRepository.findByIngredient1_IngredientNameOrIngredient2_IngredientName(ingredientName, ingredientName);

        List<String> prohibitedIngredientNames = prohibitedEntities.stream()
                .flatMap(entity -> {
                    if (entity.getIngredient1().getIngredientName().equals(ingredientName)) {
                        return Stream.of(entity.getIngredient2().getIngredientName());
                    } else {
                        return Stream.of(entity.getIngredient1().getIngredientName());
                    }
                })
                .collect(Collectors.toList());

        return new ProhibitResDto(prohibitedIngredientNames);
    }


}

