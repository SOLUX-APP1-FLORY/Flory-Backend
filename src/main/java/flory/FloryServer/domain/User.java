package flory.FloryServer.domain;


import flory.FloryServer.domain.base.BaseEntity;
import flory.FloryServer.domain.enums.Gender;
import flory.FloryServer.domain.mapping.Relationship;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
// @IdClass(UserId.class)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Id
    // 복합키?
    @Column(length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Diary> DiaryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Gift> GiftList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Relationship> RelationshipList = new ArrayList<>();


    public void updateProfile(String nickname, Gender gender) {
        this.nickname = nickname;
        this.gender = gender;
    }
}