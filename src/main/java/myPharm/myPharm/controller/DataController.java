package myPharm.myPharm.controller;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.alert.AlertResDto;
import myPharm.myPharm.domain.dto.ingredient.IngredientResDto;
import myPharm.myPharm.domain.dto.medicine.MedicineReqDto;
import myPharm.myPharm.domain.dto.prohibit.ProhibitResDto;
import myPharm.myPharm.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    // 약 이름으로 성분 검색
    @GetMapping("/medicine/search")
    public IngredientResDto searchMedicine(@RequestParam String medicineName) {
        return dataService.searchingredient(medicineName);
    }

    // 성분이름으로 유의사항 불러오기
    @GetMapping("/alert/search")
    public AlertResDto searchalert(@RequestParam String ingredientName) {
        return dataService.searchAlert(ingredientName);
    }

    //성분이름으로 같이 먹으면안되는 성분이름가져오기
    @GetMapping("/prohibit/search")
    public AlertResDto searchprohibit(@RequestParam String ingredientName) {
        return dataService.searchprohibit(ingredientName);
    }

}
