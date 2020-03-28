package kiec.ireneusz.cardgame.domain.game.dto;

import kiec.ireneusz.cardgame.domain.game.types.ColorName;
import kiec.ireneusz.cardgame.domain.game.types.FigureName;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardApi {

    private Long colorStrength;
    private Long figureStrength;
    private ColorName colorName;
    private FigureName figureName;
    private String imagePath;

}
