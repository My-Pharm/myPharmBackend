package myPharm.myPharm.controller;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.entity.AlertEntity;
import myPharm.myPharm.domain.entity.MedboxEntity;
import myPharm.myPharm.domain.entity.MedicineEntity;
import myPharm.myPharm.repository.MedboxRepository;
import myPharm.myPharm.repository.MedicineRepository;
import myPharm.myPharm.service.InterationCheckServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interaction")
public class InteractionController {

    private final MedboxRepository medboxRepository;
    private final InterationCheckServiceImpl interationCheckService;
    @GetMapping("/check-all")//#4
    public List<String[]> checkAll(){

        List<MedboxEntity> list2 = medboxRepository.findAll();
        List<String[]> list = interationCheckService.checkInteraction(list2);

        return list;
    }

    @GetMapping("/check-once")//#6
    public List<String[]> checkOnce(List<MedboxEntity> addlist){


        //dto가져옴
        List<MedboxEntity> list2 = medboxRepository.findAll();

        for(MedboxEntity med: addlist){
            list2.add(med);
        }
        List<String[]> list = interationCheckService.checkInteraction(list2);


        return list;
    }

}
