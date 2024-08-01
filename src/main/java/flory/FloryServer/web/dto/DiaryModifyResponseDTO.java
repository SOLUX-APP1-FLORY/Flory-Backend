package flory.FloryServer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryModifyResponseDTO {
    private boolean isSuccess;
    private String code;
    private String message;
    private ModifyResultDTO result;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyResultDTO {
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
