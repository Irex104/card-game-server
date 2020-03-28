package kiec.ireneusz.cardgame.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kiec.ireneusz.cardgame.domain.game.Card;
import kiec.ireneusz.cardgame.domain.game.Player;
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
public class PlayerDTO {

    private Long id;
    private String name;
    private String description;
    private Long level;
    private Long experience;
    private List<CardDTO> playerWaist;

    public PlayerDTO(Player player, List<CardDTO> playerWaist) {
        this.id = player.getId();
        this.name = player.getName();
        this.description = player.getDescription();
        this.level = player.getLevel();
        this.experience = player.getExperience();
        this.playerWaist = playerWaist;
    }
}
