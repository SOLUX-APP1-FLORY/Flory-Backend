package flory.FloryServer.web.dto;

import flory.FloryServer.domain.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;

public class DiaryModifyRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyDTO{
        private Long diary_id;
        private String title;
        private String content;
        private String flower_id;
    }

}
