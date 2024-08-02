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
public class UserModifyResponseDTO {
    private boolean isSuccess;
    private String code;
    private String message;
    private UserModifyResponseDTO.ModifyResultDTO result;

    @Getter
    @Builder
    public static class ModifyResultDTO {
        private String uid;
        private String nickname;
        private String email;
        private Gender gender;
        private LocalDate birthdate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

}