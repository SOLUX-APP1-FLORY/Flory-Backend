package flory.FloryServer.domain;

import java.io.Serializable;
import java.util.Objects;

public class UserId implements Serializable {
    private Long id;
    private String nickname;

    public UserId() {}

    public UserId(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(id, userId.id) &&
                Objects.equals(nickname, userId.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname);
    }
}
