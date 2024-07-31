package flory.FloryServer.service.UserService;

import flory.FloryServer.domain.User;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.web.dto.UserInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserInfoResponseDTO.InfoResultDTO InfoUser(String token) {
        if (token == null || !jwtUtil.validateToken(token)) {
            throw new RuntimeException("JWT Token is missing or invalid");
        }

        String userId = jwtUtil.getUidFromToken(token);
        Optional<User> userOptional = userRepository.findByUid(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            return UserInfoResponseDTO.InfoResultDTO.builder()
                    .userId(user.getUid())
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .gender(user.getGender())
                    .createdAt(user.getCreatedAt())
                    .build();
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
