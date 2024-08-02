package flory.FloryServer.web.dto;

import lombok.Getter;
import lombok.Setter;

public class LetterViewRequestDTO {
    @Getter
    @Setter
    public static class LetterViewDTO {
        private String bouquet_name;
        private Long giftId; //선물 준 사람
    }
}
