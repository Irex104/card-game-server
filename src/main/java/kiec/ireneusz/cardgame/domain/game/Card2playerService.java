package kiec.ireneusz.cardgame.domain.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Card2playerService {

    private final Card2playerRepository repository;

    @Autowired
    public Card2playerService(Card2playerRepository repository) {
        this.repository = repository;
    }


    void deal(Game game, List<Card> mainWaist) {
        int numberOfPlayers = game.getPlayers().size();
        int cardsPerPlayer = mainWaist.size() / numberOfPlayers;
        List<Card2player> card2playerList = new ArrayList<>(game.getPlayers().size());

        for (int i = 0; i < numberOfPlayers; i++)
            for (int j = 0; j < cardsPerPlayer; j++)
                card2playerList.add(new Card2player((long) i+1, mainWaist.get(i + 3 * j).getId()));

        repository.saveAll(card2playerList);
    }
}
