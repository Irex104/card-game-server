package kiec.ireneusz.cardgame.domain.game;

import kiec.ireneusz.cardgame.domain.game.dto.GameApi;
import kiec.ireneusz.cardgame.utils.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(schema = "public", name = "games")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Game extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String description;
    @Column(name = "points_per_game", nullable = false)
    private Long pointsPerGame;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            schema = "public", name = "player2game",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;
    @NotNull
    private boolean last = false;

    public Game(GameApi api, List<Player> playersList) {
        this.name = api.getName();
        this.description = api.getDescription();
        this.pointsPerGame = api.getPointsPerGame();
        this.players = playersList;
    }
}
