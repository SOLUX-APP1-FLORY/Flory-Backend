package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.DiaryService.DiaryModifyService;
import flory.FloryServer.web.dto.DiaryModifyRequestDTO;
import flory.FloryServer.web.dto.DiaryModifyResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DiaryModifyController {
    private final DiaryModifyService diaryModifyService;

    @PatchMapping("/diary")
    public ApiResponse<DiaryModifyResponseDTO.DiaryModifyResultDTO> modifyDiary(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid DiaryModifyRequestDTO.DiaryModifyDTO requestDTO) {

        // 일기 수정 요청 처리 및 결과 반환
        DiaryModifyResponseDTO.DiaryModifyResultDTO resultDTO = diaryModifyService.modifyDiary(token, requestDTO);

        // 성공 응답 생성 및 반환
        return ApiResponse.onSuccess(resultDTO);
    }
}
