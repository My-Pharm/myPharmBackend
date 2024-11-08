package myPharm.myPharm.repository;

import myPharm.myPharm.domain.entity.IngredientEntity;
import myPharm.myPharm.domain.entity.RelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<RelationEntity, Long> {

    @Query("SELECT r.ingredient FROM RelationEntity r WHERE r.medicine.medicineName = :medicineName")
    List<IngredientEntity> findIngredientsByMedicineName(@Param("medicineName") String medicineName);
}
