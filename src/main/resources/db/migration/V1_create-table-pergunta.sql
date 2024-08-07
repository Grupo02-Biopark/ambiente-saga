CREATE TABLE Pergunta (
    id_pergunta INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255),
    documento BOOLEAN NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    id_formulario INT NOT NULL,
    FOREIGN KEY (id_formulario) REFERENCES Formulario(id_formulario)
),

INSERT INTO Pergunta (titulo, documento, estado, id_formulario) VALUES ('Qual é o seu nome?', TRUE, TRUE, 1);
INSERT INTO Pergunta (titulo, documento, estado, id_formulario) VALUES ('Qual é a sua idade?', FALSE, TRUE, 1);
INSERT INTO Pergunta (titulo, documento, estado, id_formulario) VALUES ('Qual é o seu endereço?', TRUE, TRUE, 1);
INSERT INTO Pergunta (titulo, documento, estado, id_formulario) VALUES ('Você possui algum documento de identidade?', FALSE, TRUE, 1);
INSERT INTO Pergunta (titulo, documento, estado, id_formulario) VALUES ('Qual é a sua profissão?', FALSE, TRUE, 1);
INSERT INTO Pergunta (titulo, documento, estado, id_formulario) VALUES ('Qual é o seu número de telefone?', TRUE, TRUE, 1);
INSERT INTO Pergunta (titulo, documento, estado, id_formulario) VALUES ('Você é residente permanente?', FALSE, TRUE, 4);
INSERT INTO Pergunta (titulo, documento, estado, id_formulario) VALUES ('Por quanto tempo você está na cidade?', TRUE, TRUE, 1);
INSERT INTO Pergunta (titulo, documento, estado, id_formulario) VALUES ('Você tem alguma doença preexistente?', FALSE, TRUE, 1);
INSERT INTO Pergunta (titulo, documento, estado, id_formulario) VALUES ('Qual é o nome do seu médico?', TRUE, TRUE, 1);
