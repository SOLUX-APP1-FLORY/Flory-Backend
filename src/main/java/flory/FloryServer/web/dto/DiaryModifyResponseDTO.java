package flory.FloryServer.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DiaryModifyResponseDTO {
    private boolean isSuccess;
    private String code;
    private String message;

    @Getter
    @Builder
    public static class DiaryModifyResultDTO {
        private Long diary_id;
        private int flower_id;
        private String flower;
        private String flower_meaning;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
