package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.UserService.UserUpdateService;
import flory.FloryServer.web.dto.UserUpdateRequestDTO;
import flory.FloryServer.web.dto.UserUpdateResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserUpdateController {
    private final UserUpdateService userUpdateService;

    @PatchMapping("/member")
    public ApiResponse<UserUpdateResponseDTO.UpdateResultDTO> userUpdate(@RequestBody @Valid UserUpdateRequestDTO.UpdateDTO requestDTO) {
        try {
            // 사용자 정보 업데이트
            UserUpdateResponseDTO.UpdateResultDTO responseDTO = userUpdateService.updateUser(requestDTO);

            // 성공 응답 생성
            return ApiResponse.onSuccess(responseDTO);
        } catch (RuntimeException e) {
            // 실패 응답 생성
            return ApiResponse.onFailure("Memeber4004", e.getMessage(), null);
        }
    }
}
