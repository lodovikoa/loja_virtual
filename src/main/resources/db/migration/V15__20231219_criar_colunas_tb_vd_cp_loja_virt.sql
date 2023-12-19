ALTER TABLE `loja_virtual_mentoria`.`tb_vd_cp_loja_virt` ADD COLUMN `codigo_etiqueta` VARCHAR(255) AFTER `empresa_id`;
ALTER TABLE `loja_virtual_mentoria`.`tb_vd_cp_loja_virt` ADD COLUMN `url_imprime_etiqueta` VARCHAR(255) AFTER `codigo_etiqueta`;
ALTER TABLE `loja_virtual_mentoria`.`tb_vd_cp_loja_virt` ADD COLUMN `servico_transportadora` VARCHAR(255) AFTER `url_imprime_etiqueta`;