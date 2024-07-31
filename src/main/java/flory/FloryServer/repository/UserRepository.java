package flory.FloryServer.repository;

import flory.FloryServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 중복 가입 방지와 존재여부를 파악하는 메서드를 추가
    Optional<User> findByUid(String Uid);
    boolean existsByUid(String Uid);

}
