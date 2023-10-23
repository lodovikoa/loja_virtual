ALTER TABLE `loja_virtual_mentoria`.`tb_vd_cp_loja_virt`
ADD COLUMN `excluido` TINYINT NOT NULL DEFAULT 0 AFTER `empresa_id`;