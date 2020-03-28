package kiec.ireneusz.cardgame.domain.game.dto;

import kiec.ireneusz.cardgame.domain.game.Game;
import kiec.ireneusz.cardgame.domain.game.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameApi {

    private String name;
    private String description;
    private Long pointsPerGame;
    private List<Long> playersIdsList;

}
