package flory.FloryServer.service.DiaryService;

import flory.FloryServer.apiPayload.exception.ResourceNotFoundException;
import flory.FloryServer.domain.Diary;
import flory.FloryServer.domain.Flower;
import flory.FloryServer.repository.DiaryRepository; // 다이어리 리포지토리 추가
import flory.FloryServer.web.dto.DiaryViewRequestDTO;
import flory.FloryServer.web.dto.DiaryViewResponseDTO;
import lombok.RequiredArgsConstructor;


import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Service
@RequiredArgsConstructor
public class DiaryViewService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Transactional
    public DiaryViewResponseDTO.DiaryViewDetailDTO viewDiary(Long id, DiaryViewRequestDTO.DiaryViewDTO requestDTO) {
        // 입력값 검증
        /*if (requestDTO.getUser_id() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용자 ID는 필수입니다.");
        }
        if (requestDTO.getDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "날짜는 필수입니다.");
        }*/

        LocalDateTime createdAt;

        // 작성 날짜를 가져옵니다.
        // LocalDateTime createdAt = LocalDateTime.parse(requestDTO.getDate());
        // LocalDateTime createdAt = LocalDateTime.parse(requestDTO.getDate(), DateTimeFormatter.ISO_DATE_TIME);
        try {
            createdAt = LocalDateTime.parse(requestDTO.getDate(), DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 날짜 형식입니다. 올바른 형식은 yyyy-MM-dd'T'HH:mm:ss입니다.");
        }

        // 디버깅 로그 출력
        /*System.out.println("Requested User ID: " + requestDTO.getUser_id());
        System.out.println("Requested Date: " + createdAt);*/

        // 작성 날짜와 사용자 ID로 다이어리 조회
        Diary diary = diaryRepository.findByUserIdAndCreatedAt(requestDTO.getUser_id(), createdAt)
                .orElseThrow(() -> new ResourceNotFoundException("해당 날짜에 작성된 다이어리를 찾을 수 없습니다."));

        // 관련된 꽃 정보 조회
        Flower flower = diary.getFlower(); // 다이어리에서 꽃 정보 가져오기

        return DiaryViewResponseDTO.DiaryViewDetailDTO.builder()
                .diary_id(diary.getId()) // 다이어리 ID
                .title(diary.getTitle()) // 일기 제목
                .content(diary.getContent()) // 일기 내용
                .flower_id(flower.getId()) // 꽃 ID
                .flower_name(flower.getFlowerName()) // 꽃 이름
                .flower_imageUrl(flower.getFlowerUrl()) // 꽃 이미지 URL (가정)
                .build();
    }
}