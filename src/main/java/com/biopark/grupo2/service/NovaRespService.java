package com.biopark.grupo2.service;

import com.biopark.grupo2.model.*;
import com.biopark.grupo2.repository.RepositoryFormulario;
import com.biopark.grupo2.repository.RepositoryPergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovaRespService {
    private final RepositoryPergunta repositoryPergunta;

    @Autowired
    private RepositoryFormulario repositoryFormulario;

    public NovaRespService(RepositoryPergunta repositoryPergunta) {
        this.repositoryPergunta = repositoryPergunta;
    }

    public Formulario novoFormulario(Formulario formulario, Empresa empresa, List<Pergunta> perguntas){
        Formulario novoFormulario = new Formulario();

        novoFormulario.setTitulo(formulario.getTitulo() + " - Cópia");
        novoFormulario.setBase(1);
        novoFormulario.setEstado(true);
        novoFormulario.setEmpresa(empresa);
        novoFormulario = repositoryFormulario.save(novoFormulario);

        for (Pergunta pergunta : perguntas){
            Pergunta novaPergunta = new Pergunta();
            novaPergunta.setTitulo(pergunta.getTitulo());
            novaPergunta.setDocumento(pergunta.isDocumento());
            novaPergunta.setEstado(pergunta.isEstado());
            novaPergunta.setFormulario(novoFormulario);
            repositoryPergunta.save(novaPergunta);
        }

        return novoFormulario;

    }
}
