package flory.FloryServer.domain;

import lombok.*;
import flory.FloryServer.domain.common.BaseEntity;
import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Neighbor extends BaseEntity {

    @Id
    // 기본 키 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "neighbor", cascade = CascadeType.ALL)
    private List<Relationship> relationshipList = new ArrayList<>();
}
