package flory.FloryServer.domain;

import flory.FloryServer.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Gift extends BaseEntity {
    // Persistent Entity should have primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // JPA에서 스트링 컬럼의 VARCHAR 255 제한 해제
    @Column(columnDefinition = "TEXT")
    private String message;

    // <외래키> userId, userNickname, receiverId, flowerId(부케), cardId
    @ManyToOne(fetch = FetchType.LAZY)  // N:1에서 N에 해당하는 엔티티가 1에 해당하는 엔티티와 매핑
    @JoinColumn(name = "sender_id")     // 실제 데이터베이스에서 해당하는 칼럼명
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nickname")
    @Column(nullable = false, length = 20)
    private User sender; // ?같은 인스턴스 다른 칼럼

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bouquet_id")
    private Flower bouquet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

}
