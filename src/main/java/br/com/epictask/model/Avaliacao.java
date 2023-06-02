package br.com.epictask.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idavaliacao;
    private int nota;
    private String avaliacao;

    @OneToOne
    @MapsId
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;
}
