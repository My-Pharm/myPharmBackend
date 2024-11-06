package myPharm.myPharm.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import myPharm.myPharm.domain.entity.IngredientEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProhibitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_name1")
    private IngredientEntity ingredient1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_name2")
    private IngredientEntity ingredient2;

}
