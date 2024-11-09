package myPharm.myPharm.controller;

import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.alert.AlertResDto;
import myPharm.myPharm.domain.dto.medbox.MedboxReqDto;
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

import java.text.ParseException;
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
    public List<AlertResDto> checkAll(Authentication authentication) throws ParseException {

        Long outhId = Long.valueOf(authentication.getName());

        UserEntity user = userRepository.findByOuthId(outhId);

        List<MedboxEntity> myMedBoxList = medboxRepository.findByUser(user);

        List<AlertResDto> alertList = interationCheckService.checkInteraction(myMedBoxList);


        return alertList;
    }

    @GetMapping("/check-once")//#6
    public List<AlertResDto> checkOnce(@RequestBody List<MedboxReqDto> addlist, Authentication authentication) throws ParseException {

        Long outhId = Long.valueOf(authentication.getName());
        UserEntity user = userRepository.findByOuthId(outhId);

        //dto가져옴
        List<MedboxEntity> myMedBoxList = medboxRepository.findByUser(user);

        for(MedboxReqDto dto: addlist){
            myMedBoxList.add(new MedboxEntity(0L,null,dto.getStartDate(),dto.getEndDate(),new MedicineEntity(dto.getMedicineName())));
        }

        List<AlertResDto> alertList = interationCheckService.checkInteraction(myMedBoxList);


        return alertList;
    }

}
