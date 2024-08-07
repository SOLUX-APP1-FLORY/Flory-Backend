package flory.FloryServer.repository;

import flory.FloryServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(int id); // 로그인한 사용자 조회

    Optional<User> findByUid(String userId);

    Optional<User> findByNickname(String nickname); // 이웃 관련

    List<User> findByNicknameContaining(String nickname);

}

