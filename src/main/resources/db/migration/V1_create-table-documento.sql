CREATE TABLE Documento (
    id_documento INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nomeDocumento VARCHAR(2049) NOT NULL,
    validado BOOLEAN DEFAULT true,
    id_resposta INT NOT NULL,
    FOREIGN KEY (id_resposta) REFERENCES Resposta(id_resposta)
)