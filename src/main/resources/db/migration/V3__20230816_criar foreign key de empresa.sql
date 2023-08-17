ALTER TABLE tb_avaliacao_produto
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_categoria_produto
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_conta_pagar
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_conta_receber
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_cupom_desconto
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_endereco
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_forma_pagamento
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_imagem_produto
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_item_venda_loja
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_marca_produto
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_nota_fiscal_compra
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_nota_fiscal_venda
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_nota_item_produto
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_pessoa_fisica
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_pessoa_juridica
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_produto
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_status_rastreio
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_usuario
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;

ALTER TABLE tb_vd_cp_loja_virt
ADD COLUMN empresa_id bigint NOT NULL,
ADD INDEX empresa_id_fk_idx (empresa_id ASC) VISIBLE;