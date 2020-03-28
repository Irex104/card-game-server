package kiec.ireneusz.cardgame.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kiec.ireneusz.cardgame.domain.game.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameDTO {

    private Long id;
    private String name;
    private String description;
    private Long pointsPerGame;
    private List<PlayerDTO> playerDTOs;

    public GameDTO(Game game, List<PlayerDTO> playerDTOs) {
        this.id = game.getId();
        this.name = game.getName();
        this.description = game.getDescription();
        this.pointsPerGame = game.getPointsPerGame();
        this.playerDTOs = playerDTOs;
    }
}
