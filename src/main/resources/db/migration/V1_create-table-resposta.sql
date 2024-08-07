CREATE TABLE Resposta (
    id_resposta INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    resposta INT NOT NULL,
    ultima_mod DATE,
    id_usuario INT NOT NULL,
    id_pergunta INT NOT NULL,
    id_formulario INT NOT NULL,
    id_certificado INT NOT NULL,
    id_empresa INT NOT NULL,
    FOREIGN KEY (id_pergunta) REFERENCES Pergunta(id_pergunta),
    FOREIGN KEY (id_formulario) REFERENCES Formulario(id_formulario),
    FOREIGN KEY (id_certificado) REFERENCES Certificado(id_certificado)
)