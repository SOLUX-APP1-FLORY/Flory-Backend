package flory.FloryServer.login.dto;

import flory.FloryServer.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    private String uid;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                //.email(email)
                .uid(uid)
                .password(passwordEncoder.encode(password))
                //.authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(uid, password);
    }
}
