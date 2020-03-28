package kiec.ireneusz.cardgame.domain.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Card2playerRepository extends JpaRepository<Card2player, Long> {

    List<Card2player> findAllByPlayerIdAndDeletedAtIsNull(Long playerId);

}
