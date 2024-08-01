package flory.FloryServer.web.controller;

import flory.FloryServer.service.DiaryService.DiaryListService;
import flory.FloryServer.web.dto.DiaryListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DiaryListController {
    @Autowired
    private DiaryListService diaryListService;

    @GetMapping("/main/diaries")
    public ResponseEntity<DiaryListResponseDTO> getDiaries(@RequestHeader("Authorization") String token,
                                                           @RequestParam int year,
                                                           @RequestParam int month,
                                                           @RequestParam int day) {

        DiaryListResponseDTO response = diaryListService.getDiaries(token, year, month, day);

        return ResponseEntity.ok(response);
    }


}
