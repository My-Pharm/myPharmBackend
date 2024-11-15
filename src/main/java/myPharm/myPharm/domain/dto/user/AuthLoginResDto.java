package myPharm.myPharm.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthLoginResDto {

    private String accessToken;

    //Patch 메서드에서는 입력한 RefreshToken 그대로 반환
    private String refreshToken;
}
