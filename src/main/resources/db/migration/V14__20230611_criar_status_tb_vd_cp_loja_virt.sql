ALTER TABLE `loja_virtual_mentoria`.`tb_vd_cp_loja_virt`
ADD COLUMN `status_venda_loja_virtual` enum('FINALIZADA','CANCELADA','ABANDONADA') AFTER `nota_fiscal_venda_id`;