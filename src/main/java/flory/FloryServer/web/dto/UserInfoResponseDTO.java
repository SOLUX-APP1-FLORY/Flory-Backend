package flory.FloryServer.web.dto;

import flory.FloryServer.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDTO {
    private boolean isSuccess;
    private String code;
    private String message;
    private InfoResultDTO result;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InfoResultDTO {
        private String userId;
        private String nickname;
        private String email;
        private LocalDate birthdate;
        private Gender gender;
        private LocalDateTime createdAt;
    }
}
