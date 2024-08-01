package flory.FloryServer.domain;

import flory.FloryServer.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Diary extends BaseEntity {

    @Id
    // 기본 키 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nickname")
    private User userNickname;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flowerId")
    private Flower flower;

}