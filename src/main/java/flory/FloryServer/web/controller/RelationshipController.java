package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.apiPayload.exception.RelationException;
import flory.FloryServer.domain.User;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.service.NeighborService.RelationshipService;
import flory.FloryServer.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/neighbor")
public class RelationshipController {

    @Autowired
    private RelationshipService relationshipService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/follow")
    @Operation(summary = "이웃 추가 API", description = "현재 사용자의 이웃을 추가합니다.")
    public ApiResponse<String> addNeighbor(@RequestHeader("Authorization") String userToken, @RequestBody NeighborAddRequestDTO addRequestDTO) {
        try {
            relationshipService.addNeighbor(userToken, addRequestDTO.getTargetUserId());
            return ApiResponse.onSuccess("이웃 추가에 성공했습니다.");
        } catch (RuntimeException e) {
            return ApiResponse.onFailure("common500", e.getMessage(), null);
        }
    }

    @PatchMapping("/unfollow")
    @Operation(summary = "이웃 삭제 API", description = "현재 사용자의 이웃을 삭제합니다.")
    public ApiResponse<String> deleteNeighbor(@RequestHeader("Authorization") String userToken, @RequestBody NeighborDeleteRequestDTO deleteRequestDTO) {
        try {
            relationshipService.deleteNeighbor(userToken, deleteRequestDTO.getTargetUserId());
            return ApiResponse.onSuccess("이웃 삭제에 성공했습니다.");
        } catch (RuntimeException e) {
            return ApiResponse.onFailure("common500", e.getMessage(), null);
        }
    }

//    @GetMapping("/neighbors")
//    public ApiResponse<NeighborResponseDTO> getNeighbors(@RequestHeader("Authorization") String token) {
//        try {
//            // Bearer 부분을 제거하고 토큰 값만 추출
//            String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
//
//            if (jwtUtil.validateToken(jwtToken)) {
//                String username = jwtUtil.getUidFromToken(jwtToken);
//                List<String> neighbors = relationshipService.getNeighborsByUsername(username);
//                NeighborResponseDTO response = new NeighborResponseDTO();
//                response.setNeighbors(neighbors);
//                return ApiResponse.onSuccess(response);
//            } else {
//                return ApiResponse.onFailure("common401", "토큰이 유효하지 않습니다.", null);
//            }
//        } catch (Exception e) {
//            return ApiResponse.onFailure("common500", "서버 오류", null);
//        }
//    }
@GetMapping("")
@Operation(summary = "이웃 목록 조회 API", description = "현재 사용자의 이웃 목록을 조회합니다.")
@ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
})
public ApiResponse<List<String>> getNeighbors(@RequestHeader("Authorization") String token) {
    try {
        List<String> neighbors = relationshipService.getNeighborsByToken(token);
        return ApiResponse.onSuccess(neighbors);
    } catch (RuntimeException e) {
        return ApiResponse.onFailure("common500", e.getMessage(), null);
    }
}
}
