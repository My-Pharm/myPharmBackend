package myPharm.myPharm.service;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.alert.AlertResDto;
import myPharm.myPharm.domain.dto.ingredient.IngredientResDto;
import myPharm.myPharm.domain.entity.AlertEntity;
import myPharm.myPharm.domain.entity.IngredientEntity;
import myPharm.myPharm.domain.entity.ProhibitEntity;
import myPharm.myPharm.repository.AlertRepository;
import myPharm.myPharm.repository.ProhibitRepository;
import myPharm.myPharm.repository.RelationRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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

    public AlertResDto searchprohibit(String ingredientName) {

        List<ProhibitEntity> prohibitedEntities = prohibitRepository.findByIngredient1_IngredientNameOrIngredient2_IngredientName(ingredientName, ingredientName);

        Set<String> prohibitedIngredientNames = new HashSet<>();

        for (ProhibitEntity entity : prohibitedEntities) {
            if (entity.getIngredient1().getIngredientName().equals(ingredientName)) {
                prohibitedIngredientNames.add(entity.getIngredient2().getIngredientName());
            } else {
                prohibitedIngredientNames.add(entity.getIngredient1().getIngredientName());
            }
        }

        return new AlertResDto(new ArrayList<>(prohibitedIngredientNames));
    }

}

