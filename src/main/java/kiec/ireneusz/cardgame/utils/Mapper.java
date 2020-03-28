package kiec.ireneusz.cardgame.utils;

import kiec.ireneusz.cardgame.domain.game.Card;
import kiec.ireneusz.cardgame.domain.game.Player;
import kiec.ireneusz.cardgame.domain.game.dto.CardDTO;
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
}
