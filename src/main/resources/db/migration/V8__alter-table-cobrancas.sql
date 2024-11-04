alter table cobrancas
add ativo tinyint;
update cobrancas
set ativo = 1;