package flory.FloryServer.web.controller;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.apiPayload.exception.RelationException;
import flory.FloryServer.domain.User;
import flory.FloryServer.service.NeighborService.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/neighbor")
public class RelationshipController {

    @Autowired
    private RelationshipService relationshipService;

    @PostMapping("/follow")
    public ApiResponse<String> followUser(@RequestParam Long userId, @RequestParam Long targetUserId) {
        try {
            relationshipService.followUser(userId, targetUserId);
            User user = relationshipService.getUserById(userId);
            User targetUser = relationshipService.getUserById(targetUserId);
            String resultMessage = String.format("%s와 %s가 친구가 되었습니다~", user.getNickname(), targetUser.getNickname());
            return ApiResponse.onSuccess(resultMessage);
        } catch (RelationException.ResourceNotFoundException e) {
            return ApiResponse.onFailure("common404", e.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.onFailure("common500", "서버 오류", null);
        }
    }

    @PatchMapping("/unfollow")
    public ApiResponse<String> unfollowUser(@RequestParam Long userId, @RequestParam Long targetUserId) {
        try {
            relationshipService.unfollowUser(userId, targetUserId);
            User user = relationshipService.getUserById(userId);
            User targetUser = relationshipService.getUserById(targetUserId);
            String resultMessage = String.format("%s와 %s의 팔로우가 취소되었습니다.", user.getNickname(), targetUser.getNickname());
            return ApiResponse.onSuccess(resultMessage);
        } catch (RelationException.ResourceNotFoundException e) {
            return ApiResponse.onFailure("common404", e.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.onFailure("common500", "서버 오류", null);
        }
    }
}
