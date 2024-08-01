package flory.FloryServer.apiPayload.exception;

public class RelationException {
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public class AlreadyFollowingException extends RuntimeException {
        public AlreadyFollowingException(String message) {
            super(message);
        }
    }
}
