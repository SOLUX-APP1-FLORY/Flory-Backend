package flory.FloryServer.repository;

import flory.FloryServer.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

//    Optional<Diary> findByCreatedAt(LocalDate date);
     Optional<Diary> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end); // 날짜 범위 조회하려면? -> 시간 정보를 포함하는 타입
    // List<Diary> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate); // endDate까지 포함하는 diary 항목이 반환됨.

    // 다이어리 ID와 작성 시간으로 다이어리 조회
    Optional<Diary> findByCreatedAt(LocalDateTime createdAt);
}
