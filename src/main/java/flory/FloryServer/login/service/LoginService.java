package flory.FloryServer.login.service;

import flory.FloryServer.login.dto.LoginResponseDTO;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.UserInfoResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

//    private final UserRepository userRepository;
//
//    public LoginService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public LoginResponseDTO.loginDTO loadToken(String token) throws UsernameNotFoundException {

        return LoginResponseDTO.loginDTO.builder()
                .token(token)
                .build();
    }
}

