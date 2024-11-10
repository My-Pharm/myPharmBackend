package myPharm.myPharm.repository;

import myPharm.myPharm.domain.entity.ProhibitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProhibitRepository extends JpaRepository<ProhibitEntity, Long> {
    List<ProhibitEntity> findByIngredient1_IngredientNameOrIngredient2_IngredientName(String ingredientName1, String ingredientName2);
}