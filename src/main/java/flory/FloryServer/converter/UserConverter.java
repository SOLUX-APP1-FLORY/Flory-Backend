package flory.FloryServer.converter;

import flory.FloryServer.dto.UserResponse;

public class UserConverter {
    public static UserResponse.UserCreateDTO toUserCreateDTO(){
        return UserResponse.UserCreateDTO.builder()
                .testString("Test!!")
                .build();
    }
}
