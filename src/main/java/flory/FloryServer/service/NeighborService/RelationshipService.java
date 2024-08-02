package flory.FloryServer.service.NeighborService;

import flory.FloryServer.apiPayload.exception.RelationException;
import flory.FloryServer.domain.User;
import flory.FloryServer.domain.mapping.Relationship;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.repository.RelationshipRepository;
import flory.FloryServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RelationshipService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public void addNeighbor(String userToken, String targetUserNickname) { // , Long targetUserId
        User user = getUserFromToken(userToken);
        User targetUser = userRepository.findByNickname(targetUserNickname)
                .orElseThrow(() -> new RuntimeException("Target user not found"));

        Relationship relationship = Relationship.builder()
                .user(user)
                .targetUser(targetUser)
                .build();

        relationshipRepository.save(relationship);
    }

    public void deleteNeighbor(String userToken, String targetUserNickname) {
        User user = getUserFromToken(userToken);
        User targetUser = userRepository.findByNickname(targetUserNickname)
                .orElseThrow(() -> new RuntimeException("Target user not found"));

        Relationship relationship = relationshipRepository.findByUserAndTargetUser(user, targetUser)
                .orElseThrow(() -> new RuntimeException("Relationship not found"));

        relationshipRepository.delete(relationship);
    }

//    public List<String> getNeighbors() {
//        User user = getAuthenticatedUser();
//
//        List<Relationship> relationships = relationshipRepository.findByUser(user);
//
//        return relationships.stream()
//                .map(relationship -> relationship.getTargetUser().getNickname())
//                .collect(Collectors.toList());
//    }
    public List<String> getNeighborsByToken(String token) {
        String jwtToken = token.substring(7); // "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            throw new RuntimeException("Invalid token");
        }

        Optional<User> userOptional = userRepository.findByUid(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        List<Relationship> relationships = relationshipRepository.findAllByUser(user);

        // 이웃 닉네임 목록 생성
        return relationships.stream()
                .map(relationship -> relationship.getTargetUser().getNickname())
                .collect(Collectors.toList());
    }

    private User getUserFromToken(String token) {
        String jwtToken = token.substring(7); // "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            throw new RuntimeException("Invalid token");
        }

        return userRepository.findByUid(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserById(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new RelationException.ResourceNotFoundException("User not found: "+ nickname));
    }

}
