package com.biopark.grupo2.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO {
    private Integer base;
    private Date ultima_mod;
    private String fantasia;
    private String usuario;
    private Long id_formulario;
    private Long id_certificado;
}
