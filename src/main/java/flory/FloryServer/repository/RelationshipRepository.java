package flory.FloryServer.repository;

import flory.FloryServer.domain.User;
import flory.FloryServer.domain.mapping.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    Optional<Relationship> findByUserAndTargetUser(User user, User targetUser);

    List<Relationship> findAllByUser(User user);
}
