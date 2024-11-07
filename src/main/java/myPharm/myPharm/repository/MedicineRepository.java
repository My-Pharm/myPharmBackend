package myPharm.myPharm.repository;

import myPharm.myPharm.domain.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Long> {
    Optional<MedicineEntity> findByName(String name); // findByName 메서드 추가
}