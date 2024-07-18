package flory.FloryServer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    private final UserRepository memberRepository;

    @Autowired
    public UserService(UserRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void saveNickname(String nickname) {
        User newMember = new User();
        newMember.setNickname(nickname);
        memberRepository.save(newMember);
    }
}
