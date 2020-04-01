package kiec.ireneusz.cardgame.domain.game.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kiec.ireneusz.cardgame.domain.game.Card;
import kiec.ireneusz.cardgame.domain.game.types.ColorName;
import kiec.ireneusz.cardgame.domain.game.types.FigureName;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDTO {

    private Long id;
    private Long colorStrength;
    private Long figureStrength;
    private ColorName colorName;
    private FigureName figureName;
    private String imagePath;
    private String reverseImagePath;
    private String visibleImagePath;
    private boolean discovered;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.colorStrength = card.getColorStrength();
        this.figureStrength = card.getFigureStrength();
        this.colorName = card.getColorName();
        this.figureName = card.getFigureName();
        this.imagePath = card.getImagePath();
        this.reverseImagePath = card.getReverseImagePath();
        this.visibleImagePath = card.getVisibleImagePath();
        this.discovered = card.isDiscovered();
    }
}
