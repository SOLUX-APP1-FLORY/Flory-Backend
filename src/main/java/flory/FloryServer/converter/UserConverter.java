package flory.FloryServer.converter;


import flory.FloryServer.apiPayload.exception.PasswordNullException;
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
        if (request.getPassword() == null) {
            throw new PasswordNullException("비밀번호가 null입니다. 비밀번호를 입력해 주세요.");
        }
        return User.builder()
                .uid(request.getUid())
                .password(passwordEncoder.encode(request.getPassword())) // 비밀번호 암호화
                .email(request.getEmail())
                .build();
    }

}
