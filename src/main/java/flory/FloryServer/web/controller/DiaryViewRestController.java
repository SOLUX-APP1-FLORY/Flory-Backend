package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.DiaryService.DiaryViewService;
import flory.FloryServer.web.dto.DiaryViewRequestDTO;
import flory.FloryServer.web.dto.DiaryViewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DiaryViewRestController {

    private final DiaryViewService diaryViewService;// 다이어리 서비스 주입

    @Autowired
    public DiaryViewRestController(DiaryViewService diaryViewService) {
        this.diaryViewService = diaryViewService;
    }

    @GetMapping("/diaries/{id}")
    @Operation(summary = "사용자가 작성한 다이어리 조회 API", description = "특정 사용자가 작성한 다이어리의 상세 정보를 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOT_FOUND", description = "다이어리를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    /*@Parameters({
            @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "diaryId", description = "조회할 다이어리의 아이디, path variable 입니다!")
    })*/
    public ApiResponse<DiaryViewResponseDTO.DiaryViewDetailDTO> viewDiary(
            // GET 요청은 일반적으로 @RequestBody를 사용하지 않음.
            @PathVariable Long id,
            @RequestParam Long user_id,
            @RequestParam String date) {

        // 다이어리 조회 서비스 호출
        DiaryViewRequestDTO.DiaryViewDTO requestDTO = new DiaryViewRequestDTO.DiaryViewDTO();
        requestDTO.setUser_id(user_id);
        requestDTO.setDate(date);

        DiaryViewResponseDTO.DiaryViewDetailDTO resultDTO = diaryViewService.viewDiary(id, requestDTO);
        return ApiResponse.onSuccess(resultDTO);// 성공적으로 응답
    }
}