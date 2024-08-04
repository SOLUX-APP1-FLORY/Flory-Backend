package flory.FloryServer.web.dto;

import lombok.Getter;
import lombok.Setter;

public class UserRequestDTO {

    @Getter
    @Setter
    public static class JoinDTO{
        private String uid;
        private String password;
        private String email;
    }
}
