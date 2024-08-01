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
    public ApiResponse<FollowResponseDTO> followUser(@RequestBody FollowRequestDTO request) {
        try {
            relationshipService.followUser(request.getUserId(), request.getTargetUserId());
            User user = relationshipService.getUserById(request.getUserId());
            User targetUser = relationshipService.getUserById(request.getTargetUserId());
            String resultMessage = String.format("%s와 %s가 친구가 되었습니다~", user.getNickname(), targetUser.getNickname());
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
    public ApiResponse<UnFollowResponseDTO> unfollowUser(@RequestBody UnFollowRequestDTO request) {
        try {
            relationshipService.unfollowUser(request.getUserId(), request.getTargetUserId());
            User user = relationshipService.getUserById(request.getUserId());
            User targetUser = relationshipService.getUserById(request.getTargetUserId());
            String resultMessage = String.format("%s와 %s의 팔로우가 취소되었습니다.", user.getNickname(), targetUser.getNickname());
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
