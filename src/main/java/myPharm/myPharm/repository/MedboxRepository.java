package myPharm.myPharm.repository;

import myPharm.myPharm.domain.entity.MedboxEntity;
import myPharm.myPharm.domain.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedboxRepository extends JpaRepository<MedboxEntity,Long> {
}
