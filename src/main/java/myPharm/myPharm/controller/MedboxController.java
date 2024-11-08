package myPharm.myPharm.controller;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.medbox.MedboxReqDto;
import myPharm.myPharm.domain.dto.medbox.MedboxResDto;
import myPharm.myPharm.domain.dto.medicine.MedicineReqDto;
import myPharm.myPharm.domain.entity.MedicineEntity;
import myPharm.myPharm.service.MedboxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MedboxController {

    private final MedboxService medboxService;


    //약이름, 시작날짜, 끝날짜 받아서 저장
    @PostMapping("/medbox/save") //#1
    public void saveMedbox(@RequestBody MedboxReqDto medboxReqDto, Authentication authentication) {
        if (authentication == null) {
            throw new RuntimeException("Authentication is null");
        }
        System.out.println("Authentication name: " + authentication.getName());
        medboxService.saveMedbox(medboxReqDto, authentication);
    }

    //약이름으로 검색
    @GetMapping("/medicine/search") //#2
    public List<MedicineReqDto> searchMedicine(@RequestParam String medicineName) {
        return medboxService.searchMedicine(medicineName);
    }

    //내가 복용중인약  medbox에서 불러오기
    @GetMapping("/medbox/get") //#5
    public List<String> loadMedbox(Authentication authentication) {
        return medboxService.getMedbox(authentication);
    }

    //약 삭제
    @DeleteMapping("/medbox/delete") //#3
    public ResponseEntity<Void> deleteMyPharm(Authentication authentication, @RequestParam String medicineName) {
        try {
            medboxService.deleteMedicine(authentication, medicineName);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}








