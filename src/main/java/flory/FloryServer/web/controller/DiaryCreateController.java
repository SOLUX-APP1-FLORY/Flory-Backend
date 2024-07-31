package flory.FloryServer.web.controller;

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
    public DiaryCreateResponseDTO.CreateResultDTO createDiary(@RequestHeader("Authorization") String token, @RequestBody DiaryCreateRequestDTO.CreateDTO requestDTO) {
        return diaryCreateService.createDiary(token, requestDTO);
    }
}
