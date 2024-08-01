package flory.FloryServer.service.UserService;

import flory.FloryServer.converter.UserConverter;
import flory.FloryServer.domain.User;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserConverter userConverter; // UserConverter 주입

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDTO request) {
        // UserConverter를 사용하여 DTO를 User로 변환
        User newUser = userConverter.toUser(request);

        return userRepository.save(newUser);
    }
}
