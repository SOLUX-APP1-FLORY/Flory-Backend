package flory.FloryServer.login.jwt;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.domain.User;
import flory.FloryServer.login.dto.LoginRequestDTO;
import flory.FloryServer.login.dto.LoginResponseDTO;
import flory.FloryServer.login.service.MyUserDetailsService;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.UserInfoResponseDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import flory.FloryServer.login.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class JwtAuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login") // 중복된 '/auth' 제거
    public ApiResponse<LoginResponseDTO.loginDTO> createAuthenticationToken(@RequestBody LoginRequestDTO loginRequestDTO) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        logger.info("Attempting authentication for uid: {}", loginRequestDTO.getUid());
        logger.info("Password: {}", loginRequestDTO.getPassword());
        try {
            // 인증 처리
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUid(), loginRequestDTO.getPassword())
            );

            // JWT 토큰 생성
            final String jwt = jwtUtil.generateToken(loginRequestDTO.getUid());

            LoginResponseDTO.loginDTO resultDTO = loginService.loadToken(jwt);
            // LoginResponseDTO 생성 및 반환
            return ApiResponse.onSuccess(resultDTO);
        } catch (AuthenticationException e) {
            // 인증 실패 시 예외 처리
            logger.info("Attempting authentication for uid:", e);
            return ApiResponse.onFailure("Login4001","Incorrect username or password",null);
        }
    }
}
