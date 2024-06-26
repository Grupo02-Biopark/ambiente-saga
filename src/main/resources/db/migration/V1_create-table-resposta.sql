CREATE TABLE Resposta (
    id_resposta INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    resposta INT NOT NULL,
    observacao VARCHAR(500),
    id_usuario INT NOT NULL,
    id_pergunta INT NOT NULL,
    id_formulario INT NOT NULL,
    id_certificado INT NOT NULL,
    ultimaMod DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_pergunta) REFERENCES Pergunta(id_pergunta),
    FOREIGN KEY (id_formulario) REFERENCES Formulario(id_formulario),
    FOREIGN KEY (id_certificado) REFERENCES Certificado(id_certificado)
)