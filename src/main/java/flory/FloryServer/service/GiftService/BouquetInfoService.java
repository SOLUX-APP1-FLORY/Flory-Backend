package flory.FloryServer.service.GiftService;

import flory.FloryServer.domain.Flower;
import flory.FloryServer.domain.Gift;
import flory.FloryServer.domain.User;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.repository.FlowerRepository;
import flory.FloryServer.repository.GiftRepository;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.BouquetInfoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BouquetInfoService {
    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public BouquetInfoResponseDTO getBouquets(String token) {
        String jwtToken = token.substring(7); // "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            return BouquetInfoResponseDTO.builder()
                    .isSuccess(false)
                    .code("TOKEN_INVALID")
                    .message("Invalid token")
                    .result(null)
                    .build();
        }

        Optional<User> userOptional = userRepository.findByUid(username);
        if (userOptional.isEmpty()) {
            return BouquetInfoResponseDTO.builder()
                    .isSuccess(false)
                    .code("USER_NOT_FOUND")
                    .message("User not found")
                    .result(null)
                    .build();
        }
        User user = userOptional.get();

        // 해당 사용자가 받은 선물 목록 조회
        List<Gift> gifts = giftRepository.findByTarget(user);

        // GiftResponseDTO로 변환
        List<BouquetInfoResponseDTO.Gift> dtoGifts = gifts.stream()
                .map(gift -> BouquetInfoResponseDTO.Gift.builder()
                        .giftId(gift.getId())
                        .sender(gift.getUser().getNickname()) // 선물 보낸 사용자 닉네임
                        .bouquetId(gift.getFlower().getId())
                        .bouquetUrl(gift.getFlower().getFlowerUrl()) // bouquetUrl에 flowerUrl 설정
                        .build())
                .collect(Collectors.toList());

        return BouquetInfoResponseDTO.builder()
                .isSuccess(true)
                .code("COMMON200")
                .message("성공입니다.")
                .result(dtoGifts)
                .build();
    }
}
