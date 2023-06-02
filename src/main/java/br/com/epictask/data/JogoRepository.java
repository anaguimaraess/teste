package br.com.epictask.data;

import br.com.epictask.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository <Jogo, Integer> {
}
