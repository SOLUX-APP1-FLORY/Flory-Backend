package flory.FloryServer.repository;

import flory.FloryServer.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByUserId(Long userId);
    List<Diary> findByUserUid(String uid);
    List<Diary> findByUserUidAndCreatedAtBetween(String uid, LocalDateTime startDate, LocalDateTime endDate);
}
