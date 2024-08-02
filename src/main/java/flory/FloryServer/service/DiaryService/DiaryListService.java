package flory.FloryServer.service.DiaryService;

import flory.FloryServer.domain.Diary;
import flory.FloryServer.repository.DiaryRepository;
import flory.FloryServer.web.dto.DiaryListResponseDTO;
import flory.FloryServer.login.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryListService {
    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public DiaryListResponseDTO getDiaries(String token, int year, int month, int day) {
        String jwtToken = token.substring(7); // "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            return DiaryListResponseDTO.builder()
                    .isSuccess(false)
                    .code("TOKEN_INVALID")
                    .message("Invalid token")
                    .result(null)
                    .build();
        }

        // 주어진 연도, 월, 일로 LocalDateTime 객체 생성
        LocalDateTime startDate = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(year, month, day, 23, 59, 59);

        // 해당 날짜 범위 내의 일기 조회
        List<Diary> diaries = diaryRepository.findByUserUidAndCreatedAtBetween(username, startDate, endDate);

        Diary firstDiary = diaries.isEmpty() ? null : diaries.get(0);

        // DTO로 변환
        DiaryListResponseDTO.DiaryResultDTO dtoDiary = firstDiary == null
                ? DiaryListResponseDTO.DiaryResultDTO.builder()
                        .flowerUrl(null)
                        .createdAt(null)
                        .build()
                : DiaryListResponseDTO.DiaryResultDTO.builder()
                .flowerUrl(firstDiary.getFlower() != null ? firstDiary.getFlower().getFlowerUrl():null)
                .createdAt(firstDiary.getCreatedAt())
                .build();

        return DiaryListResponseDTO.builder()
                .isSuccess(true)
                .code("COMMON200")
                .message("성공입니다.")
                .result(dtoDiary)
                .build();
    }
}
