package flory.FloryServer.service.UserService;

import flory.FloryServer.domain.User;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.web.dto.UserInfoResponseDTO;
import flory.FloryServer.web.dto.UserModifyResponseDTO;
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
        if (userOptional.isEmpty()) {
            return UserInfoResponseDTO.builder()
                    .isSuccess(false)
                    .code("USER4004")
                    .message("사용자를 찾을 수 없습니다.")
                    .result(null)
                    .build();
        }
        User user = userOptional.get();

        UserInfoResponseDTO.UserDetailDTO result = UserInfoResponseDTO.UserDetailDTO.builder()
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
        }
}


