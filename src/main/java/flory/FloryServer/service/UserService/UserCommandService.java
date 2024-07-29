package flory.FloryServer.service.UserService;

import flory.FloryServer.domain.User;
import flory.FloryServer.web.dto.UserRequestDTO;

public interface UserCommandService {

    User joinUser(UserRequestDTO.JoinDTO request);
}
