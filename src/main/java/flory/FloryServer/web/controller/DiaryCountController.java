package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.DiaryService.DiaryCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DiaryCountController {

    private final DiaryCountService diaryCountService;

    @Autowired
    public DiaryCountController(DiaryCountService diaryViewService) {
        this.diaryCountService = diaryViewService;
    }

    @GetMapping("/main")
    public ApiResponse<Map<String, Integer>> getDiaryCountForCurrentMonth(@RequestHeader("Authorization") String token) {
        int diaryCount = diaryCountService.getDiaryCountForCurrentMonth(token);

        // 결과 맵 생성
        Map<String, Integer> result = new HashMap<>();
        result.put("count", diaryCount);

        // 성공 응답 생성
        return ApiResponse.onSuccess(result);
    }
}
