package myPharm.myPharm.domain.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import myPharm.myPharm.domain.entity.IngredientEntity;

import java.util.List;

@Data
@AllArgsConstructor
public class IngredientResDto {
    private List<IngredientEntity> ingredients;
}
