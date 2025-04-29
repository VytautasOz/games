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
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String platform;
    private LocalDate releaseDate;

    @Column(columnDefinition = "TEXT")
    private String summary;

    private Double rating;

}
