package kiec.ireneusz.cardgame.utils;

import kiec.ireneusz.cardgame.domain.game.Card;
import kiec.ireneusz.cardgame.domain.game.Game;
import kiec.ireneusz.cardgame.domain.game.Player;
import kiec.ireneusz.cardgame.domain.game.dto.CardDTO;
import kiec.ireneusz.cardgame.domain.game.dto.GameDTO;
import kiec.ireneusz.cardgame.domain.game.dto.PlayerDTO;

public class Mapper {


    public static PlayerDTO toPlayerDTOSimple(Player player) {
        return new PlayerDTO().builder()
                .id(player.getId())
                .name(player.getName())
                .build();
    }

    public static CardDTO toCardDTOSimple(Card card) {
        return new CardDTO().builder()
                .id(card.getId())
                .figureName(card.getFigureName())
                .colorName(card.getColorName())
                .visibleImagePath(card.getVisibleImagePath())
                .build();
    }

    public static GameDTO toGameDTOSimple(Game game) {
        return new GameDTO().builder()
                .id(game.getId())
                .name(game.getName())
                .build();
    }
}
