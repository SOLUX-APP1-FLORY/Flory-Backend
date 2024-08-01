package flory.FloryServer.web.dto;
import lombok.Data;

@Data
public class FollowRequestDTO {
    private Long userId;
    private Long targetUserId;
}
