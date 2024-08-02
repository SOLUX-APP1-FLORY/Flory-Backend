package flory.FloryServer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

public class LetterViewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LetterViewDetailDTO {
        private Long letter_id;// 편지 ID
        private Long user_id; // 사용자(내 아이디)
        private Long sender_id; // 편지를 보낸 사람 아이디
        private int bouquet_id; // 꽃다발 ID
        private LocalDateTime created_at;// 편지 작성 날짜
        private String bouquet_imageUrl;// 받은 꽃다발 사진 URL
        private String bouquet_name;// 받은 꽃다발 이름
        private String bouquet_meaning;// 받은 꽃다발 감정
        private String content;// 편지 내용
    }
}
