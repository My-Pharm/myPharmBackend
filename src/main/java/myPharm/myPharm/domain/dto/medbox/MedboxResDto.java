package myPharm.myPharm.domain.dto.medbox;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MedboxResDto {
    private Date startDate;
    private Date endDate;
    private String medicineName;
}
