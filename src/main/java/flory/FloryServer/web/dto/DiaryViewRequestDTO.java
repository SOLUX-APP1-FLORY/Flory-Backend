package flory.FloryServer.web.dto;

import lombok.Getter;
import lombok.Setter;

public class DiaryViewRequestDTO {
    @Getter
    @Setter
    public static class DiaryViewDTO{
        // private Long user_id; // 사용자 ID
        private String date; // 일기 작성 날짜 (예: "2024-07-31T00:00:00")
    }
}