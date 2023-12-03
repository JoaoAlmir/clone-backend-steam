package br.ufc.quixada.entity;

import lombok.*;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@NamedQueries({
    @NamedQuery(name = "getCompleteProgress", query = "select p from Progress p where p.progress_percent = 100"),
    
})

@Document
@Entity
@Table(name = "progress")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "id_profile")
    @NonNull
    Profile profile;

    @ManyToOne
    @JoinColumn(name = "id_game")
    @NonNull
    Game game;

    @Min(value = 0, message = "O progresso deve ser no mínimo 0")
    @Max(value = 100, message = "O progresso deve ser no máximo 100")
    int progress_percent;

    @Min(value = 0, message = "O tempo de jogo deve ser no mínimo 0")
    int minutes_played;

    @Min(value = 0, message = "A quantidade de troféus deve ser no mínimo 0")
    int trophy_quantity;

}
