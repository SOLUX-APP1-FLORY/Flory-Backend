package flory.FloryServer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class DiaryViewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiaryViewDetailDTO {
        private Long diary_id;// 다이어리의 고유 ID
        private int flower_id;
        private LocalDateTime created_at;// 일기 작성 날짜
        private String flower_name;// 사용자가 선택한 꽃 이름
        private String flower_meaning;// 사용자가 선택한 감정
        private String title;
        private String content;// 일기 내용
        private String flower_imageUrl;// 선택한 꽃의 사진 URL
    }
}