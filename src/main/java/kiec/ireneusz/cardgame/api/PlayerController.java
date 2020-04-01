package kiec.ireneusz.cardgame.api;

import io.swagger.annotations.Api;
import kiec.ireneusz.cardgame.domain.game.Card;
import kiec.ireneusz.cardgame.domain.game.GameFacade;
import kiec.ireneusz.cardgame.domain.game.dto.CardDTO;
import kiec.ireneusz.cardgame.domain.game.dto.PlayerApi;
import kiec.ireneusz.cardgame.domain.game.dto.PlayerDTO;
import kiec.ireneusz.cardgame.exception.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Player")
public class PlayerController {

    private final GameFacade gameFacade;

    @Autowired
    public PlayerController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @PostMapping("/createPlayer")
    public ResponseEntity<PlayerDTO> createPlayer(
            @RequestBody PlayerApi api
    ){
        return ResponseEntity.ok(gameFacade.createPlayer(api));
    }

    @GetMapping("/getOne/{playerId}")
    public ResponseEntity<PlayerDTO> getOne(
            @PathVariable Long playerId
    ) throws PlayerNotFoundException {
        return ResponseEntity.ok(gameFacade.getPlayer(playerId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PlayerDTO>> getAll(){
        return ResponseEntity.ok(gameFacade.getPlayers());
    }

    @GetMapping("/getPlayerCards/{playerId}")
    public ResponseEntity<List<CardDTO>> getPlayerCards(
            @PathVariable Long playerId
    ) throws PlayerNotFoundException {

        return ResponseEntity.ok(gameFacade.getPlayerCards(playerId));
    }

}
