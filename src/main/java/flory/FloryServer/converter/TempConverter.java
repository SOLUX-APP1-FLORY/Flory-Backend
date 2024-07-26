package flory.FloryServer.converter;

import flory.FloryServer.web.dto.TempResponse;

public class TempConverter {

    public static TempResponse.TempTestDTO toTempDTO(){
        return TempResponse.TempTestDTO.builder()
                .testString("This is Test!!!")
                .build();
    }
}
