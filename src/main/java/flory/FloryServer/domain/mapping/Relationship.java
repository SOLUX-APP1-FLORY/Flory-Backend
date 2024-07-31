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

    @ManyToOne(fetch = FetchType.LAZY)  // N:1에서 N에 해당하는 엔티티가 1에 해당하는 엔티티와 매핑
    @JoinColumn(name = "user_id")       // 실제 데이터베이스에서 해당하는 칼럼명
    private User user;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nickname")
    private User userNickname;  // ?변수명*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighbor_id")
    private Neighbor neighbor;
}