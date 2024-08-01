package flory.FloryServer.service.NeighborService;

import flory.FloryServer.apiPayload.exception.RelationException;
import flory.FloryServer.domain.User;
import flory.FloryServer.domain.mapping.Relationship;
import flory.FloryServer.repository.RelationshipRepository;
import flory.FloryServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelationshipService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    public void followUser(Long userId, Long targetUserId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User targetUser = userRepository.findById(targetUserId).orElseThrow(() -> new RuntimeException("Target user not found"));

        Optional<Relationship> existingRelationship = relationshipRepository.findByUserAndTargetUser(user, targetUser);
        if (existingRelationship.isPresent()) {
            throw new RuntimeException("You are already following this user");
        }

        Relationship relationship = Relationship.builder()
                .user(user)
                .targetUser(targetUser)
                .build();
        relationshipRepository.save(relationship);
    }

    public void unfollowUser(Long userId, Long targetUserId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User targetUser = userRepository.findById(targetUserId).orElseThrow(() -> new RuntimeException("Target user not found"));

        Relationship relationship = relationshipRepository.findByUserAndTargetUser(user, targetUser)
                .orElseThrow(() -> new RuntimeException("Follow relationship not found"));

        relationshipRepository.delete(relationship);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RelationException.ResourceNotFoundException("User not found"));
    }
}
