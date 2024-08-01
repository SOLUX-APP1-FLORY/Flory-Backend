package flory.FloryServer.service.FlowerService;

import flory.FloryServer.domain.Diary;
import flory.FloryServer.domain.Flower;
import flory.FloryServer.repository.DiaryRepository;
import flory.FloryServer.repository.FlowerRepository;
import flory.FloryServer.web.dto.FlowerCheckResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        // 날짜에 해당하는 다이어리 조회
        Optional<Diary> diary = diaryRepository.findByCreatedAt(date);

        if (diary.isPresent()) {
            // 다이어리 엔티티에서 꽃 정보를 가져옴
            Flower flower = diary.get().getFlower();

            if (flower != null) {
                // ChooseResultDTO 인스턴스 생성
                FlowerCheckResponseDTO.ChooseResultDTO chooseResult =
                        FlowerCheckResponseDTO.ChooseResultDTO.builder()
                                .flowerId(flower.getId())
                                .flowerName(flower.getFlowerName())
                                .flowerMeaning(flower.getFlowerMeaning())
                                .flowerUrl(flower.getFlowerUrl())
                                .build();

                // ChooseResultDTO를 포함하는 FlowerCheckResponseDTO 인스턴스 생성
                FlowerCheckResponseDTO response = new FlowerCheckResponseDTO(chooseResult);

                return Optional.of(response);
//            // 꽃 정보 조회
//            Optional<Flower> flower = flowerRepository.findById(flowerId);
//            if (flower.isPresent()) {
//                Flower f = flower.get();
//                return Optional.of(new FlowerCheckResponseDTO(
//                        f.getId(),
//                        f.getName(),
//                        f.getLanguage(),
//                        f.getImageUrl()
//                ));
            }
        }

        // 해당 날짜에 다이어리가 없거나 꽃 정보가 없는 경우 빈 Optional 반환
        return Optional.empty();
    }
}
