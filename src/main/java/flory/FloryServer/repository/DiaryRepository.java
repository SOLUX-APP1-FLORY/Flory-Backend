package flory.FloryServer.repository;

import flory.FloryServer.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    // 다이어리 ID와 작성 시간으로 다이어리 조회
    Optional<Diary> findByUserIdAndCreatedAt(Long userId, LocalDateTime createdAt);
}