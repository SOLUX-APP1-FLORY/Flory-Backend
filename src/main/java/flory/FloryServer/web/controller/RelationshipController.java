package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.apiPayload.exception.RelationException;
import flory.FloryServer.domain.User;
import flory.FloryServer.service.NeighborService.RelationshipService;
import flory.FloryServer.web.dto.FollowRequestDTO;
import flory.FloryServer.web.dto.FollowResponseDTO;
import flory.FloryServer.web.dto.UnFollowRequestDTO;
import flory.FloryServer.web.dto.UnFollowResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/neighbor")
public class RelationshipController {

    @Autowired
    private RelationshipService relationshipService;

    @PostMapping("/follow")
    public ApiResponse<FollowResponseDTO> followUser(@RequestBody FollowRequestDTO request, @RequestHeader String token) {
        try {
            relationshipService.followUser(request.getUserNickname(), request.getTargetUserNickname());
            User user = relationshipService.getUserById(request.getUserNickname());
            User targetUser = relationshipService.getUserById(request.getTargetUserNickname());
            String resultMessage = String.format("%s(님)이 %s(님)과 친구가 되었습니다~", user.getNickname(), targetUser.getNickname());
            FollowResponseDTO response = new FollowResponseDTO();
            response.setResultMessage(resultMessage);
            return ApiResponse.onSuccess(response);
        } catch (RelationException.ResourceNotFoundException e) {
            return ApiResponse.onFailure("common404", e.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.onFailure("common500", "서버 오류", null);
        }
    }

    @PatchMapping("/unfollow")
    public ApiResponse<UnFollowResponseDTO> unfollowUser(@RequestBody UnFollowRequestDTO request, @RequestHeader String token) {
        try {
            relationshipService.unfollowUser(request.getUserNickname(), request.getTargetUserNickname());
            User user = relationshipService.getUserById(request.getUserNickname());
            User targetUser = relationshipService.getUserById(request.getTargetUserNickname());
            String resultMessage = String.format("%s(님)이 %s(님)과의 팔로우를 취소헸습니다ㅠ", user.getNickname(), targetUser.getNickname());
            UnFollowResponseDTO response = new UnFollowResponseDTO();
            response.setResultMessage(resultMessage);
            return ApiResponse.onSuccess(response);
        } catch (RelationException.ResourceNotFoundException e) {
            return ApiResponse.onFailure("common404", e.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.onFailure("common500", "서버 오류", null);
        }
    }
}
