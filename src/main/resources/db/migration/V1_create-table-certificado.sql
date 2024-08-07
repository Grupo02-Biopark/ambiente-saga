CREATE TABLE Certificado (
    id_certificado INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    vencimento DATE,
    resultado DOUBLE,
    id_formulario INT NOT NULL,
    id_empresa INT NOT NULL,
    FOREIGN KEY (id_formulario) REFERENCES Formulario(id_formulario),
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa)
);

INSERT INTO Certificado (vencimento, resultado, id_formulario, id_empresa)
VALUES ('2024-12-31', 95.5, 6, 1);
