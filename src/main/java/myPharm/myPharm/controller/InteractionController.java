package myPharm.myPharm.controller;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.entity.AlertEntity;
import myPharm.myPharm.domain.entity.MedboxEntity;
import myPharm.myPharm.domain.entity.MedicineEntity;
import myPharm.myPharm.domain.entity.UserEntity;
import myPharm.myPharm.repository.MedboxRepository;
import myPharm.myPharm.repository.MedicineRepository;
import myPharm.myPharm.repository.UserRepository;
import myPharm.myPharm.service.InterationCheckServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interaction")
public class InteractionController {

    private final MedboxRepository medboxRepository;
    private final UserRepository userRepository;
    private final InterationCheckServiceImpl interationCheckService;

    @GetMapping("/check-all")//#4
    public List<String[]> checkAll(Authentication authentication){

        Long outhId = Long.valueOf(authentication.getName());
        System.out.println(outhId);
        UserEntity user = userRepository.findByOuthId(outhId);
        System.out.println(user);
        List<MedboxEntity> myMedBoxList = medboxRepository.findByUser(user);
        System.out.println(myMedBoxList);
        List<String[]> alertList = interationCheckService.checkInteraction(myMedBoxList);
        System.out.println(alertList);

        return alertList;
    }

    @GetMapping("/check-once")//#6
    public List<String[]> checkOnce(List<MedboxEntity> addlist,Authentication authentication){

        Long outhId = Long.valueOf(authentication.getName());
        UserEntity user = userRepository.findByOuthId(outhId);

        //dto가져옴
        List<MedboxEntity> myMedBoxList = medboxRepository.findByUser(user);

        myMedBoxList.addAll(addlist);

        List<String[]> alertList = interationCheckService.checkInteraction(myMedBoxList);


        return alertList;
    }

}
