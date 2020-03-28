package kiec.ireneusz.cardgame.domain.game;

import kiec.ireneusz.cardgame.domain.game.dto.CardApi;
import kiec.ireneusz.cardgame.domain.game.types.ColorName;
import kiec.ireneusz.cardgame.domain.game.types.FigureName;
import kiec.ireneusz.cardgame.utils.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "public", name = "cards")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "color_strength", nullable = false)
    private Long colorStrength;
    @Column(name = "figure_strength", nullable = false)
    private Long figureStrength;

    @Column(name = "color_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private ColorName colorName;
    @Column(name = "figure_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private FigureName figureName;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "reverse_image_path", nullable = false)
    private String reverseImagePath = "reverse.png";

    @Column(name = "visible_image_path", nullable = false)
    private String visibleImagePath;

    @NotNull
    private boolean discovered = false;

    public Card(CardApi api) {
        this.colorStrength = api.getColorStrength();
        this.figureStrength = api.getFigureStrength();
        this.colorName = api.getColorName();
        this.figureName = api.getFigureName();
        this.imagePath = api.getImagePath();
        this.visibleImagePath = this.getReverseImagePath();
    }

    public void reveal(){
        this.discovered = true;
        this.visibleImagePath = this.getImagePath();
    }

    public void cover(){
        this.discovered = false;
        System.out.println(this.getReverseImagePath());
        System.out.println(getVisibleImagePath());
        this.visibleImagePath = this.getReverseImagePath();
        System.out.println(this.getReverseImagePath());
        System.out.println(this.getVisibleImagePath());
    }



}
