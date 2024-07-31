package flory.FloryServer.web.dto;

import flory.FloryServer.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserInfoResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InfoResultDTO{
        String userId;
        String nickname;
        String email;
        String birthdate;
        Gender gender;
        LocalDateTime createdAt;
    }
}
