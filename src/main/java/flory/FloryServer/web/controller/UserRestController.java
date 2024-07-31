package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.converter.UserConverter;
import flory.FloryServer.domain.User;
import flory.FloryServer.service.UserService.UserCommandService;
import flory.FloryServer.web.dto.UserRequestDTO;
import flory.FloryServer.web.dto.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

    private final UserCommandService userCommandService;

    @PostMapping("/signup ")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDTO request) {
        // return null;
        User user = userCommandService.joinUser((request));
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }
}
