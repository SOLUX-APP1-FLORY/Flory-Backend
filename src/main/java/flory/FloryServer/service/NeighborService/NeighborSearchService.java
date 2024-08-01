package flory.FloryServer.service.NeighborService;

import flory.FloryServer.domain.User;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.NeighborSearchResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NeighborSearchService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public List<NeighborSearchResponseDTO> searchUsersByNickname(String token, String nickname) {
        String jwtToken = token.substring(7); // 토큰에서 "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            throw new IllegalArgumentException("Invalid JWT token");
        }


        List<User> users = userRepository.findByNicknameContaining(nickname);
        return users.stream()
                .filter(user -> !user.getUid().equals(username))
                .map(user -> new NeighborSearchResponseDTO(user.getUid(), user.getNickname()))
                .collect(Collectors.toList());
    }
}
