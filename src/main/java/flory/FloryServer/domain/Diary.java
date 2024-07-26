package flory.FloryServer.domain;

import flory.FloryServer.domain.base.BaseEntity;
import flory.FloryServer.domain.enums.FlowerMeaning;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Diary extends BaseEntity {

    @Id
    // 기본 키 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer date;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlowerMeaning flowerMeaning;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nickname")
    private User userNickname;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flower_id")
    private Flower flower;
}
