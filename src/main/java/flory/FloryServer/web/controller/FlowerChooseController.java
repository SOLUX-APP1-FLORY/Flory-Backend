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

        try {
            FlowerChooseResponseDTO.ChooseResultDTO resultDTO = flowerChooseService.chooseFlower(requestDTO.getFlowerName());
            return ApiResponse.onSuccess(resultDTO);
        } catch (RuntimeException e) {
            return ApiResponse.onFailure("FLOWER_NOT_FOUND", e.getMessage(), null);
        }
    }
}
