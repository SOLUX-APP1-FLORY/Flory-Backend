package flory.FloryServer.repository;

import flory.FloryServer.domain.Card;
import flory.FloryServer.domain.Gift;
import flory.FloryServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    List<Gift> findByTarget(User target);
    // 현재 사용자(target)와 발신자(user)를 기준으로 선물을 조회하는 메서드
    Optional<Gift> findByTargetAndUserAndCreatedAtBetween(User target, User user, LocalDateTime startDateTime, LocalDateTime endDateTime);
}