package com.biopark.grupo2.controller;

import com.biopark.grupo2.model.Formulario;
import com.biopark.grupo2.model.Pergunta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

@Controller
public class Grupo2Controller {
@GetMapping("/")
    public ModelAndView grupo2(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("criar+");
    return modelAndView;
}
@PostMapping("/criar+")
public String criarMais(@ModelAttribute Formulario formulario,
                        @ModelAttribute Pergunta pergunta,
                        Model model){

    model.addAttribute("titulo",formulario.getTitulo());
    return "criar+";
}

}
