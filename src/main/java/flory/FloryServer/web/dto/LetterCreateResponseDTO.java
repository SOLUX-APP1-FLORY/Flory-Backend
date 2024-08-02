package flory.FloryServer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LetterCreateResponseDTO {
    private boolean isSuccess;
    private String code;
    private String message;
    private LetterCreateResponseDTO.CreateResultDTO result;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDTO {
        private String message;
    }
}
