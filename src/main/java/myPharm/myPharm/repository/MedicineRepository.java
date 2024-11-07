package myPharm.myPharm.repository;

import myPharm.myPharm.domain.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicineRepository extends JpaRepository<MedicineEntity, String> {
    List<MedicineEntity> findByMedicineNameStartingWith(String medicineName);
    MedicineEntity findByMedicineName(String medicineName);
}
