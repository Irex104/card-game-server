package kiec.ireneusz.cardgame.domain.game;

import kiec.ireneusz.cardgame.domain.game.dto.CardApi;
import kiec.ireneusz.cardgame.domain.game.dto.CardDTO;
import kiec.ireneusz.cardgame.domain.game.types.ColorName;
import kiec.ireneusz.cardgame.domain.game.types.FigureName;
import kiec.ireneusz.cardgame.exception.CardNotFoundException;
import kiec.ireneusz.cardgame.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository repository;

    @Autowired
    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    List<Card> get52Waist() {
        return repository.findAllByDeletedAtIsNull();
    }

    List<Card> getShuffled52Waist(){
        List<Card> waist = get52Waist();
        return this.shuffle(waist);
    }

    private List<Card> shuffle(List<Card> waist) {
        List<Card> shuffledWaist = new ArrayList<>(waist.size());
        ArrayList<Boolean> shuffledWaistIndexFlag = new ArrayList<>(waist.size());
        for(int i=0;i<waist.size();i++)
            shuffledWaistIndexFlag.add(true);
        int randomIndex;
        for (int i=0; i<waist.size();i++){
            boolean flagDoWhile = true;
            do{
                randomIndex = (int) (Math.random() * waist.size());
                if(shuffledWaistIndexFlag.get(randomIndex)){
                    flagDoWhile = false;
                    shuffledWaistIndexFlag.set(randomIndex, false);
                    shuffledWaist.add(waist.get(randomIndex));
                }
            }while(flagDoWhile);
        }
        return shuffledWaist;
    }

    Card createCard(CardApi api){
        Card card = new Card(api);
        return repository.save(card);
    }
    List<Card> createWaist() {
        List<Card> waist = new ArrayList<>(52);
        for (int i = 0; i < 52/4; i++) {
            waist.add(new Card(new CardApi((long) 0, (long) i, ColorName.values()[0], FigureName.values()[i], (i+2)+"C.png")));
            waist.add(new Card(new CardApi((long) 1, (long) i, ColorName.values()[1], FigureName.values()[i], (i+2)+"D.png")));
            waist.add(new Card(new CardApi((long) 2, (long) i, ColorName.values()[2], FigureName.values()[i], (i+2)+"H.png")));
            waist.add(new Card(new CardApi((long) 3, (long) i, ColorName.values()[3], FigureName.values()[i], (i+2)+"S.png")));
        }
        System.out.println(waist);
        waist = repository.saveAll(waist);
        return waist;
    }

    Card getOne(Long cardId) throws CardNotFoundException {
        return repository.findByIdAndDeletedAtIsNull(cardId)
                .orElseThrow(() -> new CardNotFoundException(cardId));
    }

    List<CardDTO> getAll() {
        return repository.findAllByDeletedAtIsNull().stream()
                .map(Mapper::toCardDTOSimple).collect(Collectors.toList());
    }

    Card revealCard(Long cardId) throws CardNotFoundException {
        Card card = this.getOne(cardId);
        card.reveal();
        return repository.save(card);
    }

    Card coverCard(Long cardId) throws CardNotFoundException {
        Card card = this.getOne(cardId);
        card.cover();
        return repository.save(card);
    }

    List<Card> revealPlayerCards(List<Card> playerWaist) {
        playerWaist.stream().forEach(card -> card.reveal());
        return repository.saveAll(playerWaist);
    }

    List<Card> coverPlayerCards(List<Card> playerWaist) {
        playerWaist.stream().forEach(card -> card.cover());
        return repository.saveAll(playerWaist);
    }
}
