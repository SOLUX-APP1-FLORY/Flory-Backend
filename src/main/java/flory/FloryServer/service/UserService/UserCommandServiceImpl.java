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

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDTO request) {

        User newUser = UserConverter.toUser(request);

        return userRepository.save(newUser);
    }
}
