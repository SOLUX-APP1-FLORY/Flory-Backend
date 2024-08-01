package flory.FloryServer.service.DiaryService;

import flory.FloryServer.domain.Diary;
import flory.FloryServer.domain.Flower;
import flory.FloryServer.domain.User;
import flory.FloryServer.repository.DiaryRepository;
import flory.FloryServer.repository.FlowerRepository;
import flory.FloryServer.repository.UserRepository;
import flory.FloryServer.web.dto.DiaryModifyRequestDTO;
import flory.FloryServer.web.dto.DiaryModifyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import flory.FloryServer.login.jwt.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DiaryModifyService {
    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private FlowerRepository flowerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public DiaryModifyResponseDTO modifyDiary(String token, DiaryModifyRequestDTO.ModifyDTO requestDTO) {
        String jwtToken = token.substring(7); // 토큰에서 "Bearer " 부분 제거
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            throw new RuntimeException("Invalid token");
        }

        Optional<User> userOptional = userRepository.findByUid(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Diary diary = diaryRepository.findById(requestDTO.getDiary_id())
                .orElseThrow(() -> new RuntimeException("Diary Not Found"));

        Optional<Flower> flowerOptional = flowerRepository.findByFlowerNameInFlowerRange(requestDTO.getFlower());
        if (flowerOptional.isEmpty()) {
            throw new RuntimeException("Flower not found");
        }

        Flower flower = flowerOptional.get();

        diary.setTitle(requestDTO.getTitle());
        diary.setContent(requestDTO.getContent());
        diary.setFlower(flower);

        Diary updatedDiary = diaryRepository.save(diary);

        DiaryModifyResponseDTO.ModifyResultDTO modifyResult = DiaryModifyResponseDTO.ModifyResultDTO.builder()
                .diary_id(updatedDiary.getId())
                .flower_id(flower.getId())
                .flower(flower.getFlowerName())
                .flower_meaning(flower.getFlowerMeaning())
                .title(updatedDiary.getTitle())
                .content(updatedDiary.getContent())
                .createdAt(updatedDiary.getCreatedAt())
                .updatedAt(updatedDiary.getUpdatedAt())
                .build();

        return DiaryModifyResponseDTO.builder()
                .isSuccess(true)
                .code("COMMON200")
                .message("성공입니다.")
                .result(modifyResult)
                .build();
    }
}
