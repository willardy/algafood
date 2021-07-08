INSERT INTO cozinha (nome) values ('Tailandesa')
INSERT INTO cozinha (nome) values ('Indiana')
INSERT INTO cozinha (nome) values ('Portuguesa')
INSERT INTO cozinha (nome) values ('Italiana')

INSERT INTO estado (nome) values ('Piauí')
INSERT INTO estado (nome) values ('Maranhão')
INSERT INTO estado (nome) values ('Ceará')

INSERT INTO cidade (nome, estado_id) values ('Teresina', 1)
INSERT INTO cidade (nome, estado_id) values ('São Luis', 2)
INSERT INTO cidade (nome, estado_id) values ('Fortaleza', 3)
INSERT INTO cidade (nome, estado_id) values ('São Raimundo Nonato', 1)

INSERT INTO restaurante (data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) VALUES (utc_timestamp, utc_timestamp, 'Ininga', '987654-480', 'Perto daquele lugar', '', '4355', 'La France del peru', 9.55, 1, 1);
INSERT INTO restaurante (data_cadastro, data_atualizacao, nome, taxa_frete, cozinha_id) values (utc_timestamp, utc_timestamp, 'La pizzaria do João', 1.96, 1)
INSERT INTO restaurante (data_cadastro, data_atualizacao, nome, taxa_frete, cozinha_id) values (utc_timestamp, utc_timestamp, 'La casa del Maria', 5.96,3)
INSERT INTO restaurante (data_cadastro, data_atualizacao, nome, taxa_frete, cozinha_id) values (utc_timestamp, utc_timestamp, 'Arrumadinho da API', 0.00,4)
INSERT INTO restaurante (data_cadastro, data_atualizacao, nome, taxa_frete, cozinha_id) values (utc_timestamp, utc_timestamp, 'Frete da API', 0.00,1)

INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de Crédito')
INSERT INTO forma_pagamento (descricao) VALUES ('Cartão de Débito')
INSERT INTO forma_pagamento (descricao) VALUES ('Dinheiro')

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1,1), (1,2), (1,3), (2,2), (3,1), (4,2), (4,3)