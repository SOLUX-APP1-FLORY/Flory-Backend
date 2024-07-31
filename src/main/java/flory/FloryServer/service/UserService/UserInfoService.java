package flory.FloryServer.service.UserService;

import flory.FloryServer.domain.User;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.UserInfoResponseDTO;
import flory.FloryServer.web.dto.UserInfoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {
    private final UserRepository userRepository;

    @Autowired
    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfoResponseDTO.InfoResultDTO InfoUser(String userId) {
    Optional<User> userOptional = userRepository.findByUid(userId);
    if (userOptional.isPresent()) {
        User user = userOptional.get();

        return flory.FloryServer.web.dto.UserInfoResponseDTO.InfoResultDTO.builder()
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
