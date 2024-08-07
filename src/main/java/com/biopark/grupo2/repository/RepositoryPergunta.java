package com.biopark.grupo2.repository;

import com.biopark.grupo2.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryPergunta extends JpaRepository<Pergunta, Long> {

    @Query("SELECT p.titulo " +
           "FROM Pergunta p " +
           "WHERE p.formulario.id_formulario = :idFormulario " +
           "AND p.estado = true")

    List<String> findTitlesByFormId
            (@Param("idFormulario") Long idFormulario);

    @Query("SELECT p " +
           "FROM Pergunta p " +
           "WHERE p.formulario.id_formulario = :idFormulario")

    List<Pergunta> findPerguntasByFormularioId
            (@Param("idFormulario") Long idFormulario);
}


