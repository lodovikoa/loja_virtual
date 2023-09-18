ALTER TABLE tb_produto
ADD COLUMN nota_item_produto_id bigint NOT NULL,
ADD INDEX nota_item_produto_id_fk_idx (nota_item_produto_id ASC) VISIBLE;

ALTER TABLE tb_produto
ADD CONSTRAINT nota_item_produto_tb_produto_fk
  FOREIGN KEY (nota_item_produto_id)
  REFERENCES tb_nota_item_produto (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;