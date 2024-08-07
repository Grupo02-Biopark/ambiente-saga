package com.biopark.grupo2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "Resposta")
@Entity(name = "Resposta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_resposta")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_resposta;
    private Integer resposta;
    private Long id_usuario;
    private Date ultima_mod;

    @JoinColumn(name = "id_pergunta")
    private Long id_pergunta;

    @JoinColumn(name = "id_documento")
    private Long id_documento;

    @ManyToOne
    @JoinColumn(name = "id_formulario")
    private Formulario formulario;
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "id_certificado")
    private Certificado certificado;
}
