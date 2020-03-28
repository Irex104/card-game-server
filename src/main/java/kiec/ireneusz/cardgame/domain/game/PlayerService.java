package kiec.ireneusz.cardgame.domain.game;

import kiec.ireneusz.cardgame.domain.game.dto.PlayerApi;
import kiec.ireneusz.cardgame.domain.game.dto.PlayerDTO;
import kiec.ireneusz.cardgame.exception.PlayerNotFoundException;
import kiec.ireneusz.cardgame.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    PlayerDTO createPlayer(PlayerApi api, List<Card> playerDeck) {
        Player player = new Player(api, playerDeck);
        player = repository.save(player);
        return new PlayerDTO(player, playerDeck.stream().map(Mapper::toCardDTOSimple).collect(Collectors.toList()));
    }

    Player getOne(Long playerId) throws PlayerNotFoundException {
        return repository.findByIdAndDeletedAtIsNull(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }

    List<Player> getAll() {
        return repository.findAllByDeletedAtIsNull();
    }

    List<Player> getPlayersList(List<Long> playersIdsList) throws PlayerNotFoundException {
        List<Player> playersList = new ArrayList<>(playersIdsList.size());
        for (Long playerId : playersIdsList){
            playersList.add(this.getOne(playerId));
        }
        return playersList;
    }

}
