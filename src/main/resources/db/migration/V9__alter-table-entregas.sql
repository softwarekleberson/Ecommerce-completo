alter table entregas
add ativo tinyint;
update entregas
set ativo = 1;