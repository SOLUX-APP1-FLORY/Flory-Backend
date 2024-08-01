package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.UserService.UserInfoService;
import flory.FloryServer.web.dto.UserInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/user")
    public ApiResponse<UserInfoResponseDTO.InfoResultDTO> infoUser(@RequestHeader("Authorization") String token) {
        UserInfoResponseDTO.InfoResultDTO resultDTO = userInfoService.InfoUser(token);

        // 성공 응답 생성
        return ApiResponse.onSuccess(resultDTO);
    }
}
