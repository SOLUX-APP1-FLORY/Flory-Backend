package flory.FloryServer.web.dto;

import lombok.Getter;

public class UserRequestDTO {

    @Getter
    public static class JoinDTO{
        String uid;
        String password;
        String email;
    }
}
