package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.DiaryService.DiaryViewService;
import flory.FloryServer.web.dto.DiaryViewRequestDTO;
import flory.FloryServer.web.dto.DiaryViewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DateTimeException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class DiaryViewRestController {

    private final DiaryViewService diaryViewService;// 다이어리 서비스 주입

    // 컨트롤러에서 쿼리스트링 처리 로직 변경(전체 date string -> 각각 연&월&일 int)
    @Autowired
    public DiaryViewRestController(DiaryViewService diaryViewService) {
        this.diaryViewService = diaryViewService;
    }

    @GetMapping("/diaries")
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
            @RequestHeader("Authorization") String token,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day) {

        // 입력받은 연&월&일 쿼리스트링을 date로 변환
        LocalDate date;
        try {
            date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 날짜 형식입니다. 연도, 월, 일이 올바른지 확인하세요.");
        }

        // date를 가지고 request DTO 생성
        DiaryViewRequestDTO.DiaryViewDTO requestDTO = new DiaryViewRequestDTO.DiaryViewDTO();
        requestDTO.setDate(date.toString());

        // 다이어리 조회 서비스 호출
//        DiaryViewRequestDTO.DiaryViewDTO requestDTO = new DiaryViewRequestDTO.DiaryViewDTO();
//        // requestDTO.setUser_id(user_id);
//        requestDTO.setDate(date);
        DiaryViewResponseDTO.DiaryViewDetailDTO resultDTO = diaryViewService.viewDiary(token, requestDTO);

        // 성공 응답 생성
        return ApiResponse.onSuccess(resultDTO);// 성공적으로 응답
    }
}