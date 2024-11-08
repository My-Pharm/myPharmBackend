package myPharm.myPharm.domain.dto.alert;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AlertResDto {
    private List<String> contents;
}
