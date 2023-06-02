package br.com.epictask.controller;

import br.com.epictask.data.JogoRepository;
import br.com.epictask.model.Avaliacao;
import br.com.epictask.model.Jogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class JogoController {

    @Autowired
    JogoRepository repository;

    @PostMapping("/jogos")
    public String cadastrar(Jogo jogo) {
        repository.save(jogo);
        return "redirect:/jogos";
    }

    @PostMapping("/jogos/avaliar")
    public String avaliarJogo(@RequestParam("jogoId") Integer id,
                              @RequestParam("nota") Integer nota,
                              @RequestParam("avaliacao") String avaliacao) {
        Optional<Jogo> jogoOptional = repository.findById(id);
        if (jogoOptional.isPresent()) {
            Jogo jogo = jogoOptional.get();
            Avaliacao avaliacaoObjeto = new Avaliacao();
            avaliacaoObjeto.setNota(nota);
            avaliacaoObjeto.setAvaliacao(avaliacao);
            jogo.setAvaliacao(avaliacaoObjeto);
            repository.save(jogo);
            return "redirect:/jogos";
        } else {
            throw new IllegalArgumentException("Jogo não encontrado");
        }
    }


    @GetMapping("/formulario_avaliacao")
    public String exibirFormularioAvaliacao(@RequestParam("jogoId") Integer id, Model model) {
        Optional<Jogo> jogoOptional = repository.findById(id);
        if (jogoOptional.isPresent()) {
            Jogo jogo = jogoOptional.get();
            model.addAttribute("jogo", jogo);
            return "formulario_avaliacao";
        } else {
            throw new IllegalArgumentException("Jogo não encontrado");
        }
    }



    @GetMapping("/jogos")
    public String jogos(Model model) {
        var jogos = repository.findAll();
        model.addAttribute("jogos", jogos);
        return "jogos";
    }

    @GetMapping("/jogos/cadastrar")
    public String formulario() {
        return "formulario_jogo";
    }

    @DeleteMapping("/jogos")
    public String apagar(Integer id, RedirectAttributes redirect ){
        repository.deleteById(id);
        redirect.addFlashAttribute("mensagem", "Jogo apagado com sucesso");
        return "redirect:/jogos";
    }




    @GetMapping("/")
    public String home() {
        return "redirect:/jogos";
    }
}
