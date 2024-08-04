package flory.FloryServer.web.dto;

import lombok.*;

public class DiaryModifyRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiaryModifyDTO {
        private Long diary_id;
        private String title;
        private String content;
        private String flower;
    }

}
