package kiec.ireneusz.cardgame.domain.game;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByDeletedAtIsNull();

    Optional<Card> findByIdAndDeletedAtIsNull(Long cardId);
}
