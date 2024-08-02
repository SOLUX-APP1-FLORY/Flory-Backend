package flory.FloryServer.service.GiftService;

import flory.FloryServer.apiPayload.ApiResponse;
import flory.FloryServer.domain.Card;
import flory.FloryServer.domain.Flower;
import flory.FloryServer.domain.Gift;
import flory.FloryServer.domain.User;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.repository.CardRepository;
import flory.FloryServer.repository.FlowerRepository;
import flory.FloryServer.repository.GiftRepository;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.LetterCreateRequestDTO;
import flory.FloryServer.web.dto.LetterCreateResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LetterCreateService {

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private CardRepository cardRepository; // CardRepository 주입

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public ApiResponse<LetterCreateResponseDTO.CreateResultDTO> createletter(String token, LetterCreateRequestDTO.LetterCreateDTO requestDTO) {
        String jwtToken = token.substring(7); // 토큰에서 "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            return ApiResponse.onFailure("INVALID_TOKEN", "Invalid token", null);
        }

        Optional<User> userOptional = userRepository.findByUid(username);
        if (userOptional.isEmpty()) {
            return ApiResponse.onFailure("USER_NOT_FOUND", "User not found", null);
        }
        User user = userOptional.get();

        // 타겟 사용자 조회
        Optional<User> targetUserOptional = userRepository.findByNickname(requestDTO.getReceiverNickname());
        if (targetUserOptional.isEmpty()) {
            return ApiResponse.onFailure("TARGET_USER_NOT_FOUND", "Target user not found", null);
        }
        User targetNickname = targetUserOptional.get();

        // 꽃 조회
        Optional<Flower> flowerOptional = flowerRepository.findByFlowerNameInFlowerRange(requestDTO.getFlowerName());
        if (flowerOptional.isEmpty()) {
            return ApiResponse.onFailure("FLOWER_NOT_FOUND", "Flower not found", null);
        }
        Flower flower = flowerOptional.get();

        // 카드 조회 (requestDTO에서 카드 ID 사용)
//        Optional<Card> cardOptional = cardRepository.findById(requestDTO.getCard_id());// CardRepository 사용
//        if (cardOptional.isEmpty()) {
//            return new LetterCreateResponseDTO.LetterCreateResultDTO("Card not found");
//        }
//        Card card = cardOptional.get();

        // Gift 객체 생성 및 저장
        Gift gift = Gift.builder()
                .user(user) // User 객체를 할당
                //.card(card) // 조회한 카드 객체를 할당
                .target(targetNickname) // 선물받을 사람
                .message(requestDTO.getContent())
                .flower(flower)
                .build();

        giftRepository.save(gift);

        // return new LetterCreateResponseDTO.CreateResultDTO("편지를 성공적으로 작성했습니다.");
        LetterCreateResponseDTO.CreateResultDTO result = new LetterCreateResponseDTO.CreateResultDTO("편지를 성공적으로 작성했습니다.");
        return ApiResponse.onSuccess(result);
    }
}