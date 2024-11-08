package myPharm.myPharm.domain.dto.prohibit;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProhibitResDto {
    private List<String> prohibitedIngredients;
}
