package flory.FloryServer.web.dto;
import lombok.Getter;
import lombok.Setter;
import flory.FloryServer.domain.enums.Gender;

import java.time.LocalDate;

public class UserModifyRequestDTO {

    @Getter
    @Setter
    public static class ModifyDTO {
        private String nickname; // 선택적 닉네임
        private Gender gender;   // 선택적 성별
        private LocalDate birthdate; // 선택적 생년월일
    }
}