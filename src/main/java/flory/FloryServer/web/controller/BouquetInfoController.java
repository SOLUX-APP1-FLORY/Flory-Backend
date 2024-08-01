package flory.FloryServer.web.controller;

import flory.FloryServer.service.GiftService.BouquetInfoService;
import flory.FloryServer.web.dto.BouquetInfoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gift")
public class BouquetInfoController {
    @Autowired
    private BouquetInfoService bouquetInfoService;

    @GetMapping("/bouquet")
    public ResponseEntity<BouquetInfoResponseDTO> getGifts(@RequestHeader("Authorization") String token) {

        BouquetInfoResponseDTO response = bouquetInfoService.getBouquets(token);

        return ResponseEntity.ok(response);
    }
}
