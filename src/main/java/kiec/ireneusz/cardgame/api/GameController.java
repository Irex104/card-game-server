package kiec.ireneusz.cardgame.api;

import io.swagger.annotations.Api;
import kiec.ireneusz.cardgame.domain.game.Game;
import kiec.ireneusz.cardgame.domain.game.GameFacade;
import kiec.ireneusz.cardgame.domain.game.dto.GameApi;
import kiec.ireneusz.cardgame.domain.game.dto.GameDTO;
import kiec.ireneusz.cardgame.domain.game.dto.PlayersListApi;
import kiec.ireneusz.cardgame.exception.GameNotFoundException;
import kiec.ireneusz.cardgame.exception.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Game")
public class GameController {

    private final GameFacade gameFacade;

    @Autowired
    public GameController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @PostMapping("/createGame")
    public ResponseEntity<GameDTO> createGame(
            @RequestBody GameApi api/*,
            @RequestBody PlayersListApi playersListApi*/
    ) throws PlayerNotFoundException {
        return ResponseEntity.ok(gameFacade.createGame(api/*, playersListApi*/));
    }

    @GetMapping("/deal/{gameId}")
    public ResponseEntity<Void> deal(
            @PathVariable Long gameId
    ) throws GameNotFoundException {
        gameFacade.deal(gameId);
        return ResponseEntity.noContent().build();
    }

}
