package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.FlowerService.FlowerCheckService;
import flory.FloryServer.web.dto.FlowerCheckResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flower")
public class FlowerCheckController {
    private final FlowerCheckService flowerCheckService;

    @Autowired
    public FlowerCheckController(FlowerCheckService flowerCheckService) {
        this.flowerCheckService = flowerCheckService;
    }

    // 날짜를 쿼리 파라미터로 받음
    @GetMapping("/selected")
    public ApiResponse<FlowerCheckResponseDTO.ChooseResultDTO> getSelectedFlower(@RequestParam("date") String dateString) {
        LocalDate date;
        try {
            date = LocalDate.parse(dateString);  // 쿼리 파라미터로 전달된 날짜 문자열을 LocalDate로 변환
        } catch (Exception e) {
            // 날짜 형식이 잘못된 경우
            return ApiResponse.onFailure("400", "Invalid date format : Required 'YYYY-MM-DD' ", null);
        }

        // 서비스 호출
        Optional<FlowerCheckResponseDTO> flowerOptional = flowerCheckService.getSelectedFlower(date);

        if (flowerOptional.isPresent()) {
            return ApiResponse.onSuccess(flowerOptional.get().getChooseResultDTO()); // FlowerCheckResponseDTO에서 ChooseResultDTO 추출
        } else {
            return ApiResponse.onFailure("404", "Flower not found", null); // 꽃 정보가 없는 경우
        }
    }
}
