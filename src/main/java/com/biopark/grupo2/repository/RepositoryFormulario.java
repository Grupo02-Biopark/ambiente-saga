package com.biopark.grupo2.repository;
import com.biopark.grupo2.model.Formulario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryFormulario extends JpaRepository<Formulario, Long> {
    @Query("SELECT p " +
           "FROM Formulario p " +
           "WHERE p.id_formulario = (SELECT MAX(p2.id_formulario) " +
           "FROM Formulario p2)")

    Formulario consultaMaiorId();

    @Query("SELECT f " +
           "FROM Formulario f " +
           "WHERE f.titulo " +
           "LIKE %:formulario%")

    Page<Formulario> findFormularioByNome
            (@Param("formulario") String formulario, Pageable pageable);

    //Page<Formulario> findFormularioByNomeAndEstado(String searchTerm, Boolean estado, Pageable pageable);

    @Query("SELECT f " +
           "FROM Formulario f " +
           "WHERE f.estado = true " +
           "AND f.base = 0")

    Page<Formulario> findPaginadoAtivo(Pageable pageable);


    @Query("SELECT f " +
           "FROM Formulario f " +
           "WHERE f.estado = false " +
           "AND f.base = 0")

    Page<Formulario> findPaginadoInativo(Pageable pageable);

    List<Formulario>findByBase(Integer base);

}
