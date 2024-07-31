package flory.FloryServer.domain;

import flory.FloryServer.domain.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Flower {
    @Id
    private int id; //꽃 아이디

    @Getter
    @Setter
    private String flower_name;

    @Getter
    @Setter
    private String flower_meaning;

    @Getter
    @Setter
    private String flower_url;

    @Getter
    @Setter
    private String bouquet_url;

    @OneToMany(mappedBy = "flower", cascade = CascadeType.ALL)
    private List<Diary> DiaryList = new ArrayList<>();

    @OneToMany(mappedBy = "flower", cascade = CascadeType.ALL)
    private List<Gift> GiftList = new ArrayList<>();
}