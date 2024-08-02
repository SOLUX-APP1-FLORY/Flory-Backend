package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.DiaryService.DiaryListService;
import flory.FloryServer.web.dto.DiaryListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DiaryListController {
    private final DiaryListService diaryListService;

    @GetMapping("/main/diaries")
    public ApiResponse<DiaryListResponseDTO.DiaryResultDTO> getDiaries(@RequestHeader("Authorization") String token,
                                                                    @RequestParam int year,
                                                                    @RequestParam int month,
                                                                    @RequestParam int day) {

        DiaryListResponseDTO response = diaryListService.getDiaries(token, year, month, day);

        if (response.isSuccess()) {
            return ApiResponse.onSuccess(response.getResult());
        } else {
            return ApiResponse.onFailure(response.getCode(), response.getMessage(), response.getResult());
        }
    }
}
