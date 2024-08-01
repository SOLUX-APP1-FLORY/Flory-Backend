package flory.FloryServer.service.FlowerService;

import flory.FloryServer.domain.Flower;
import flory.FloryServer.repository.FlowerRepository;
import flory.FloryServer.web.dto.FlowerChooseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FlowerChooseService {
    @Autowired
    private FlowerRepository flowerRepository;

    @Transactional
    public FlowerChooseResponseDTO.ChooseResultDTO chooseFlower(String flowerName) {
        Flower flower = flowerRepository.findByFlowerNameInFlowerRange(flowerName)
                .orElseThrow(() -> new RuntimeException("Flower Not Found"));

        return FlowerChooseResponseDTO.ChooseResultDTO.builder()
                .flowerId(flower.getId())
                .flowerName(flower.getFlowerName())
                .flowerMeaning(flower.getFlowerMeaning())
                .flowerUrl(flower.getFlowerUrl())
                .build();
    }
}
