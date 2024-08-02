package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.GiftService.LetterViewService;
import flory.FloryServer.web.dto.LetterViewRequestDTO;
import flory.FloryServer.web.dto.LetterViewResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gift")
public class LetterViewRestController {

    private final LetterViewService letterViewService;

    @Autowired
    public LetterViewRestController(LetterViewService letterViewService) {
        this.letterViewService = letterViewService;
    }

    @GetMapping("/{giftId}/letter")
    public ApiResponse<LetterViewResponseDTO.LetterViewDetailDTO> viewLetter(
            @RequestHeader("Authorization") String token,
            @PathVariable("giftId") Long giftId) {

        LetterViewRequestDTO.LetterViewDTO requestDTO = new LetterViewRequestDTO.LetterViewDTO();
        requestDTO.setGiftId(giftId);

        LetterViewResponseDTO.LetterViewDetailDTO resultDTO = letterViewService.viewLetter(token, requestDTO);

        return ApiResponse.onSuccess(resultDTO);
    }
}

/*
@RestController
@RequestMapping("/api/v1/gift")
public class LetterViewRestController {

    private final LetterViewService letterViewService;

    @Autowired
    public LetterViewRestController(LetterViewService letterViewService) {
        this.letterViewService = letterViewService;
    }

    @GetMapping("/{giftId}/letter")
    public ApiResponse<LetterViewResponseDTO.LetterViewDetailDTO> viewLetter(
            @RequestHeader("Authorization") String token,
            @RequestParam String date,
            @PathVariable("giftId") Long sender_id) {

        // LetterViewRequestDTO 생성 및 설정
        LetterViewRequestDTO.LetterViewDTO requestDTO = new LetterViewRequestDTO.LetterViewDTO();
        requestDTO.setDate(date);
        requestDTO.setSender(sender_id); // giftId를 sender로 설정

        // 편지 조회 서비스 호출
        LetterViewResponseDTO.LetterViewDetailDTO resultDTO = letterViewService.viewLetter(token, requestDTO);

        return ApiResponse.onSuccess(resultDTO);
    }
}*/
