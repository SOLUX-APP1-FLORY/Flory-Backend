package flory.FloryServer.converter;


import flory.FloryServer.domain.User;
import flory.FloryServer.web.dto.UserRequestDTO;
import flory.FloryServer.web.dto.UserResponseDTO;
import java.time.LocalDateTime;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDTO request){

        return User.builder()
                .uid(request.getUid())
                .password(request.getPassword())
                .email(request.getEmail())
                .phoneNum(request.getPhoneNum())
                .build();
    }
}
