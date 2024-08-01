package flory.FloryServer.web.controller;

import flory.FloryServer.service.NeighborService.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/neighbor")
public class RelationshipController {

    @Autowired
    private RelationshipService relationshipService;

    @PostMapping("/follow")
    public void followUser(@RequestParam Long userId, @RequestParam Long targetUserId) {
        relationshipService.followUser(userId, targetUserId);
    }

    @PatchMapping("/unfollow")
    public void unfollowUser(@RequestParam Long userId, @RequestParam Long targetUserId) {
        relationshipService.unfollowUser(userId, targetUserId);
    }
}
