package br.ufc.quixada.entity;

import lombok.*;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@NamedQueries({
        @NamedQuery(name = "gameByName", query = "select g from Game g where upper(g.name) like upper(:name)"),
        @NamedQuery(name = "gameByPriceLessThanEqual", query = "select g from Game g where g.price <= :price")
})

@Document
@Entity
@Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private Double review;

    @NonNull
    @Min(value = 0, message = "O preço deve ser no mínimo 0")
    private Double price;

    @NonNull
    private String developer;

    @NonNull
    private String publisher;

    @NonNull
    private LocalDate releaseDate;

    @NonNull
    private String gender;
}
