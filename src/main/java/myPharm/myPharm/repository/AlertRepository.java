package myPharm.myPharm.repository;

import myPharm.myPharm.domain.entity.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<AlertEntity, Long> {

    @Query("SELECT a FROM AlertEntity a WHERE a.ingredient.ingredientName = :ingredientName")
    List<AlertEntity> findByIngredientName(@Param("ingredientName") String ingredientName);
}
