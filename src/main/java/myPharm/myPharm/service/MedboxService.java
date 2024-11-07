package myPharm.myPharm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.medbox.MedboxReqDto;
import myPharm.myPharm.domain.dto.medbox.MedboxResDto;
import myPharm.myPharm.domain.dto.medicine.MedicineReqDto;
import myPharm.myPharm.domain.entity.MedboxEntity;
import myPharm.myPharm.domain.entity.MedicineEntity;
import myPharm.myPharm.domain.entity.UserEntity;
import myPharm.myPharm.repository.MedboxRepository;
import myPharm.myPharm.repository.MedicineRepository;
import myPharm.myPharm.repository.UserRepository;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedboxService {

    private final MedboxRepository medboxRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;

    //약 검색
    @Transactional(readOnly = true)
    public List<MedicineReqDto> searchMedicine(String medicineName) {
        List<MedicineEntity> allMedicine = medicineRepository.findByMedicineNameStartingWith(medicineName);

        return allMedicine.stream()
                .map(medicine -> new MedicineReqDto(medicine.getMedicineName()))
                .collect(Collectors.toList());
    }

    //medbox에 약 추가
    @Transactional
    public void saveMedbox(MedboxReqDto medboxReqDto, Authentication authentication) {
        Long outhId = Long.valueOf(authentication.getName());
        UserEntity user = userRepository.findByOuthId(outhId);

        MedicineEntity medicine = medicineRepository.findByMedicineName(medboxReqDto.getMedicineName());
        if (medicine == null) {
            throw new IllegalArgumentException("일치하는 약이 없습니다" + medboxReqDto.getMedicineName());
        }

        MedboxEntity medbox = MedboxEntity.builder()
                .user(user)
                .medicine(medicine)
                .startDate(medboxReqDto.getStartDate())
                .endDate(medboxReqDto.getEndDate())
                .build();

        medboxRepository.save(medbox);
    }

    //medbox에서 내가 지금 복용중인 약 불러오기
    @Transactional(readOnly = true)
    public List<String> getMedbox(Authentication authentication) {
        Long outhId = Long.valueOf(authentication.getName());
        UserEntity user = userRepository.findByOuthId(outhId);
        List<MedboxEntity> medboxEntities = medboxRepository.findByUser(user);

        return medboxEntities.stream()
                .map(medbox -> medbox.getMedicine().getMedicineName())
                .collect(Collectors.toList());
    }

    //medbox에서 약 삭제
    @Transactional
    public void deleteMedicine(Authentication authentication, String medicineName) {
        Long outhId = Long.valueOf(authentication.getName());
        UserEntity user = userRepository.findByOuthId(outhId);

        MedboxEntity medboxEntity = medboxRepository.findByUserAndMedicine_MedicineName(user, medicineName)
                .orElseThrow(() -> new EntityNotFoundException("유저는 해당약 복용중 아님"));

        medboxRepository.delete(medboxEntity);
    }



}
