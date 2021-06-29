insert into cozinha (nome) values ('Tailandesa')
insert into cozinha (nome) values ('Indiana')
insert into cozinha (nome) values ('Portuguesa')
insert into cozinha (nome) values ('Italiana')

insert into restaurante (nome, taxa_frete, cozinha_id) values ('La pizzaria do João', 1.96, 1)
insert into restaurante (nome, taxa_frete, cozinha_id) values ('La casa del Maria', 5.96,3)
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Arrumadinho da API', 0.00,4)
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Frete da API', 0.00,1)

insert into estado (nome) values ('Piauí')
insert into estado (nome) values ('Maranhão')
insert into estado (nome) values ('Ceará')

insert into cidade (nome, estado_id) values ('Teresina', 1)
insert into cidade (nome, estado_id) values ('São Luis', 2)
insert into cidade (nome, estado_id) values ('Fortaleza', 3)
insert into cidade (nome, estado_id) values ('São Raimundo Nonato', 1)
