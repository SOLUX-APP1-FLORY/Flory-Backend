package flory.FloryServer.repository;

import flory.FloryServer.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    // 다이어리 ID와 작성 시간으로 다이어리 조회
    // Optional<Diary> findByUserNameAndCreatedAt(String username, LocalDateTime createdAt);
    // Optional<Diary> findByCreatedAt(LocalDateTime createdAt);
    // Optional<Diary> findByUserIdAndCreatedAt(Long userId, LocalDateTime createdAt);
    // 사용자 ID와 작성 날짜로 다이어리 조회
    // Optional<Diary> findByUserIdAndCreatedAtBetween(Long userId, LocalDateTime startOfDay, LocalDateTime endOfDay);
    List<Diary> findAllByUserIdAndCreatedAtBetween(Long userId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}