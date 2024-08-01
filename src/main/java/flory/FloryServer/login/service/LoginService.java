package flory.FloryServer.login.service;

import flory.FloryServer.login.dto.LoginResponseDTO;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.UserInfoResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public LoginResponseDTO.loginDTO loadToken(String token) throws UsernameNotFoundException {

        return LoginResponseDTO.loginDTO.builder()
                .token(token)
                .build();
    }
}

