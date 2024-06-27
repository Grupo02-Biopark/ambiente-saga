package com.biopark.grupo2.model;

import jakarta.persistence.*;
import lombok.*;

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
    private long id_resposta;
    private Integer resposta;

    @ManyToOne
    @JoinColumn(name = "id_formulario")
    private Formulario id_formulario;
    @ManyToOne
    @JoinColumn(name = "id_pergunta")
    private Pergunta id_pergunta;
    @ManyToOne
    @JoinColumn(name = "id_certificado")
    private Certificado id_certificado;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario id_usuario;
}
