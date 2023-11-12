package br.ufc.quixada.entity;

import lombok.*;

import java.time.LocalDate;

import jakarta.persistence.*;

@NamedQueries({
	@NamedQuery(name = "gameByName", query = "select g from Game g where upper(g.name) like upper(:name)"),
    @NamedQuery(name = "gameByPriceLessThanEqual", query = "select g from Game g where g.price <= :price")
})
    
@Entity
@Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private Double review;
    @NonNull
    private Double price;
    @NonNull
    private String developer;
    @NonNull
    private String publisher;
    @NonNull
    private LocalDate release_date;
    @NonNull
    private String gender;
    @NonNull
    private String achievments;
}