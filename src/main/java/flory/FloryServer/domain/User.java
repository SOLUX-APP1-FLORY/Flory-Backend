package flory.FloryServer.domain;


import flory.FloryServer.domain.base.BaseEntity;
import flory.FloryServer.domain.enums.Gender;
import flory.FloryServer.domain.mapping.Relationship;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false, length = 20)
    private String uid;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String email;

//    //@Column(nullable = false, length = 20)
//    private String phoneNum;

    //@Column(nullable = false, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    //@Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private Gender gender;

    //@Column(nullable = false)
    private LocalDate birthdate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Diary> DiaryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Gift> GiftList = new ArrayList<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Relationship> RelationshipList = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Relationship> following = new HashSet<>();

    @OneToMany(mappedBy = "targetUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Relationship> followers = new HashSet<>();


    public void updateProfile(String nickname, Gender gender) {
        this.nickname = nickname;
        this.gender = gender;
    }
}