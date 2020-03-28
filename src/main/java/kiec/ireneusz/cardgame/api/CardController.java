package kiec.ireneusz.cardgame.api;

import io.swagger.annotations.Api;
import kiec.ireneusz.cardgame.domain.game.GameFacade;
import kiec.ireneusz.cardgame.domain.game.dto.CardApi;
import kiec.ireneusz.cardgame.domain.game.dto.CardDTO;
import kiec.ireneusz.cardgame.exception.CardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
@RequestMapping(value = "/cards", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Card")
public class CardController {

    private final GameFacade gameFacade;

    @Autowired
    public CardController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @GetMapping("/createWaist")
    public ResponseEntity<List<CardDTO>> createWaist(){
        return ResponseEntity.ok(gameFacade.createWaist());
    }

    @PostMapping("/createCard")
    public ResponseEntity<CardDTO> createCard(
            @RequestBody CardApi api
    ){
        return ResponseEntity.ok(gameFacade.createCard(api));
    }

    @GetMapping("/getOne/{cardId}")
    public ResponseEntity<CardDTO>/*String*/ getOne(
            @PathVariable Long cardId/*,
            Model model*/
    ) throws CardNotFoundException {
        /*CardDTO cardDTO = gameFacade.getCard(cardId);
        model.addAttribute("cardName", cardDTO.getFigureName() + " " + cardDTO.getColorName());
        model.addAttribute("cardImage", cardDTO.getVisibleImagePath());*/
        return /*"CardOutput"*/ResponseEntity.ok(gameFacade.getCard(cardId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CardDTO>> getAll(){
        return ResponseEntity.ok(gameFacade.getCards());
    }

}
