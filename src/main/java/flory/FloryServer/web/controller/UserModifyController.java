package flory.FloryServer.web.controller;


import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.UserService.UserModifyService;
import flory.FloryServer.web.dto.UserModifyRequestDTO;
import flory.FloryServer.web.dto.UserModifyResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserModifyController {
    private final UserModifyService userModifyService;


    @PatchMapping("")
    public ApiResponse<UserModifyResponseDTO.ModifyResultDTO> modifyUser(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid UserModifyRequestDTO.ModifyDTO requestDTO) {

        UserModifyResponseDTO responseDTO = userModifyService.modifyUser(token, requestDTO);

        if (responseDTO.isSuccess()) {
            return ApiResponse.onSuccess(responseDTO.getResult());
        } else {
            return ApiResponse.onFailure(responseDTO.getCode(), responseDTO.getMessage(), null);
        }
    }

}
