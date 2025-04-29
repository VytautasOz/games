package lt.codeacademy.games.repository;

import lt.codeacademy.games.entity.Game;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByRatingGreaterThan(Double rating, Sort sort);
    List<Game> findByRatingGreaterThanAndPlatformContainingIgnoreCase(Double rating, String platform, Sort sort);
}
