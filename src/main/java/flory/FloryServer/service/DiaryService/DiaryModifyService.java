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
    public DiaryModifyResponseDTO.DiaryModifyResultDTO modifyDiary(String token, DiaryModifyRequestDTO.DiaryModifyDTO requestDTO) {
        String jwtToken = token.substring(7); // Remove "Bearer " part from the token
        String username = jwtUtil.getUidFromToken(jwtToken);

        if (!jwtUtil.validateToken(jwtToken)) {
            throw new RuntimeException("Invalid token");
        }

        User user = userRepository.findByUid(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Diary diary = diaryRepository.findById(requestDTO.getDiary_id())
                .orElseThrow(() -> new RuntimeException("Diary not found"));

        Flower flower = flowerRepository.findByFlowerNameInFlowerRange(requestDTO.getFlower())
                .orElseThrow(() -> new RuntimeException("Flower not found"));

        diary.setTitle(requestDTO.getTitle());
        diary.setContent(requestDTO.getContent());
        diary.setFlower(flower);

        Diary updatedDiary = diaryRepository.save(diary);

        return DiaryModifyResponseDTO.DiaryModifyResultDTO.builder()
                .diary_id(updatedDiary.getId())
                .flower_id(flower.getId())
                .flower(flower.getFlowerName())
                .flower_meaning(flower.getFlowerMeaning())
                .title(updatedDiary.getTitle())
                .content(updatedDiary.getContent())
                .createdAt(updatedDiary.getCreatedAt())
                .updatedAt(updatedDiary.getUpdatedAt())
                .build();
    }

}
