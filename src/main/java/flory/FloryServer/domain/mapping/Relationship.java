package flory.FloryServer.domain.mapping;

import flory.FloryServer.domain.Neighbor;
import flory.FloryServer.domain.User;
import flory.FloryServer.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Relationship extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumns({
//            @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            @JoinColumn(name = "user_nickname", referencedColumnName = "nickname")
//    })
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighbor_id")
    private Neighbor neighbor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id", nullable = false)
    private User targetUser;

    @Builder
    public Relationship(User user, User target) {
        this.user = user;
        this.targetUser = target;
    }
}