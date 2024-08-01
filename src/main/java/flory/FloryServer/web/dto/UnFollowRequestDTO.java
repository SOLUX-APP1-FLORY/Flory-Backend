package flory.FloryServer.web.dto;
import lombok.Data;

@Data
public class UnFollowRequestDTO {
//    private Long userId;
//    private Long targetUserId;
    private String userNickname;
    private String targetUserNickname;
}