package kiec.ireneusz.cardgame.api;

import io.swagger.annotations.Api;
import kiec.ireneusz.cardgame.domain.game.GameFacade;
import kiec.ireneusz.cardgame.domain.game.dto.CardDTO;
import kiec.ireneusz.cardgame.exception.CardNotFoundException;
import kiec.ireneusz.cardgame.exception.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@RestController
@RequestMapping(value = "/cardsAction", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "CardAction")
public class CardActionsController {

    private final GameFacade gameFacade;

    @Autowired
    public CardActionsController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @GetMapping("/revealCard/{cardId}")
    public ResponseEntity<CardDTO> revealCard(
            @PathVariable Long cardId
    ) throws CardNotFoundException {
        return ResponseEntity.ok(gameFacade.revealCard(cardId));
    }

    @GetMapping("/coverCard/{cardId}")
    public ResponseEntity<CardDTO> coverCard(
            @PathVariable Long cardId
    ) throws CardNotFoundException {
        return ResponseEntity.ok(gameFacade.coverCard(cardId));
    }

    @GetMapping("/revealPlayerCards/{playerId}")
    public ResponseEntity<List<CardDTO>> revealPlayerCards(
            @PathVariable Long playerId
    ) throws PlayerNotFoundException {
        return ResponseEntity.ok(gameFacade.revealPlayerCards(playerId));
    }

    @GetMapping("/coverPlayerCards/{playerId}")
    public ResponseEntity<List<CardDTO>> coverPlayerCards(
            @PathVariable Long playerId
    ) throws PlayerNotFoundException {
        return ResponseEntity.ok(gameFacade.coverPlayerCards(playerId));
    }

}
