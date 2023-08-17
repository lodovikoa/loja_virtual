ALTER TABLE tb_avaliacao_produto
ADD CONSTRAINT empresa_tb_avaliacao_produto_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_categoria_produto
ADD CONSTRAINT empresa_tb_categoria_produto_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_conta_pagar
ADD CONSTRAINT empresa_tb_conta_pagar_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_conta_receber
ADD CONSTRAINT empresa_tb_conta_receber_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_cupom_desconto
ADD CONSTRAINT empresa_tb_cupom_desconto_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_endereco
ADD CONSTRAINT empresa_tb_endereco_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_forma_pagamento
ADD CONSTRAINT empresa_tb_forma_pagamento_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_imagem_produto
ADD CONSTRAINT empresa_tb_imagem_produto_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_item_venda_loja
ADD CONSTRAINT empresa_tb_item_venda_loja_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_marca_produto
ADD CONSTRAINT empresa_tb_marca_produto_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_nota_fiscal_compra
ADD CONSTRAINT empresa_tb_nota_fiscal_compra_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_nota_fiscal_venda
ADD CONSTRAINT empresa_tb_nota_fiscal_venda_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_nota_item_produto
ADD CONSTRAINT empresa_tb_nota_item_produto_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_pessoa_fisica
ADD CONSTRAINT empresa_tb_pessoa_fisica_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_pessoa_juridica
ADD CONSTRAINT empresa_tb_pessoa_juridica_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tb_produto
ADD CONSTRAINT empresa_tb_produto_fk
  FOREIGN KEY (empresa_id)
  REFERENCES tb_pessoa_juridica (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

 ALTER TABLE tb_status_rastreio
 ADD CONSTRAINT empresa_tb_status_rastreio_fk
   FOREIGN KEY (empresa_id)
   REFERENCES tb_pessoa_juridica (id)
   ON DELETE RESTRICT
   ON UPDATE RESTRICT;

 ALTER TABLE tb_usuario
 ADD CONSTRAINT empresa_tb_usuario_fk
   FOREIGN KEY (empresa_id)
   REFERENCES tb_pessoa_juridica (id)
   ON DELETE RESTRICT
   ON UPDATE RESTRICT;

 ALTER TABLE tb_vd_cp_loja_virt
 ADD CONSTRAINT empresa_tb_vd_cp_loja_virt_fk
   FOREIGN KEY (empresa_id)
   REFERENCES tb_pessoa_juridica (id)
   ON DELETE RESTRICT
   ON UPDATE RESTRICT;