package com.biopark.grupo2.service;

import com.biopark.grupo2.exception.CertificadoExcessao;
import com.biopark.grupo2.exception.EmpresaExcessao;
import com.biopark.grupo2.exception.FormularioExcessao;
import com.biopark.grupo2.exception.PerguntaExcessao;
import com.biopark.grupo2.model.Certificado;
import com.biopark.grupo2.model.Empresa;
import com.biopark.grupo2.model.Formulario;
import com.biopark.grupo2.model.Pergunta;
import com.biopark.grupo2.repository.RepositoryCertificado;
import com.biopark.grupo2.repository.RepositoryEmpresa;
import com.biopark.grupo2.repository.RepositoryFormulario;
import com.biopark.grupo2.repository.RepositoryPergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaService {

    @Autowired
    private RepositoryFormulario repositoryFormulario;

    @Autowired
    private RepositoryCertificado repositoryCertificado;

    @Autowired
    private RepositoryEmpresa repositoryEmpresa;

    @Autowired
    RepositoryPergunta repositoryPergunta;

    public Formulario buscarFormulario(Long idFormulario) {
        return repositoryFormulario.findById(idFormulario)
                .orElseThrow(() -> new FormularioExcessao
                        ("Formulário não encontrado"));
    }

    public Certificado buscarCertificado(Long idCertificado) {
        return repositoryCertificado.findById(idCertificado)
                .orElseThrow(() -> new CertificadoExcessao
                        ("Certificado não encontrado"));
    }

    public Empresa buscarEmpresa(Long idEmpresa) {
        return repositoryEmpresa.findById(idEmpresa)
                .orElseThrow(() -> new EmpresaExcessao
                        ("Empresa não encontrada"));
    }

    public List<Pergunta> buscarPerguntas(List<Long> id) {
        List<Pergunta> perguntas = repositoryPergunta.findAllById((id));
        if (perguntas.isEmpty()) {
            throw new PerguntaExcessao("Nenhuma pergunta cadastrada no formulario");
        }
        return perguntas;
    }
}

