package br.ufc.quixada.entity;

import lombok.*;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Document
@Entity
@Table(name = "progress")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "idProfile")
    @NonNull
    Profile profile;

    @ManyToOne
    @JoinColumn(name = "idGame")
    @NonNull
    Game game;

    @Min(value = 0, message = "O progresso deve ser no mínimo 0")
    @Max(value = 100, message = "O progresso deve ser no máximo 100")
    int progressPercent;

    @Min(value = 0, message = "O tempo de jogo deve ser no mínimo 0")
    int minutesPlayed;

    @Min(value = 0, message = "A quantidade de troféus deve ser no mínimo 0")
    int trophyQuantity;
}
