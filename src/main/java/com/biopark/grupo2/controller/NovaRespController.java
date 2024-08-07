package com.biopark.grupo2.controller;

import com.biopark.grupo2.model.Certificado;
import com.biopark.grupo2.model.Empresa;
import com.biopark.grupo2.model.Formulario;
import com.biopark.grupo2.model.Pergunta;
import com.biopark.grupo2.repository.RepositoryCertificado;
import com.biopark.grupo2.repository.RepositoryEmpresa;
import com.biopark.grupo2.repository.RepositoryFormulario;
import com.biopark.grupo2.repository.RepositoryPergunta;
import com.biopark.grupo2.service.BuscaService;
import com.biopark.grupo2.service.NovaRespService;
import com.biopark.grupo2.service.NovoCertificadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class NovaRespController {

    @Autowired
    private RepositoryFormulario repositoryFormulario;
    @Autowired
    private RepositoryEmpresa repositoryEmpresa;
    @Autowired
    private RepositoryPergunta repositoryPergunta;

    @Autowired
    private NovoCertificadoService novoCertificadoService;
    @Autowired
    private BuscaService buscaService;

    @GetMapping("/executarAvaliacao")
    public ModelAndView paginaNovaResposta(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("novoRespForm");
        modelAndView.addObject("forms",todosFormularios());
        modelAndView.addObject("empresas", todasEmpresas());
        return modelAndView;
    }

    @Autowired
    NovaRespService novaRespService;

    @PostMapping("/novaAvaliacao")
    public RedirectView criarResposta(@RequestParam("id_formulario")Long idFormulario,
                                      @RequestParam("id_empresa")Long idEmpresa){

        Formulario formulario = buscaService
                .buscarFormulario(idFormulario);
        Empresa empresa = buscaService
                .buscarEmpresa(idEmpresa);

        List<Pergunta> perguntas = formulario.getPerguntas();

        Formulario novoFormulario = novaRespService
                .novoFormulario(formulario,empresa,perguntas);

//        repositoryCertificado.save(novaRespService.novoCertificado(encontraFormPorId(id_formulario), encontraEmpresaPorId(id_empresa)));
//        repositoryFormulario.save(novaRespService.novoFormulario(encontraFormPorId(id_formulario), encontraEmpresaPorId(id_empresa), encontraPerguntasPeloFormId(id_formulario)));
        String redirectUrl = String.format("/executarFormulario/%d?id_empresa=%d", novoFormulario.getId_formulario(), idEmpresa);
        return new RedirectView(redirectUrl);
    }
    //Consultas
    public List<Formulario> todosFormularios(){
        return repositoryFormulario.findByBase(0);
    }

    public List<Empresa> todasEmpresas(){
        return repositoryEmpresa.findAll();
    }

    public Formulario encontraFormPorId(Long id){
        return repositoryFormulario.findById(id).orElse(null);
    }

    public Formulario pegaMaiorForm(){
        return repositoryFormulario.consultaMaiorId();
    }

    public Empresa encontraEmpresaPorId(Long id){
        return repositoryEmpresa.findById(id).orElse(null);
    }

    public List<Pergunta> encontraPerguntasPeloFormId(Long idFormulario) {
        return repositoryPergunta.findPerguntasByFormularioId(idFormulario);
    }
}
