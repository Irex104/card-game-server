package kiec.ireneusz.cardgame.domain.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByIdAndDeletedAtIsNull(Long playerId);

    List<Player> findAllByDeletedAtIsNull();
}
