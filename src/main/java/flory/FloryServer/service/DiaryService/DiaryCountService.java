package flory.FloryServer.service.DiaryService;

import flory.FloryServer.domain.User;
import flory.FloryServer.login.jwt.JwtUtil;
import flory.FloryServer.repository.DiaryRepository;
import flory.FloryServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryCountService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public int getDiaryCountForCurrentMonth(String token) {
        // 토큰에서 "Bearer " 부분 제거
        String jwtToken = token.substring(7);
        String username = jwtUtil.getUidFromToken(jwtToken);

        // 토큰 검증
        if (!jwtUtil.validateToken(jwtToken)) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        // 사용자 조회
        User user = userRepository.findByUid(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 현재 연도와 월 구하기
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        // 작성한 날짜 조회
        List<LocalDate> distinctDates = diaryRepository.findDistinctDatesByUserIdAndMonth(user.getId(), currentYear, currentMonth);

        // 중복 제거된 날짜 개수 반환
        return distinctDates.size();
    }
}
