ALTER TABLE `loja_virtual_mentoria`.`tb_vd_cp_loja_virt`
    DROP FOREIGN KEY `nota_fiscal_venda_fk`;

ALTER TABLE `loja_virtual_mentoria`.`tb_vd_cp_loja_virt`
    CHANGE COLUMN `nota_fiscal_venda_id` `nota_fiscal_venda_id` BIGINT NULL ,
    DROP INDEX `UK_44dmdjcdoj4few4sapi741mpe` ;

ALTER TABLE `loja_virtual_mentoria`.`tb_vd_cp_loja_virt`
ADD CONSTRAINT `nota_fiscal_venda_fk`
  FOREIGN KEY (`nota_fiscal_venda_id`)
  REFERENCES `loja_virtual_mentoria`.`tb_nota_fiscal_venda` (`id`);