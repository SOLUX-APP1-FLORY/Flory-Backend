package flory.FloryServer.web.dto;

import flory.FloryServer.domain.enums.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserInfoResponseDTO {
    private boolean isSuccess;
    private String code;
    private String message;
    private UserInfoResponseDTO.UserDetailDTO result;

    @Getter
    @Setter
    @Builder
    public static class UserDetailDTO {
        private String userId;
        private String nickname;
        private String email;
        private LocalDate birthdate;
        private Gender gender;
        private LocalDateTime createdAt;
    }
}
