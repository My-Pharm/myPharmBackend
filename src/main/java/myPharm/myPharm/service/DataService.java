package myPharm.myPharm.service;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.ingredient.IngredientResDto;
import myPharm.myPharm.domain.dto.medicine.MedicineReqDto;
import myPharm.myPharm.domain.entity.IngredientEntity;
import myPharm.myPharm.domain.entity.MedicineEntity;
import myPharm.myPharm.repository.MedicineRepository;
import myPharm.myPharm.repository.RelationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataService {

    private final RelationRepository relationRepository;


    public IngredientResDto searchingredient(String medicineName) {
        List<IngredientEntity> ingredients = relationRepository.findIngredientsByMedicineName(medicineName);

        return new IngredientResDto(ingredients);
    }
}

