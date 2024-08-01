package flory.FloryServer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LetterCreateRequestDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LetterCreateDTO {
        private String flowerName;
        private Integer card_id;
        private Integer target_id;
        private String content;
    }
}
