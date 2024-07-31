package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.FlowerService.FlowerChooseService;
import flory.FloryServer.web.dto.FlowerChooseRequestDTO;
import flory.FloryServer.web.dto.FlowerChooseResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FlowerChooseController {
    private final FlowerChooseService flowerChooseService;

    @PostMapping("/flower")
    public ApiResponse<FlowerChooseResponseDTO.ChooseResultDTO> chooseFlower(@RequestBody @Valid FlowerChooseRequestDTO.ChooseDTO requestDTO) {

        FlowerChooseResponseDTO.ChooseResultDTO resultDTO = flowerChooseService.chooseFlower(requestDTO.getFlowerName());

        // 성공 응답 생성
        return ApiResponse.onSuccess(resultDTO);
    }
}
