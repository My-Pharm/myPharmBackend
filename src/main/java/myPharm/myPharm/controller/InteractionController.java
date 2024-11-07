package myPharm.myPharm.controller;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.entity.AlertEntity;
import myPharm.myPharm.domain.entity.MedicineEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interaction")//#4
public class InteractionController {
    @GetMapping("/check-all")//#4
    public List<AlertEntity> checkAll(){

        List<AlertEntity> list = new ArrayList<>();
        return list;
    }

    @GetMapping("/check-once")//#6
    public List<AlertEntity> checkOnce(List<MedicineEntity> addlist){

        List<AlertEntity> list = new ArrayList<>();
        return list;
    }

}
