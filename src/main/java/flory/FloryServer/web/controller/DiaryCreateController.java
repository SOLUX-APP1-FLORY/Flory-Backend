package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.DiaryService.DiaryCreateService;
import flory.FloryServer.web.dto.DiaryCreateRequestDTO;
import flory.FloryServer.web.dto.DiaryCreateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DiaryCreateController {
    private final DiaryCreateService diaryCreateService;

    @PostMapping("/diary")
    public ApiResponse<DiaryCreateResponseDTO.CreateResultDTO> createDiary(@RequestHeader("Authorization") String token, @RequestBody DiaryCreateRequestDTO.CreateDTO requestDTO) {
        DiaryCreateResponseDTO responseDTO = diaryCreateService.createDiary(token, requestDTO);

        if (responseDTO.isSuccess()) {
            return ApiResponse.onSuccess(responseDTO.getResult());
        } else {
            return ApiResponse.onFailure(responseDTO.getCode(), responseDTO.getMessage(), responseDTO.getResult());
        }
    }
}
