package com.biopark.grupo2.service;

import com.biopark.grupo2.DTO.AvaliacaoDTO;
import com.biopark.grupo2.repository.RepositoryCertificado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;


@Service
public class ServiceCertificado {

    @Autowired
    private RepositoryCertificado repositoryCertificado;

    public Page<AvaliacaoDTO> listarAvaliacoesPage(int page, int size, String filter, Integer status, String searchTerm) {
        Page<Object[]> resultados;
        PageRequest pageable = PageRequest.of(page, size);
        if (searchTerm != null && !searchTerm.isEmpty()) {
            resultados = repositoryCertificado.findAvaliacoesByEmpresaNome(searchTerm, pageable);
        } else {
            switch (filter) {
                case "MaisRecente":
                    resultados = repositoryCertificado.findAvaliacoesMaisRecentes(pageable);
                    break;
                case "MenosRecente":
                    resultados = repositoryCertificado.findAvaliacoesMenosRecentes(pageable);
                    break;
                case "OrdemEmpresa":
                    resultados = repositoryCertificado.findAvaliacoesOrdemEmpresa(pageable);
                    break;
                case "OrdemFuncionario":
                    resultados = repositoryCertificado.findAvaliacoesOrdemFuncionario(pageable);
                    break;
                case "base1":
                    resultados = repositoryCertificado.findAvaliacoesByStatus(1, pageable);
                    break;
                case "base2":
                    resultados = repositoryCertificado.findAvaliacoesByStatus(2, pageable);
                    break;
                case "base3":
                    resultados = repositoryCertificado.findAvaliacoesByStatus(3, pageable);
                    break;
                default:
                    resultados = repositoryCertificado.findAvaliacoesMaisRecentes(pageable);
            }
        }
        return resultados.map(this::convertToDTO);
    }

    private AvaliacaoDTO convertToDTO(Object[] resultado) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setBase((Integer) resultado[0]);
        dto.setUltima_mod((Date) resultado[1]);
        dto.setFantasia((String) resultado[2]);
        dto.setFantasia((String) resultado[3]);
        dto.setUsuario((String) resultado[4]);
        dto.setId_formulario((Long) resultado[5]);
        dto.setId_certificado((Long) resultado[6]);
        return dto;
    }
}
