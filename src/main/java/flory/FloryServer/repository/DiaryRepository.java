package flory.FloryServer.repository;

import flory.FloryServer.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByUserId(Long userId);
    List<Diary> findByUserUid(String uid);
    List<Diary> findByUserUidAndCreatedAtBetween(String uid, LocalDateTime startDate, LocalDateTime endDate);
    // 다이어리 ID와 작성 시간으로 다이어리 조회
    Optional<Diary> findByCreatedAt(LocalDateTime createdAt);
}

