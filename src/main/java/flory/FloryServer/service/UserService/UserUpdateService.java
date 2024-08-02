package flory.FloryServer.service.UserService;

import flory.FloryServer.domain.User;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.UserUpdateRequestDTO;
import flory.FloryServer.web.dto.UserUpdateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import flory.FloryServer.login.jwt.JwtUtil;

import java.util.Optional;

@Service
public class UserUpdateService {
    private final UserRepository userRepository;

    @Autowired
    public UserUpdateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public UserUpdateResponseDTO updateUser(UserUpdateRequestDTO.UpdateDTO requestDTO) {

        Optional<User> userOptional = userRepository.findById(requestDTO.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNickname(requestDTO.getNickname());
            user.setGender(requestDTO.getGender());

            userRepository.save(user);

            UserUpdateResponseDTO.UpdateResultDTO result = UserUpdateResponseDTO.UpdateResultDTO.builder()
                    .uid(user.getUid())
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .gender(user.getGender())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .build();

            return UserUpdateResponseDTO.builder()
                    .isSuccess(true)
                    .code("COMMON200")
                    .message("User updated successfully")
                    .result(result)
                    .build();
        } else {
            return UserUpdateResponseDTO.builder()
                    .isSuccess(false)
                    .code("USER404")
                    .message("User not found")
                    .result(null)
                    .build();
        }
    }
}
