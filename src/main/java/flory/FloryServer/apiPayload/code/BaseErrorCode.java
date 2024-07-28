package flory.FloryServer.apiPayload.code;

public interface BaseErrorCode {

    public ErrorReasonDTO getReason();  //ErrorReason-> ErrorReasonDTO

    public ErrorReasonDTO getReasonHttpStatus();
}
