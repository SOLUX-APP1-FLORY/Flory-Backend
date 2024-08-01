package flory.FloryServer.repository;

import flory.FloryServer.domain.Card;
import flory.FloryServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findById(int id);
}
