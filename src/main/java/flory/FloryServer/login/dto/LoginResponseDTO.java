package flory.FloryServer.login.dto;

import lombok.*;


public class LoginResponseDTO {
    @Data
    @Builder
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class loginDTO {
        private String token;
    }// JWT 토큰
}
