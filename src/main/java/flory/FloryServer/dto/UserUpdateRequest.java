package flory.FloryServer.dto;

import flory.FloryServer.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private Long id;
    private String nickname;
    private Gender gender;
}
