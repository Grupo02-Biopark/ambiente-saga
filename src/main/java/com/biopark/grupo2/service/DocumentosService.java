package com.biopark.grupo2.service;

import com.biopark.grupo2.model.Documentos;
import com.biopark.grupo2.model.Resposta;
import com.biopark.grupo2.repository.RepositoryDocumentos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentosService {

    @Autowired
    private RepositoryDocumentos repositoryDocumentos;

    @Transactional
    public List<Documentos> salvarDocumentos(List<String> nomesDocumentos, Resposta novaResposta) {

        List<Documentos> documentosSalvos = new ArrayList<>();

        for (String nome : nomesDocumentos) {

            Documentos documento = new Documentos();
            documento.setNomeDocumento(nome);
            documento.setId_resposta(novaResposta);

            Documentos salvo = repositoryDocumentos.save(documento);

            documentosSalvos.add(salvo);
        }

        return documentosSalvos;
    }
}
