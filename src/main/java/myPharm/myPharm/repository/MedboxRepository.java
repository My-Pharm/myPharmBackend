package myPharm.myPharm.repository;

import myPharm.myPharm.domain.entity.MedboxEntity;
import myPharm.myPharm.domain.entity.MedicineEntity;
import myPharm.myPharm.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedboxRepository extends JpaRepository<MedboxEntity,Long> {
    List<MedboxEntity> findByUser(UserEntity user);
    Optional<MedboxEntity> findByUserAndMedicine_MedicineName(UserEntity user, String medicineName);
}
