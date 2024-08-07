package com.biopark.grupo2.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Documentos")
@Entity(name = "Documentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_documento")
public class Documentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_documento;
    private String nomeDocumento;

    @ManyToOne
    @JoinColumn(name = "id_resposta")
    private Resposta id_resposta;
}
