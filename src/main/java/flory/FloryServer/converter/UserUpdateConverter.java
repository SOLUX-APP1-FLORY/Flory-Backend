package flory.FloryServer.converter;

import flory.FloryServer.domain.User;
import flory.FloryServer.service.UserService.UserUpdateService;
import flory.FloryServer.web.dto.UserUpdateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class UserUpdateConverter {
    public static UserUpdateResponseDTO.UpdateResultDTO toUpdateResultDTO(User user){
        return UserUpdateResponseDTO.UpdateResultDTO.builder()
                .uid(user.getUid())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
