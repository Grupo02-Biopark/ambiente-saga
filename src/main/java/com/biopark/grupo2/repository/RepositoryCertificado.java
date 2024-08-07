package com.biopark.grupo2.repository;

import com.biopark.grupo2.model.Certificado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryCertificado extends JpaRepository<Certificado, Long> {
    @Query(value = "SELECT DISTINCT f.base, r.ultima_mod AS data, " +
                   "f.titulo AS form, " +
                   "e.fantasia, u.nome AS usuario, " +
                   "f.id_formulario, c.id_certificado AS cert " +
                   "FROM certificado c " +
                   "JOIN empresa e ON e.id_empresa = c.id_empresa " +
                   "JOIN formulario f ON c.id_formulario = f.id_formulario " +
                   "JOIN resposta r ON r.id_formulario = f.id_formulario " +
                   "JOIN usuario u ON r.id_usuario = u.id_usuario " +
                   "WHERE e.fantasia LIKE %:empresa% " +
                   "AND (f.id_formulario, r.ultima_mod) " +
                   "IN (SELECT r2.id_formulario, MAX(r2.ultima_mod) " +
                   "FROM resposta r2 GROUP BY r2.id_formulario) " +
                   "ORDER BY e.fantasia ASC",
                   nativeQuery = true)

    Page<Object[]> findAvaliacoesByEmpresaNome
            (@Param("empresa") String empresa, Pageable pageable);

    @Query(value = "SELECT DISTINCT f.base, r.ultima_mod AS data, " +
                   "f.titulo AS form, " +
                   "e.fantasia, u.nome AS usuario, " +
                   "f.id_formulario, c.id_certificado AS cert " +
                   "FROM certificado c " +
                   "JOIN empresa e ON e.id_empresa = c.id_empresa " +
                   "JOIN formulario f ON c.id_formulario = f.id_formulario " +
                   "JOIN resposta r ON r.id_formulario = f.id_formulario " +
                   "JOIN usuario u ON r.id_usuario = u.id_usuario " +
                   "WHERE (f.id_formulario, r.ultima_mod) " +
                   "IN (SELECT r2.id_formulario, MAX(r2.ultima_mod) " +
                   "FROM resposta r2 GROUP BY r2.id_formulario) " +
                   "ORDER BY r.ultima_mod DESC",
                    nativeQuery = true)

    Page<Object[]> findAvaliacoesMaisRecentes(Pageable pageable);

    @Query(value = "SELECT DISTINCT f.base, r.ultima_mod AS data, " +
                   "f.titulo AS form, e.fantasia, u.nome AS usuario, f.id_formulario, c.id_certificado AS cert " +
                   "FROM certificado c " +
                   "JOIN empresa e ON e.id_empresa = c.id_empresa " +
                   "JOIN formulario f ON c.id_formulario = f.id_formulario " +
                   "JOIN resposta r ON r.id_formulario = f.id_formulario " +
                   "JOIN usuario u ON r.id_usuario = u.id_usuario " +
                   "WHERE (f.id_formulario, r.ultima_mod) " +
                   "IN (SELECT r2.id_formulario, MAX(r2.ultima_mod) " +
                   "FROM resposta r2 GROUP BY r2.id_formulario) " +
                   "ORDER BY r.ultima_mod ASC",
                    nativeQuery = true)

    Page<Object[]> findAvaliacoesMenosRecentes(Pageable pageable);

    @Query(value = "SELECT DISTINCT f.base, r.ultima_mod AS data, " +
                   "f.titulo AS form, " +
                   "e.fantasia, u.nome AS usuario, " +
                   "f.id_formulario, c.id_certificado AS cert " +
                   "FROM certificado c " +
                   "JOIN empresa e ON e.id_empresa = c.id_empresa " +
                   "JOIN formulario f ON c.id_formulario = f.id_formulario " +
                   "JOIN resposta r ON r.id_formulario = f.id_formulario " +
                   "JOIN usuario u ON r.id_usuario = u.id_usuario " +
                   "WHERE (f.id_formulario, r.ultima_mod) " +
                   "IN (SELECT r2.id_formulario, MAX(r2.ultima_mod) " +
                   "FROM resposta r2 " +
                   "GROUP BY r2.id_formulario) " +
                   "ORDER BY e.fantasia ASC",
                    nativeQuery = true)

    Page<Object[]> findAvaliacoesOrdemEmpresa(Pageable pageable);

    @Query(value = "SELECT DISTINCT f.base, r.ultima_mod AS data, " +
                   "f.titulo AS form, " +
                   "e.fantasia, u.nome AS usuario, " +
                   "f.id_formulario, c.id_certificado AS cert " +
                   "FROM certificado c " +
                   "JOIN empresa e ON e.id_empresa = c.id_empresa " +
                   "JOIN formulario f ON c.id_formulario = f.id_formulario " +
                   "JOIN resposta r ON r.id_formulario = f.id_formulario " +
                   "JOIN usuario u ON r.id_usuario = u.id_usuario " +
                   "WHERE (f.id_formulario, r.ultima_mod) " +
                   "IN (SELECT r2.id_formulario, MAX(r2.ultima_mod) " +
                   "FROM resposta r2 " +
                   "GROUP BY r2.id_formulario) " +
                   "ORDER BY u.nome ASC",
                    nativeQuery = true)

    Page<Object[]> findAvaliacoesOrdemFuncionario(Pageable pageable);

    @Query(value = "SELECT DISTINCT f.base, r.ultima_mod AS data, " +
                   "f.titulo AS form, " +
                   "e.fantasia, u.nome AS usuario, " +
                   "f.id_formulario, c.id_certificado AS cert " +
                   "FROM certificado c " +
                   "JOIN empresa e ON e.id_empresa = c.id_empresa " +
                   "JOIN formulario f ON c.id_formulario = f.id_formulario " +
                   "JOIN resposta r ON r.id_formulario = f.id_formulario " +
                   "JOIN usuario u ON r.id_usuario = u.id_usuario " +
                   "WHERE f.base = :status AND (f.id_formulario, r.ultima_mod) " +
                   "IN (SELECT r2.id_formulario, MAX(r2.ultima_mod) " +
                   "FROM resposta r2 " +
                   "GROUP BY r2.id_formulario)",
                    nativeQuery = true)

    Page<Object[]> findAvaliacoesByStatus
            (@Param("status") int status, Pageable pageable);

}
