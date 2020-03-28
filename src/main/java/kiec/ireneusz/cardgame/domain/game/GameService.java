package kiec.ireneusz.cardgame.domain.game;

import kiec.ireneusz.cardgame.domain.game.dto.GameApi;
import kiec.ireneusz.cardgame.domain.game.dto.GameDTO;
import kiec.ireneusz.cardgame.exception.GameNotFoundException;
import kiec.ireneusz.cardgame.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository repository;

    @Autowired
    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    GameDTO createGame(GameApi api, List<Player> playersList) {
        Game game = new Game(api, playersList);
        game = repository.save(game);
        return new GameDTO(game, playersList.stream().map(Mapper::toPlayerDTOSimple).collect(Collectors.toList()));
    }

    Game getOne(Long gameId) throws GameNotFoundException {
        return repository.findByIdAndDeletedAtIsNull(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
    }


}
