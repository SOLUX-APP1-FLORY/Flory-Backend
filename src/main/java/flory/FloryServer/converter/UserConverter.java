package flory.FloryServer.converter;


import flory.FloryServer.domain.User;
import flory.FloryServer.web.dto.UserRequestDTO;
import flory.FloryServer.web.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Component
public class UserConverter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public User toUser(UserRequestDTO.JoinDTO request){
        return User.builder()
                .uid(request.getUid())
                .password(passwordEncoder.encode(request.getPassword())) // 비밀번호 암호화
                .email(request.getEmail())
                .build();
    }
}
