package myPharm.myPharm.domain.dto.medbox;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MedboxReqDto {

    private String medicineName;

    private Date startDate;

    private Date endDate;


}
