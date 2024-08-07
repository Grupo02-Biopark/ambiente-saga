package com.biopark.grupo2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Table(name = "Certificado")
@Entity(name = "Certificado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_certificado")
public class Certificado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_certificado;
    private Date vencimento;
    private double resultado;

    @JoinColumn(name = "id_formulario")
    private Long id_formulario;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @OneToMany(mappedBy = "certificado")
    private Set<Resposta> respostas;

}
