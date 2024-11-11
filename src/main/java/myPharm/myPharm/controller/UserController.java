package myPharm.myPharm.controller;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import myPharm.myPharm.domain.dto.user.AuthLoginResDto;
import myPharm.myPharm.domain.entity.UserEntity;
import myPharm.myPharm.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @GetMapping("/login/auth")
    public void login(@RequestParam String code, HttpServletResponse response) throws IOException {
        AuthLoginResDto authLoginRes = authService.login(code);
        System.out.println(authLoginRes);
        if (authLoginRes != null) {
            String redirectUrl = "http://51.21.23.40/login/success?accessToken=" + authLoginRes.getAccessToken() + "&refreshToken=" + authLoginRes.getRefreshToken();
            System.out.println(redirectUrl);
            response.sendRedirect(redirectUrl);
            System.out.println(response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    @PatchMapping("/login/auth")
    public ResponseEntity<AuthLoginResDto> refreshToken(Authentication authentication) {
        return authService.refreshToken(authentication);
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserEntity> getUserInfo(Authentication authentication) {
        UserEntity user = authService.getUserInfo(authentication);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/logout/auth")
    public ResponseEntity<HttpStatus> logout(Authentication authentication){
        return authService.logout(authentication);
    }
}