package com.biopark.grupo2.model;

import jakarta.persistence.*;
import lombok.*;

@Table (name = "Usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String cidade;
    private String cpf;
    private String telefone;
    private boolean estado;
}


