package myPharm.myPharm.controller;


import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.medbox.MedboxReqDto;
import myPharm.myPharm.domain.entity.MedicineEntity;
import myPharm.myPharm.service.MedboxService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medbox")
public class MedboxController {
    private final MedboxService medboxService;

    @PostMapping("/save-my-pharm")//#1
    public String saveMyPharm(@RequestBody MedboxReqDto medboxReqDto){

        return "약냉장고 약추가 성공";
    }
    @GetMapping("/search-medicine")//#2
    public List<MedicineEntity> searchMedicine(){

        List<MedicineEntity> list = new ArrayList<>();
        return list;
    }
    @DeleteMapping("/delete-my-pharm")//#3 내 약 삭제
    public String deleteMyPharm(@RequestBody String medName){


        return "삭제완료";
    }
    @GetMapping("/load-my-pharm")//#5
    public List<MedicineEntity> loadMyPharm(Long id){
        List<MedicineEntity> list = new ArrayList<>();
        return list;
    }





}
