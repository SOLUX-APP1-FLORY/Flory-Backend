package flory.FloryServer.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BouquetInfoResponseDTO {
    private boolean isSuccess;
    private String code;
    private String message;
    private List<Gift> result;

    @Getter
    @Builder
    public static class Gift {
        private Long id;
        private String sender;
        private int bouquetId;
    }
}
