CREATE TABLE tb_acesso_end_point (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome_end_point VARCHAR(100) NOT NULL,
  qtde_acessos INT NOT NULL,
  ultimo_acesso DATETIME NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX nome_end_point_UNIQUE (nome_end_point ASC) VISIBLE)
ENGINE = InnoDB;