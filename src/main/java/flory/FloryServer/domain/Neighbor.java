package flory.FloryServer.domain;

import flory.FloryServer.domain.base.BaseEntity;
import flory.FloryServer.domain.mapping.Relationship;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

