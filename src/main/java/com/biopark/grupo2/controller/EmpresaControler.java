package com.biopark.grupo2.controller;

import com.biopark.grupo2.model.Empresa;
import com.biopark.grupo2.repository.RepositoryEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EmpresaControler {

    @Autowired
    private RepositoryEmpresa repositoryEmpresa;

    @GetMapping("/register")
    public ModelAndView modelAndView(){

        Empresa empresa = new Empresa();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("novaEmpresa");
        modelAndView.addObject("empresa", empresa);
        return modelAndView;
    }



    /*public String showForm(Model model) {
        Empresa empresa = new Empresa();
        model.addAttribute("empresa", empresa);
       return  "novaEmpresa";
    }*/
    @PostMapping("/register")
    public RedirectView createEmpresa(@ModelAttribute ("empresa") Empresa empresa, RedirectAttributes attributes) {
        repositoryEmpresa.save(empresa);
        attributes.addFlashAttribute("condition", "funcionol");
        return new RedirectView("/novaEmpresa");
    }

}