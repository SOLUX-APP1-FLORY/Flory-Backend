package flory.FloryServer.domain;

import flory.FloryServer.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Neighbor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성
    private Long id;

    @OneToMany(mappedBy = "neighbor", cascade = CascadeType.ALL)
    private List<Relationship> relationshipList = new ArrayList<>();
}
