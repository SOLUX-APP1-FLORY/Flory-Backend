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
    public ApiResponse<DiaryModifyResponseDTO.ModifyResultDTO> modifyDiary(@RequestHeader("Authorization") String token, @RequestBody @Valid DiaryModifyRequestDTO.ModifyDTO requestDTO) {

        DiaryModifyResponseDTO.ModifyResultDTO resultDTO = diaryModifyService.modifyDiary(token, requestDTO);

        // 성공 응답 생성
        return ApiResponse.onSuccess(resultDTO);}

}
