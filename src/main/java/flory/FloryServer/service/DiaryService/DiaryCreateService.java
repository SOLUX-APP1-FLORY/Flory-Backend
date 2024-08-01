package flory.FloryServer.service.DiaryService;

import flory.FloryServer.domain.Diary;
import flory.FloryServer.domain.Flower;
import flory.FloryServer.domain.User;
import flory.FloryServer.repository.DiaryRepository;
import flory.FloryServer.repository.FlowerRepository;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.DiaryCreateRequestDTO;
import flory.FloryServer.web.dto.DiaryCreateResponseDTO;
import flory.FloryServer.login.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DiaryCreateService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public DiaryCreateResponseDTO createDiary(String token, DiaryCreateRequestDTO.CreateDTO requestDTO) {
        String jwtToken = token.substring(7); // 토큰에서 "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            return DiaryCreateResponseDTO.builder()
                    .isSuccess(false)
                    .code("TOKEN_INVALID")
                    .message("Invalid token")
                    .result(new DiaryCreateResponseDTO.CreateResultDTO("Invalid token"))
                    .build();
        }

        Optional<User> userOptional = userRepository.findByUid(username);
        if (userOptional.isEmpty()) {
            return DiaryCreateResponseDTO.builder()
                    .isSuccess(false)
                    .code("USER_NOT_FOUND")
                    .message("User not found")
                    .result(new DiaryCreateResponseDTO.CreateResultDTO("User not found"))
                    .build();
        }
        User user = userOptional.get();

        Optional<Flower> flowerOptional = flowerRepository.findByFlowerNameInFlowerRange(requestDTO.getFlowerName());
        if (flowerOptional.isEmpty()) {
            return DiaryCreateResponseDTO.builder()
                    .isSuccess(false)
                    .code("FLOWER_NOT_FOUND")
                    .message("Flower not found")
                    .result(new DiaryCreateResponseDTO.CreateResultDTO("Flower not found"))
                    .build();
        }
        Flower flower = flowerOptional.get();

        Diary diary = Diary.builder()
                .user(user)
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .flower(flower)
                .build();

        diaryRepository.save(diary);

        return DiaryCreateResponseDTO.builder()
                .isSuccess(true)
                .code("COMMON200")
                .message("Diary created successfully")
                .result(new DiaryCreateResponseDTO.CreateResultDTO("다이어리가 성공적으로 생성되었습니다."))
                .build();
    }
}
