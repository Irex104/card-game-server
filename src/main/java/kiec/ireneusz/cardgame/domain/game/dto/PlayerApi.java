package kiec.ireneusz.cardgame.domain.game.dto;

import kiec.ireneusz.cardgame.domain.game.types.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerApi {

    private String name;
    private Gender gender;
    private String description;

}
