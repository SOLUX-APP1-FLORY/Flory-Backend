package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.UserService.UserInfoService;
import flory.FloryServer.web.dto.UserInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/user/{id}")
    public ApiResponse<UserInfoResponseDTO.InfoResultDTO> infoUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        UserInfoResponseDTO.InfoResultDTO resultDTO = userInfoService.InfoUser(username);

        // 성공 응답 생성
        return ApiResponse.onSuccess(resultDTO);
    }
}
