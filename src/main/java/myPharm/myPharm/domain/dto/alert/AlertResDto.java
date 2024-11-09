package myPharm.myPharm.domain.dto.alert;

import lombok.AllArgsConstructor;
import lombok.Data;
import myPharm.myPharm.domain.entity.AlertEntity;

import java.util.List;

@Data
@AllArgsConstructor
public class AlertResDto {

    private String medicineName;
    private String typeName;
    private String contents;
}
