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
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            @JoinColumn(name = "user_nickname", referencedColumnName = "nickname")
    })
    private User user;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nickname")
    private User userNickname;  // ?변수명*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighbor_id")
    private Neighbor neighbor;
}