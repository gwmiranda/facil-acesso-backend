-- Inserindo dados na tabela 'acessibilidade'
INSERT INTO acessibilidade (datacriacao, descricao, icon)
VALUES ('2023-10-16 10:00:00', 'Deficiência auditiva', 'deaf'),
       ('2023-10-15 09:30:00', 'Deficiência física', 'wheelchair-alt'),
       ('2023-10-14 14:15:00', 'Deficiência intelectual', 'user'),
       ('2023-10-13 12:45:00', 'Terceira idade', 'blind'),
       ('2023-10-13 12:45:00', 'Deficiência fono auditiva', 'asl-interpreting'),
       ('2023-10-13 12:45:00', 'Deficiência fonológica', 'microphone-slash'),
       ('2023-10-13 12:45:00', 'Deficiência visual', 'eye-slash'),
       ('2023-10-12 16:20:00', 'Gestante', 'child');

-- Inserindo dados na tabela 'tipoestabelecimento'
INSERT INTO tipoestabelecimento (datacriacao, descricao, icon)
VALUES ('2023-10-16 10:00:00', 'Comércio', 'shopping-cart'),
       ('2023-10-15 09:30:00', 'Gastronomia', 'birthday-cake'),
       ('2023-10-14 14:15:00', 'Hotel', 'hotel'),
       ('2023-10-13 12:45:00', 'Financeiro', 'bank'),
       ('2023-10-13 12:45:00', 'Educação', 'graduation-cap'),
       ('2023-10-12 16:20:00', 'Lazer', 'music');

-- Inserindo dados na tabela 'usuario'
INSERT INTO usuario (cpf, datacriacao, datanascimento, email, login, senha, telefone)
VALUES ('12345678901', '2023-10-16 10:00:00', '1990-01-01', 'user1@example.com', 'user1', '123', '123-456-7890'),
       ('23456789012', '2023-10-15 09:30:00', '1985-05-15', 'user2@example.com', 'user2', '123', '234-567-8901'),
       ('34567890123', '2023-10-14 14:15:00', '1995-11-30', 'user3@example.com', 'user3', '123', '345-678-9012'),
       ('45678901234', '2023-10-13 12:45:00', '1980-09-20', 'user4@example.com', 'user4', '123', '456-789-0123'),
       ('56789012345', '2023-10-12 16:20:00', '2000-03-10', 'user5@example.com', 'user5', '123', '567-890-1234');

-- Inserindo dados na tabela 'comentario'
INSERT INTO comentario (bairro, cep, cidade, comentario, complemento, datacriacao, estado, idcomentario,
                        nivelsatisfacao, numero, rua, estabelecimento_id, usuario_id, nomeestabelecimento)
VALUES ('Bairro1', '12345-678', 'Cidade1', 'Comentário 1', 'Complemento 1', '2023-10-16 10:00:00', 'Estado1', 1, 1, 123,
        'Rua1', 1, 1, 'Nome 1'),
       ('Bairro2', '23456-789', 'Cidade2', 'Comentário 2', 'Complemento 2', '2023-10-15 09:30:00', 'Estado2', 2, 2, 234,
        'Rua2', 2, 2, 'Nome 2'),
       ('Bairro3', '34567-890', 'Cidade3', 'Comentário 3', 'Complemento 3', '2023-10-14 14:15:00', 'Estado3', 3, 3, 345,
        'Rua3', 3, 3, 'Nome 3'),
       ('Bairro4', '45678-901', 'Cidade4', 'Comentário 4', 'Complemento 4', '2023-10-13 12:45:00', 'Estado4', 4, 1, 456,
        'Rua4', 4, 4, 'Nome 4'),
       ('Bairro5', '56789-012', 'Cidade5', 'Comentário 5', 'Complemento 5', '2023-10-12 16:20:00', 'Estado5', 5, 2, 567,
        'Rua5', 5, 5, 'Nome 5');

-- Inserindo dados na tabela 'comentario_acessibilidade'
INSERT INTO comentario_acessibilidade (comentario_id, acessibilidade_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);

-- Inserindo dados na tabela 'curtida'
INSERT INTO curtida (datacriacao, comentario_id, usuario_id)
VALUES ('2023-10-16 10:00:00', 1, 1),
       ('2023-10-15 09:30:00', 2, 2),
       ('2023-10-14 14:15:00', 3, 3),
       ('2023-10-13 12:45:00', 4, 4),
       ('2023-10-12 16:20:00', 5, 5);

-- Inserindo dados na tabela 'favorito'
INSERT INTO favorito (datacriacao, comentario_id, usuario_id)
VALUES ('2023-10-16 10:00:00', 1, 1),
       ('2023-10-15 09:30:00', 2, 2),
       ('2023-10-14 14:15:00', 3, 3),
       ('2023-10-13 12:45:00', 4, 4),
       ('2023-10-12 16:20:00', 5, 5);

-- Inserindo dados na tabela 'usuario_acessibilidade'
INSERT INTO usuario_acessibilidade (usuario_id, acessibilidade_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);
