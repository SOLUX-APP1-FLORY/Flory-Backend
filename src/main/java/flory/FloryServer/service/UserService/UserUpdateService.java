package flory.FloryServer.service.UserService;

import flory.FloryServer.domain.User;
import flory.FloryServer.domain.enums.Gender;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.UserUpdateRequestDTO;
import flory.FloryServer.web.dto.UserUpdateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserUpdateService {
    private final UserRepository userRepository;

    @Autowired
    public UserUpdateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserUpdateResponseDTO.UpdateResultDTO updateUser(String userId, UserUpdateRequestDTO.UpdateDTO requestDTO) {
        Optional<User> userOptional = userRepository.findByUid(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNickname(requestDTO.getNickname());
            user.setGender(requestDTO.getGender());

            userRepository.save(user);

            return UserUpdateResponseDTO.UpdateResultDTO.builder()
                    .uid(user.getUid())
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .gender(user.getGender())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .build();
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
