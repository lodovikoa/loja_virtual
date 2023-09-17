ALTER TABLE tb_produto
ADD COLUMN categoria_produto_id bigint NOT NULL,
ADD INDEX categoria_produto_id_fk_idx (categoria_produto_id ASC) VISIBLE;

ALTER TABLE tb_produto
ADD COLUMN marca_produto_id bigint NOT NULL,
ADD INDEX marca_produto_id_fk_idx (marca_produto_id ASC) VISIBLE;

ALTER TABLE tb_produto
ADD CONSTRAINT categoria_produto_tb_produto_fk
  FOREIGN KEY (categoria_produto_id)
  REFERENCES tb_categoria_produto (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE tb_produto
ADD CONSTRAINT marca_produto_tb_produto_fk
  FOREIGN KEY (marca_produto_id)
  REFERENCES tb_marca_produto (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;