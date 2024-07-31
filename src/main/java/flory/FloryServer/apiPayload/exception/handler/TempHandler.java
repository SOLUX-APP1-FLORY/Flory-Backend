package flory.FloryServer.apiPayload.exception.handler;

import flory.FloryServer.apiPayload.code.BaseErrorCode;
import flory.FloryServer.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
