package lt.codeacademy.games.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
public class UserGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long videoGameId;
    private Long userId;
    private LocalDate playedDate;

    @Column(columnDefinition = "TEXT")
    private String userReview;

    private Float userRating;

}
