package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.service.NeighborService.NeighborSearchService;
import flory.FloryServer.web.dto.NeighborSearchResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NeighborSearchController {

    private final NeighborSearchService neighborSearchService;

    private final JwtUtil jwtUtil;

    @GetMapping("/user/search")
    public ApiResponse<List<NeighborSearchResponseDTO>> searchUsers(@RequestHeader("Authorization") String token,
                                                  @RequestParam String nickname) {


        List<NeighborSearchResponseDTO> neighborSearchResponseDTO = neighborSearchService.searchUsersByNickname(token, nickname);

        return ApiResponse.onSuccess(neighborSearchResponseDTO);
    }
}
