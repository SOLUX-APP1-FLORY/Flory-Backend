package flory.FloryServer.repository;

import flory.FloryServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUid(String uid); // 로그인한 사용자 조회
}

