package flory.FloryServer.service.GiftService;

import flory.FloryServer.apiPayload.exception.ResourceNotFoundException;
import flory.FloryServer.domain.Flower;
import flory.FloryServer.domain.Gift;
import flory.FloryServer.domain.User;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.repository.GiftRepository;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.LetterViewRequestDTO;
import flory.FloryServer.web.dto.LetterViewResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LetterViewService {

    private final GiftRepository giftRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public LetterViewResponseDTO.LetterViewDetailDTO viewLetter(String token, LetterViewRequestDTO.LetterViewDTO requestDTO) {
        String jwtToken = token.substring(7); // "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            throw new RuntimeException("Invalid token");
        }

        Optional<User> userOptional = userRepository.findByUid(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get(); // 현재 사용자(수신자)

        LocalDateTime createdAt;
        try {
            createdAt = LocalDateTime.parse(requestDTO.getDate(), DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 날짜 형식입니다. 올바른 형식은 yyyy-MM-dd'T'HH:mm:ss입니다.");
        }

        LocalDateTime startOfDay = createdAt.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);

        // 발신자를 ID로 찾기
        User sender = userRepository.findById(requestDTO.getSender())
                .orElseThrow(() -> new ResourceNotFoundException("발신자를 찾을 수 없습니다."));

        // 쿼리 수정: 현재 사용자가 받은 선물을 조회
        Optional<Gift> giftOptional = giftRepository.findByTargetAndUserAndCreatedAtBetween(user, sender, startOfDay, endOfDay);
        Gift gift = giftOptional.orElseThrow(() -> new ResourceNotFoundException("해당 날짜에 받은 선물을 찾을 수 없습니다."));

        Flower flower = gift.getFlower();

        return LetterViewResponseDTO.LetterViewDetailDTO.builder()
                .letter_id(gift.getId()) // 편지 ID
                .user_id(user.getId()) // 받은 사람 ID(로그인한 사용자의 아이디, 나)
                .sender_id(gift.getUser().getId()) // 보낸 사람 ID(gift 테이블의 user_id)
                .bouquet_id(flower.getId()) // 꽃다발 ID
                .created_at(gift.getCreatedAt()) // 편지 작성 날짜
                .bouquet_imageUrl(flower.getBouquetUrl()) // 꽃 사진 URL
                .bouquet_name(flower.getFlowerName()) // 꽃 이름
                .bouquet_meaning(flower.getFlowerMeaning()) // 꽃 감정
                .content(gift.getMessage()) // 편지 내용
                .build();
    }
}

/*
@Service
@RequiredArgsConstructor
public class LetterViewService {

    private final GiftRepository giftRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public LetterViewResponseDTO.LetterViewDetailDTO viewLetter(String token, LetterViewRequestDTO.LetterViewDTO requestDTO) {
        String jwtToken = token.substring(7); // "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            throw new RuntimeException("Invalid token");
        }

        */
/*User user = userRepository.findByUid(username)
                .orElseThrow(() -> new RuntimeException("User not found"));*//*

        Optional<User> userOptional = userRepository.findByUid(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();

        LocalDateTime createdAt;
        try {
            createdAt = LocalDateTime.parse(requestDTO.getDate(), DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 날짜 형식입니다. 올바른 형식은 yyyy-MM-dd'T'HH:mm:ss입니다.");
        }

        LocalDateTime startOfDay = createdAt.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);

        // Gift 조회
        // Gift 조회
        User targetUser = userRepository.findById(requestDTO.getSender())
                .orElseThrow(() -> new ResourceNotFoundException("발신자를 찾을 수 없습니다."));

        Optional<Gift> giftOptional = giftRepository.findByUserIdAndTargetAndCreatedAtBetween(user, targetUser, startOfDay, endOfDay);
        Gift gift = giftOptional.orElseThrow(() -> new ResourceNotFoundException("해당 날짜에 받은 선물을 찾을 수 없습니다."));

        */
/*Optional<Gift> giftOptional = giftRepository.findByUserIdAndTargetAndCreatedAtBetween(requestDTO.getSender(), user.getId(), startOfDay, endOfDay);
        Gift gift = giftOptional.orElseThrow(() -> new ResourceNotFoundException("해당 날짜에 작성된 다이어리를 찾을 수 없습니다."));*//*


        // 관련된 꽃 정보 조회
        Flower flower = gift.getFlower(); // 다이어리에서 꽃 정보 가져오기

        // LetterViewDetailDTO 반환
        return LetterViewResponseDTO.LetterViewDetailDTO.builder()
                .letter_id(gift.getId()) // 편지 ID
                .user_id(user.getId()) // 사용자 ID
                .bouquet_id(flower.getId()) // 꽃다발 ID
                .created_at(gift.getCreatedAt()) // 편지 작성 날짜
                .bouquet_imageUrl(flower.getBouquetUrl()) // 꽃 사진 URL
                .bouquet_name(flower.getFlowerName()) // 꽃 이름
                .bouquet_meaning(flower.getFlowerMeaning()) // 꽃 감정
                .content(gift.getMessage()) // 편지 내용
                .build();
    }
}*/
