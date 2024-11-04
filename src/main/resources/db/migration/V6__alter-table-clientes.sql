alter table clientes
add ativo tinyint;
update clientes
set ativo = 1;