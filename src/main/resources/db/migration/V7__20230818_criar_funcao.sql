DELIMITER //
create function validaChavePessoa(sqPessoa bigint)
	returns int
    DETERMINISTIC
    begin
		declare existe int;
        select count(1) into existe from tb_pessoa_fisica where id = sqPessoa;
        if(existe <= 0) then
			select count(1) into existe from tb_pessoa_juridica where id = sqPessoa;
       end if;

        return existe;
    end //
DELIMITER ;