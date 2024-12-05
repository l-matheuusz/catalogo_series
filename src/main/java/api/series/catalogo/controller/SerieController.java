package api.series.catalogo.controller;

import api.series.catalogo.model.Serie;
import api.series.catalogo.repository.SerieRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("serie")
public class SerieController {

    @Autowired
    private SerieRepository serieRepository;

    @GetMapping("cadastrar")
    public String cadastrar(Serie serie){
        return "serie/cadastro";
    }

    @GetMapping("listar") //localhost:8080/serie/listar
    public String listar(Model model){
        model.addAttribute("status", serieRepository.findAll());
        return "serie/listar";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        model.addAttribute("serie", serieRepository.findById(id));
        //Retornar a view
        return "serie/editar";
    }

    @PostMapping("editar")
    @Transactional
    public String editar(@Valid Serie serie, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model){
        if (bindingResult.hasErrors()){
            return "serie/editar";
        }
        serieRepository.save(serie);
        redirectAttributes.addFlashAttribute("msg", "Serie atualizada");
        return "redirect:/serie/listar";
    }

    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Serie serie, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Model model){
        if (bindingResult.hasErrors()){
            return "serie/editar";
        }
        serieRepository.save(serie);
        redirectAttributes.addFlashAttribute("msg", "Serie registrada!");
        return "redirect:/serie/cadastrar";
    }

    @PostMapping("excluir")
    @Transactional
    public String remover(Long idStatus, RedirectAttributes redirectAttributes){
        serieRepository.deleteById(idStatus);
        redirectAttributes.addFlashAttribute("msg", "Serie removida!");
        return "redirect:/serie/listar";
    }
}
