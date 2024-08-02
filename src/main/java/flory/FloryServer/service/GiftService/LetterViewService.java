package flory.FloryServer.service.GiftService;

import flory.FloryServer.apiPayload.exception.ResourceNotFoundException;
import flory.FloryServer.domain.Flower;
import flory.FloryServer.domain.Gift;
import flory.FloryServer.domain.User;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.repository.FlowerRepository;
import flory.FloryServer.repository.GiftRepository;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.LetterViewRequestDTO;
import flory.FloryServer.web.dto.LetterViewResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LetterViewService {

    private final GiftRepository giftRepository;
    private final UserRepository userRepository;
    private final FlowerRepository flowerRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public LetterViewResponseDTO.LetterViewDetailDTO viewLetter(String token, LetterViewRequestDTO.LetterViewDTO requestDTO) {
        String jwtToken = token.substring(7); // "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        Optional<User> userOptional = userRepository.findByUid(username);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        User user = userOptional.get(); // 현재 사용자(수신자)

        Long giftId = requestDTO.getGiftId();
        Optional<Gift> giftOptional = giftRepository.findById(giftId);
        Gift gift = giftOptional.orElseThrow(() -> new ResourceNotFoundException("Gift not found"));

        Flower flower = gift.getFlower();

        // Find the bouquet information based on flower name
        Flower bouquet = flowerRepository.findByBouquetNameInFlowerRange(flower.getFlowerName())
                .orElseThrow(() -> new ResourceNotFoundException("Bouquet not found"));

        return LetterViewResponseDTO.LetterViewDetailDTO.builder()
                .letter_id(gift.getId()) // 편지 ID
                .user_id(user.getId()) // 받은 사람 ID(로그인한 사용자의 아이디, 나)
                .sender(gift.getUser().getNickname()) // 보낸 사람 닉네임
                .created_at(gift.getCreatedAt()) // 편지 작성 날짜
                .bouquet_imageUrl(bouquet.getBouquetUrl()) // 꽃 사진 URL
                .bouquet_name(flower.getFlowerName()) // 꽃 이름
                .bouquet_meaning(flower.getFlowerMeaning()) // 꽃 감정
                .content(gift.getMessage()) // 편지 내용
                .build();
    }
}



