package flory.FloryServer.web.dto;

import flory.FloryServer.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponseDTO {
    private boolean isSuccess;
    private String code;
    private String message;
    private UpdateResultDTO result;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateResultDTO {
        private String uid;
        private String nickname;
        private String email;
        private Gender gender;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
