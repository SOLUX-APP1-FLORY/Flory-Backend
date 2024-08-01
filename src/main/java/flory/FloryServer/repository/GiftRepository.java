package flory.FloryServer.repository;

import flory.FloryServer.domain.Gift;
import flory.FloryServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    List<Gift> findByTarget(User target);
}

