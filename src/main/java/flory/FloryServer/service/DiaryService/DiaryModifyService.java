package flory.FloryServer.service.DiaryService;

import flory.FloryServer.domain.Diary;
import flory.FloryServer.domain.Flower;
import flory.FloryServer.repository.DiaryRepository;
import flory.FloryServer.repository.FlowerRepository;
import flory.FloryServer.web.dto.DiaryModifyRequestDTO;
import flory.FloryServer.web.dto.DiaryModifyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DiaryModifyService {
    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private FlowerRepository flowerRepository;

    public Optional<Diary> getDiaryById(Long id) {
        return diaryRepository.findById(id);
    }

    @Transactional
    public DiaryModifyResponseDTO.ModifyResultDTO modifyDiary(Long id, DiaryModifyRequestDTO.ModifyDTO requestDTO) {

        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Diary Not Found")));

        Flower flower = flowerRepository.findById(Integer.parseInt(requestDTO.getFlower_id()))
                .orElseThrow(() -> new RuntimeException("Flower Not Found"));

        diary.setTitle(requestDTO.getTitle());
        diary.setContent(requestDTO.getContent());
        diary.setFlower(flower);

        Diary updatedDiary = diaryRepository.save(diary);

        return DiaryModifyResponseDTO.ModifyResultDTO.builder()
                .diary_id(diary.getId())
                .flower_id(flower.getId())
                .flower(flower.getFlowerName())
                .flower_meaning(flower.getFlowerMeaning())
                .title(diary.getTitle())


                .build();
    }
}
