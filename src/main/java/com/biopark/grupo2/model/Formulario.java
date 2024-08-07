package com.biopark.grupo2.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "Formulario")
@Entity(name = "Formulario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_formulario")
public class Formulario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_formulario;
    private String titulo;
    private Integer base;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @OneToMany(mappedBy = "formulario")
    private List<Pergunta> perguntas;

}
