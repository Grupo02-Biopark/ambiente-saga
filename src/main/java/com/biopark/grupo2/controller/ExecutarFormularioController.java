package com.biopark.grupo2.controller;

import com.biopark.grupo2.DTO.RespostaDTO;
import com.biopark.grupo2.model.*;
import com.biopark.grupo2.service.BuscaService;
import com.biopark.grupo2.service.ExecutarFormularioService;
import com.biopark.grupo2.service.NovoCertificadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ExecutarFormularioController {

    @Autowired
    private ExecutarFormularioService executarFormularioService;

    @Autowired
    private BuscaService buscaService;

    @Autowired
    private NovoCertificadoService novoCertificadoService;

    @GetMapping("/executarFormulario/{id}")
    public ModelAndView executarFormulario(@PathVariable Long id,
                                           @RequestParam("id_empresa")Long idEmpresa) {

        ModelAndView modelAndView = new ModelAndView();

        Empresa empresa = buscaService.buscarEmpresa(idEmpresa);
        Formulario formulario = buscaService.buscarFormulario(id);
        Certificado certificado = novoCertificadoService.criarCertificado(id, empresa);
        List<Pergunta> perguntas = formulario.getPerguntas();

        RespostaDTO respostaDTO = new RespostaDTO();

        respostaDTO.setId_formulario(formulario.getId_formulario());
        respostaDTO.setId_perguntas(perguntas.stream().map(Pergunta::getId_pergunta).collect(Collectors.toList()));
        respostaDTO.setId_empresa(idEmpresa);
        respostaDTO.setId_certificado(certificado.getId_certificado());

        modelAndView.setViewName("executarFormulario");
        modelAndView.addObject("respostaDTO", respostaDTO);
        modelAndView.addObject("tituloFormulario", formulario.getTitulo());
        modelAndView.addObject("idFormulario", formulario.getId_formulario());
        modelAndView.addObject("baseFormulario", formulario.getBase());
        modelAndView.addObject("estadoFormulario", formulario.getEstado());
        modelAndView.addObject("idCertificado", certificado.getId_certificado());
        modelAndView.addObject("idEmpresa", empresa.getId_empresa());
        modelAndView.addObject("perguntas", perguntas);
        return modelAndView;
    }

    @PostMapping("/executarFormulario")
    public RedirectView salvarResposta(@ModelAttribute("respostaDTO") @Valid RespostaDTO respostaDTO,
                                       RedirectAttributes attributes) {

        Map<Long, Integer> respostas = respostaDTO.getRespostas();

        if (respostas != null && !respostas.isEmpty()) {
            executarFormularioService.criarRespostas(respostaDTO);
        }

        attributes.addFlashAttribute("resposta-cadastrada", "resposta-cadastrada");
        return new RedirectView("/certificado/" + respostaDTO.getId_formulario());
    }
}
