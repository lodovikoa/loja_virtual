ALTER TABLE tb_pessoa_fisica ADD COLUMN tipo_pessoa VARCHAR(255) NULL AFTER id;
ALTER TABLE tb_pessoa_juridica ADD COLUMN tipo_pessoa VARCHAR(255) NULL AFTER id;