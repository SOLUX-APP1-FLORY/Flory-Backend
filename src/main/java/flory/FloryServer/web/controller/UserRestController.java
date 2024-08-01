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
@RequestMapping("/api/v1")
public class UserRestController {

    private final UserCommandService userCommandService;
    private final UserConverter userConverter; // UserConverter 주입

    @PostMapping("/signup")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDTO request) {
        User user = userCommandService.joinUser(request); // joinUser 메서드 호출

        // UserConverter를 통해 User 객체를 DTO로 변환
        UserResponseDTO.JoinResultDTO resultDTO = userConverter.toJoinResultDTO(user);

        // ApiResponse를 사용하여 성공 응답 반환
        return ApiResponse.onSuccess(resultDTO);
    }
}
