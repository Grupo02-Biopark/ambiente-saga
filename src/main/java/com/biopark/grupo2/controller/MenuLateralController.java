package com.biopark.grupo2.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuLateralController {
    @GetMapping("/")
    public ModelAndView pegarPaginaInicial() {
        ModelAndView pegarPaginaInicial = new ModelAndView();
        pegarPaginaInicial.setViewName("paginaInicial");
        return pegarPaginaInicial;
    }
}
