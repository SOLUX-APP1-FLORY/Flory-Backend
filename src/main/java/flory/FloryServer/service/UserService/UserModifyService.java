package flory.FloryServer.service.UserService;

import flory.FloryServer.domain.User;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.UserModifyRequestDTO;
import flory.FloryServer.web.dto.UserModifyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserModifyService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserModifyService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public UserModifyResponseDTO modifyUser(String token, UserModifyRequestDTO.ModifyDTO requestDTO) {
        // JWT 토큰에서 사용자 이름 추출 및 검증
        String jwtToken = token.substring(7); // "Bearer " 접두사 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            return UserModifyResponseDTO.builder()
                    .isSuccess(false)
                    .code("INVALID_TOKEN")
                    .message("유효하지 않은 토큰입니다.")
                    .result(null)
                    .build();
        }

        // UID로 사용자 찾기
        Optional<User> userOptional = userRepository.findByUid(username);
        if (userOptional.isEmpty()) {
            return UserModifyResponseDTO.builder()
                    .isSuccess(false)
                    .code("USER4004")
                    .message("사용자를 찾을 수 없습니다.")
                    .result(null)
                    .build();
        }

        // 사용자 정보 업데이트
        User user = userOptional.get();

        // 닉네임이 제공된 경우 업데이트
        if (requestDTO.getNickname() != null) {
            user.setNickname(requestDTO.getNickname());
        }

        // 성별이 제공된 경우 업데이트
        if (requestDTO.getGender() != null) {
            user.setGender(requestDTO.getGender());
        }

        // 생년월일이 제공된 경우 업데이트
        if (requestDTO.getBirthdate() != null) {
            user.setBirthdate(requestDTO.getBirthdate());
        }

        // 업데이트된 사용자 정보 저장
        userRepository.save(user);

        // 업데이트된 정보를 담은 응답 DTO 생성
        UserModifyResponseDTO.ModifyResultDTO result = UserModifyResponseDTO.ModifyResultDTO.builder()
                .uid(user.getUid())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .gender(user.getGender())
                .birthdate(user.getBirthdate())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();

        // 성공 응답 반환
        return UserModifyResponseDTO.builder()
                .isSuccess(true)
                .code("COMMON200")
                .message("사용자 정보가 성공적으로 업데이트되었습니다.")
                .result(result)
                .build();
    }
}