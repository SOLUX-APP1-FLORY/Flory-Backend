package flory.FloryServer.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class DiaryListResponseDTO {
    private boolean isSuccess;
    private String code;
    private String message;
    private DiaryResultDTO result;

    @Getter
    @Builder
    public static class DiaryResultDTO {
        private String flowerUrl;
        private LocalDateTime createdAt;
    }
}
