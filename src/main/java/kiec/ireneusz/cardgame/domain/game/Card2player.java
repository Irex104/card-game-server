package kiec.ireneusz.cardgame.domain.game;

import kiec.ireneusz.cardgame.utils.AbstractModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "public", name = "card2player")
@Getter
@NoArgsConstructor
public class Card2player extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "card_id", nullable = false)
    private Long cardId;

    public Card2player(@NotNull Long playerId, @NotNull Long cardId) {
        this.playerId = playerId;
        this.cardId = cardId;
    }
}
