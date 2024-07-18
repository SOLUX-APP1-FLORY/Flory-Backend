package domain;

import domain.common.BaseEntity;
import domain.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@IdClass(UserId.class)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String password;

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<Diary> DiaryList = new ArrayList<>();

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<Gift> GiftList = new ArrayList<>();

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<Relationship> GiftList = new ArrayList<>();

    public void updateProfile(String nickname, Gender gender) {
        this.nickname = nickname;
        this.gender = gender;
    }

}
