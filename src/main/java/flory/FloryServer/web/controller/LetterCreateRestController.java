package flory.FloryServer.web.controller;

import flory.FloryServer.service.GiftService.LetterCreateService;
import flory.FloryServer.web.dto.LetterCreateRequestDTO;
import flory.FloryServer.web.dto.LetterCreateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gift")
@RequiredArgsConstructor
public class LetterCreateRestController {
    private final LetterCreateService letterCreateService;

    @PostMapping("/letter")
    public LetterCreateResponseDTO.LetterCreateResultDTO createletter(@RequestHeader("Authorization") String token, @RequestBody LetterCreateRequestDTO.LetterCreateDTO requestDTO) {
        return letterCreateService.createletter(token, requestDTO);
    }
}
