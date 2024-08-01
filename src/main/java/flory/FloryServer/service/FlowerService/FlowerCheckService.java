package flory.FloryServer.service.FlowerService;

import flory.FloryServer.domain.Diary;
import flory.FloryServer.domain.Flower;
import flory.FloryServer.repository.DiaryRepository;
import flory.FloryServer.repository.FlowerRepository;
import flory.FloryServer.web.dto.FlowerCheckResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class FlowerCheckService {

    private final DiaryRepository diaryRepository;
//    private final FlowerRepository flowerRepository;

    @Autowired
    public FlowerCheckService(DiaryRepository diaryRepository) { //, FlowerRepository flowerRepository
        this.diaryRepository = diaryRepository;
//        this.flowerRepository = flowerRepository;
    }

    public Optional<FlowerCheckResponseDTO> getSelectedFlower(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        // 날짜에 해당하는 다이어리 조회
        // 서비스 클래스에서 입력된 날짜를 LocalDateTime 범위로 변환하고 이를 사용하여 저장소 메소드를 호출

        Optional<Diary> diaryOptional = diaryRepository.findByCreatedAtBetween(startOfDay, endOfDay);

        if (diaryOptional.isPresent()) {
            Diary diary = diaryOptional.get();
            Flower flower = diary.getFlower();
            FlowerCheckResponseDTO.ChooseResultDTO chooseResultDTO = FlowerCheckResponseDTO.ChooseResultDTO.builder()
                    .flowerId(flower.getId())
                    .flowerName(flower.getFlowerName())
                    .flowerMeaning(flower.getFlowerMeaning())
                    .flowerUrl(flower.getFlowerUrl())
                    .build();

            return Optional.of(new FlowerCheckResponseDTO(chooseResultDTO));
        } else {
            return Optional.empty();
        }
    }
}
