package flory.FloryServer.repository;

import flory.FloryServer.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

     Optional<Diary> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    // List<Diary> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate); // endDate까지 포함하는 diary 항목이 반환됨.

    // 다이어리 ID와 작성 시간으로 다이어리 조회
    Optional<Diary> findByCreatedAt(LocalDateTime createdAt);

    // 특정 사용자가 이번 달에 작성한 일기의 개수를 가져오는 메서드
    @Query("SELECT COUNT(d) FROM Diary d WHERE d.user.id = :userId AND YEAR(d.createdAt) = :year AND MONTH(d.createdAt) = :month")
    int countByUserIdAndMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);
}
