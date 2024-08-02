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

    public UserInfoResponseDTO infoUser(String token) {
        String jwtToken = token.substring(7); // 토큰에서 "Bearer " 부분 제거

        if (!jwtUtil.validateToken(jwtToken)) {
            return UserInfoResponseDTO.builder()
                    .isSuccess(false)
                    .code("INVALID_TOKEN")
                    .message("Invalid token")
                    .result(null)
                    .build();
        }

        String userId = jwtUtil.getUidFromToken(jwtToken);

        Optional<User> userOptional = userRepository.findByUid(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            UserInfoResponseDTO.InfoResultDTO result = UserInfoResponseDTO.InfoResultDTO.builder()
                    .userId(user.getUid())
                    .nickname(user.getNickname())
                    .birthdate(user.getBirthdate())
                    .email(user.getEmail())
                    .gender(user.getGender())
                    .createdAt(user.getCreatedAt())
                    .build();

            return UserInfoResponseDTO.builder()
                    .isSuccess(true)
                    .code("USER_FOUND")
                    .message("User found")
                    .result(result)
                    .build();
        } else {
            return UserInfoResponseDTO.builder()
                    .isSuccess(false)
                    .code("USER_NOT_FOUND")
                    .message("User not found")
                    .result(null)
                    .build();
        }
    }
}
