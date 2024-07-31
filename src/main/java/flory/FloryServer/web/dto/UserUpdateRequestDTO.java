package flory.FloryServer.web.dto;

import flory.FloryServer.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;

public class UserUpdateRequestDTO {
    @Getter
    @Setter
    public static class UpdateDTO{
        private String nickname;
        private Gender gender;

    }
}
