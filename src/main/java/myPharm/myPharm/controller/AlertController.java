package myPharm.myPharm.controller;


import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.entity.AlertEntity;
import myPharm.myPharm.domain.entity.MedboxEntity;
import myPharm.myPharm.domain.entity.UserEntity;
import myPharm.myPharm.repository.MedboxRepository;
import myPharm.myPharm.repository.UserRepository;
import myPharm.myPharm.service.AlertCheckServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlertController {
    private final UserRepository userRepository;
    private final MedboxRepository medboxRepository;
    private final AlertCheckServiceImpl alertCheckService;

    @GetMapping("/alert-check")
    public List<String[]> alertListCheckAll(Authentication authentication){

        Long outhId = Long.valueOf(authentication.getName());
        UserEntity user = userRepository.findByOuthId(outhId);

        List<MedboxEntity> myMedBoxList = medboxRepository.findByUser(user);
        List<String[]> alertList = alertCheckService.alertCheck(myMedBoxList);


        return alertList;
    }
}
