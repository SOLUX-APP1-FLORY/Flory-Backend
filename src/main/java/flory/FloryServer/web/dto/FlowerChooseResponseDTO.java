package flory.FloryServer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FlowerChooseResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChooseResultDTO{
        private int flowerId;
        private String flowerName;
        private String flowerMeaning;
        private String flowerUrl;
    }
}
