ALTER TABLE `loja_virtual_mentoria`.`tb_nota_fiscal_venda`
DROP FOREIGN KEY `venda_compra_loja_virt_fk`;

ALTER TABLE `loja_virtual_mentoria`.`tb_nota_fiscal_venda`
CHANGE COLUMN `venda_compra_loja_virt_id` `venda_compra_loja_virt_id` BIGINT NULL ,
DROP INDEX `UK_1j6jegfpl8rx97rxg74hxdcbd` ;
;
ALTER TABLE `loja_virtual_mentoria`.`tb_nota_fiscal_venda`
ADD CONSTRAINT `venda_compra_loja_virt_fk`
  FOREIGN KEY (`venda_compra_loja_virt_id`)
  REFERENCES `loja_virtual_mentoria`.`tb_vd_cp_loja_virt` (`id`);