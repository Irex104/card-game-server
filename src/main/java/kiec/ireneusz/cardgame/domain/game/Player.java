package kiec.ireneusz.cardgame.domain.game;

import kiec.ireneusz.cardgame.domain.game.types.Gender;
import kiec.ireneusz.cardgame.domain.game.dto.PlayerApi;
import kiec.ireneusz.cardgame.utils.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "public", name = "players")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Player extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String description;
    @NotNull
    private Long level;
    @NotNull
    private Long experience;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            schema = "public", name = "card2player",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> playerWaist;

    public Player(PlayerApi api, List<Card> playerWaist) {
        this.name = api.getName();
        this.gender = api.getGender();
        this.description = api.getDescription();
        this.level = 1L;
        this.experience = 0L;
        this.playerWaist = playerWaist;
    }

    public void giveWaist(ArrayList<Card> add) {
    }
}
