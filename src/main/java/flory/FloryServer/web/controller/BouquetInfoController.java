package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.service.GiftService.BouquetInfoService;
import flory.FloryServer.web.dto.BouquetInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gift")
@RequiredArgsConstructor
public class BouquetInfoController {
    private final BouquetInfoService bouquetInfoService;

    @GetMapping("/bouquet")
    public ApiResponse<List<BouquetInfoResponseDTO.Gift>> getGifts(@RequestHeader("Authorization") String token) {
        BouquetInfoResponseDTO response = bouquetInfoService.getBouquets(token);

        if (response.isSuccess()) {
            return ApiResponse.onSuccess(response.getResult());
        } else {
            return ApiResponse.onFailure(response.getCode(), response.getMessage(), response.getResult());
        }
    }
}
