package flory.FloryServer.repository;

import flory.FloryServer.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    Optional<Diary> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    // List<Diary> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate); // endDate까지 포함하는 diary 항목이 반환됨.

    // 다이어리 ID와 작성 시간으로 다이어리 조회
    Optional<Diary> findByCreatedAt(LocalDateTime createdAt);

    Optional<Diary> findByUserId (Long userId);
    List<Diary> findByUserUid (String uid);
    List<Diary> findByUserUidAndCreatedAtBetween (String uid, LocalDateTime startDate, LocalDateTime endDate);

    // 사용자 ID와 작성 날짜로 다이어리 조회 (LocalDate로 수정)
    // List<Diary> findAllByUserIdAndCreatedAtBetween(Long userId, LocalDate startOfDay, LocalDate endOfDay);
    List<Diary> findAllByUserIdAndCreatedAtBetween (Long userId, LocalDateTime startDate, LocalDateTime endDate);

    // 특정 사용자가 이번 달에 작성한 일기의 개수를 가져오는 메서드
    @Query(value = "SELECT DISTINCT DATE(created_at) FROM diary WHERE user_id = :userId AND YEAR(created_at) = :year AND MONTH(created_at) = :month", nativeQuery = true)
    List<java.sql.Date> findDistinctDatesByUserIdAndMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    default List<LocalDate> findDistinctLocalDatesByUserIdAndMonth(Long userId, int year, int month) {
        List<java.sql.Date> dates = findDistinctDatesByUserIdAndMonth(userId, year, month);
        return dates.stream()
                .map(java.sql.Date::toLocalDate)
                .collect(Collectors.toList());
    }
}