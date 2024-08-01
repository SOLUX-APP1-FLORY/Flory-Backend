package flory.FloryServer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowerChooseResponseDTO {
    private boolean isSuccess;
    private String code;
    private String message;
    private ChooseResultDTO result;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChooseResultDTO {
        private int flowerId;
        private String flowerName;
        private String flowerMeaning;
        private String flowerUrl;
    }
}
