package kiec.ireneusz.cardgame.domain.game;

import kiec.ireneusz.cardgame.domain.game.dto.*;
import kiec.ireneusz.cardgame.exception.CardNotFoundException;
import kiec.ireneusz.cardgame.exception.GameNotFoundException;
import kiec.ireneusz.cardgame.exception.PlayerNotFoundException;
import kiec.ireneusz.cardgame.utils.Mapper;
import kiec.ireneusz.cardgame.utils.PageableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameFacade {

    private final GameService gameService;
    private final PlayerService playerService;
    private final CardService cardService;
    private final Card2playerService card2playerService;

    @Autowired
    public GameFacade(
            GameService gameService,
            PlayerService playerService,
            CardService cardService,
            Card2playerService card2playerService
    ) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.cardService = cardService;
        this.card2playerService = card2playerService;
    }

    //region GAME

    public GameDTO createGame(GameApi api) throws PlayerNotFoundException {
        List<Player> playersList = playerService.getPlayersList(api.getPlayersIdsList());
        return gameService.createGame(api, playersList);
    }

    public void deal(Long gameId) throws GameNotFoundException {
        Game game = gameService.getOne(gameId);
        List<Card> mainWaist = cardService.getShuffled52Waist();
        card2playerService.deal(game, mainWaist);
    }

    public List<GameDTO> getGames() {
        return gameService.getAll().stream()
                .map(Mapper::toGameDTOSimple).collect(Collectors.toList());
    }

    //endregion
    //region PLAYER

    public PlayerDTO createPlayer(PlayerApi api) {
        List<Card> playerDeck = new ArrayList<>();
        return playerService.createPlayer(api, playerDeck);
    }

    public List<Player> getPlayersList(PlayersListApi playersList) throws PlayerNotFoundException {
        return playerService.getPlayersList(playersList.getPlayersIds());
    }

    public PlayerDTO getPlayer(Long playerId) throws PlayerNotFoundException {
        Player player = playerService.getOne(playerId);
        List<Card> playerWaist = player.getPlayerWaist();
        return new PlayerDTO(player, playerWaist.stream().map(Mapper::toCardDTOSimple).collect(Collectors.toList()));
    }

    public List<PlayerDTO> getPlayers() {
        return playerService.getAll().stream()
                .map(Mapper::toPlayerDTOSimple).collect(Collectors.toList());
    }

    //endregion
    //region CARD

    public List<CardDTO> createWaist() {
        return cardService.createWaist().stream()
                .map(Mapper::toCardDTOSimple).collect(Collectors.toList());
    }

    public CardDTO createCard(CardApi api) {
        return new CardDTO(cardService.createCard(api));
    }

    public CardDTO getCard(Long cardId) throws CardNotFoundException {
        return new CardDTO(cardService.getOne(cardId));
    }

    public List<CardDTO> getCards() {
        return cardService.getAll();
    }

    public CardDTO revealCard(Long cardId) throws CardNotFoundException {
        return new CardDTO(cardService.revealCard(cardId));
    }

    public CardDTO coverCard(Long cardId) throws CardNotFoundException {
        return new CardDTO(cardService.coverCard(cardId));
    }

    public List<CardDTO> revealPlayerCards(Long playerId) throws PlayerNotFoundException {
        Player player = playerService.getOne(playerId);
        List<Card> playerWaist = player.getPlayerWaist();
        return cardService.revealPlayerCards(playerWaist).stream()
                .map(Mapper::toCardDTOSimple).collect(Collectors.toList());
    }

    public List<CardDTO> coverPlayerCards(Long playerId) throws PlayerNotFoundException {
        Player player = playerService.getOne(playerId);
        List<Card> playerWaist = player.getPlayerWaist();
        return cardService.coverPlayerCards(playerWaist).stream()
                .map(Mapper::toCardDTOSimple).collect(Collectors.toList());
    }

    public List<CardDTO> getPlayerCards(Long playerId) throws PlayerNotFoundException {
        PlayerDTO playerDTO = this.getPlayer(playerId);
        return playerDTO.getPlayerWaist();
    }
    //endregion
}
