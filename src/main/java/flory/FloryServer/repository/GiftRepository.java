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

}