package com.biopark.grupo2.controller;
import com.biopark.grupo2.DTO.AvaliacaoDTO;
import com.biopark.grupo2.model.Formulario;
import com.biopark.grupo2.repository.RepositoryFormulario;
import com.biopark.grupo2.service.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class FormularioController {

    @Autowired
    private RepositoryFormulario repositoryFormulario;
    @Autowired
    private FormularioService formularioService;

    @GetMapping("/listaFormsBase")
    public ModelAndView lista_forms_base(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "all") String filter,
                                         @RequestParam(required = false) Boolean estado,
                                         @RequestParam(required = false) String searchTerm) {
        if (filter == "all") {
            estado = true; // ou qualquer valor padrão que faça sentido no seu contexto
        }
        Page<Formulario> formulariosBase = formularioService
                .findFormulariosLista(page, size, filter, estado,searchTerm);
        ModelAndView modelAndView = new ModelAndView("listaFormsBase");
        modelAndView.addObject("formulariosBase", formulariosBase);
        modelAndView.addObject("totalPages", formulariosBase.getTotalPages());
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("filter", filter);
        modelAndView.addObject("estado", estado);
        modelAndView.addObject("searchTerm", searchTerm);
        return modelAndView;
    }

    @PostMapping("/listaFormsBase")
    public RedirectView criarForm(
            @ModelAttribute ("formulario") Formulario formulario, RedirectAttributes attributes){
        Formulario formularioSalvo = repositoryFormulario.save(formulario);
        attributes.addFlashAttribute("condition", "cadastro-ok");
        Long idFormulario = formularioSalvo.getId_formulario();
        return new RedirectView("/detalhesFormulario/" + idFormulario);
    }


}