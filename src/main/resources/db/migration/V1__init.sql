-- Copia pela linha de comando
-- mysqldump -d -u root -p loja_virtual_mentoria > loja_virtual_mentoria.sql

-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: loja_virtual_mentoria
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sq_pessoa`
--

DROP TABLE IF EXISTS `sq_pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sq_pessoa` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_acesso`
--

DROP TABLE IF EXISTS `tb_acesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_acesso` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_avaliacao_produto`
--

DROP TABLE IF EXISTS `tb_avaliacao_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_avaliacao_produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `nota` int NOT NULL,
  `pessoa_id` bigint NOT NULL,
  `produto_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `produto_avaliacao_fk` (`produto_id`),
  CONSTRAINT `produto_avaliacao_fk` FOREIGN KEY (`produto_id`) REFERENCES `tb_produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_avaliacao_produto_validaPessoaInsert` BEFORE INSERT ON `tb_avaliacao_produto` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL; -- For├ºa dar erro
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_avaliacao_produto_validaPessoaUpdate` BEFORE UPDATE ON `tb_avaliacao_produto` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tb_categoria_produto`
--

DROP TABLE IF EXISTS `tb_categoria_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_categoria_produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome_desc` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_conta_pagar`
--

DROP TABLE IF EXISTS `tb_conta_pagar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_conta_pagar` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `dt_pagamento` date DEFAULT NULL,
  `dt_vencimento` date NOT NULL,
  `status` enum('ABERTA','ALUGUEL','COBRANCA','FUNCIONARIO','NEGOCIADA','QUITADA','VENCIDA') NOT NULL,
  `valor_desconto` decimal(38,2) DEFAULT NULL,
  `valor_total` decimal(38,2) NOT NULL,
  `pessoa_id` bigint NOT NULL,
  `pessoa_forn_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_conta_pagar_validaPessoaInsert` BEFORE INSERT ON `tb_conta_pagar` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_conta_pagar_validaPessoaFornInsert` BEFORE INSERT ON `tb_conta_pagar` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_forn_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_forn_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_conta_pagar_validaPessoaUpdate` BEFORE UPDATE ON `tb_conta_pagar` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_conta_pagar_validaPessoaFornUpdate` BEFORE UPDATE ON `tb_conta_pagar` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_forn_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_forn_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tb_conta_receber`
--

DROP TABLE IF EXISTS `tb_conta_receber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_conta_receber` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `dt_pagamento` date DEFAULT NULL,
  `dt_vencimento` date NOT NULL,
  `status` enum('ABERTA','COBRANCA','QUITADA','VENCIDA') NOT NULL,
  `valor_desconto` decimal(38,2) DEFAULT NULL,
  `valor_total` decimal(38,2) NOT NULL,
  `pessoa_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_conta_receber_validaPessoaInsert` BEFORE INSERT ON `tb_conta_receber` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_conta_receber_validaPessoaUpdate` BEFORE UPDATE ON `tb_conta_receber` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tb_cupom_desconto`
--

DROP TABLE IF EXISTS `tb_cupom_desconto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cupom_desconto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_desc` varchar(255) NOT NULL,
  `data_validade_cupom` date NOT NULL,
  `valor_porcent_desc` decimal(38,2) DEFAULT NULL,
  `valor_real_desc` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_endereco`
--

DROP TABLE IF EXISTS `tb_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_endereco` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) NOT NULL,
  `cep` varchar(255) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `numero` varchar(255) NOT NULL,
  `rua_logra` varchar(255) NOT NULL,
  `tipo_endereco` enum('COBRANCA','ENTREGA') NOT NULL,
  `uf` varchar(255) NOT NULL,
  `pessoa_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_endereco_validaPessoaInsert` BEFORE INSERT ON `tb_endereco` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_endereco_validaPessoaUpdate` BEFORE UPDATE ON `tb_endereco` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tb_forma_pagamento`
--

DROP TABLE IF EXISTS `tb_forma_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_forma_pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_imagem_produto`
--

DROP TABLE IF EXISTS `tb_imagem_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_imagem_produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `imagem_miniatura` text NOT NULL,
  `imagem_original` text NOT NULL,
  `produto_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `produto_fk` (`produto_id`),
  CONSTRAINT `produto_fk` FOREIGN KEY (`produto_id`) REFERENCES `tb_produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_item_venda_loja`
--

DROP TABLE IF EXISTS `tb_item_venda_loja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_item_venda_loja` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantidade` double NOT NULL,
  `produto_id` bigint NOT NULL,
  `venda_compra_loja_virt_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `produto_item_venda_fk` (`produto_id`),
  KEY `venda_compra_loja_virt_item_venda_fk` (`venda_compra_loja_virt_id`),
  CONSTRAINT `produto_item_venda_fk` FOREIGN KEY (`produto_id`) REFERENCES `tb_produto` (`id`),
  CONSTRAINT `venda_compra_loja_virt_item_venda_fk` FOREIGN KEY (`venda_compra_loja_virt_id`) REFERENCES `tb_vd_cp_loja_virt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_marca_produto`
--

DROP TABLE IF EXISTS `tb_marca_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_marca_produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome_desc` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_nota_fiscal_compra`
--

DROP TABLE IF EXISTS `tb_nota_fiscal_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_nota_fiscal_compra` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_compra` date NOT NULL,
  `descricao_obs` varchar(255) DEFAULT NULL,
  `numero_nota` varchar(255) NOT NULL,
  `serie_nota` varchar(255) NOT NULL,
  `valor_desconto` decimal(38,2) DEFAULT NULL,
  `valor_icms` decimal(38,2) NOT NULL,
  `valor_total` decimal(38,2) NOT NULL,
  `conta_pagar_id` bigint NOT NULL,
  `pessoa_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conta_pagar_fk` (`conta_pagar_id`),
  CONSTRAINT `conta_pagar_fk` FOREIGN KEY (`conta_pagar_id`) REFERENCES `tb_conta_pagar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_nota_fiscal_compra_validaPessoaInsert` BEFORE INSERT ON `tb_nota_fiscal_compra` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_nota_fiscal_compra_validaPessoaUpdate` BEFORE UPDATE ON `tb_nota_fiscal_compra` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tb_nota_fiscal_venda`
--

DROP TABLE IF EXISTS `tb_nota_fiscal_venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_nota_fiscal_venda` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `numero` varchar(255) NOT NULL,
  `pdf` text NOT NULL,
  `serie` varchar(255) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `xml` text NOT NULL,
  `venda_compra_loja_virt_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1j6jegfpl8rx97rxg74hxdcbd` (`venda_compra_loja_virt_id`),
  CONSTRAINT `venda_compra_loja_virt_fk` FOREIGN KEY (`venda_compra_loja_virt_id`) REFERENCES `tb_vd_cp_loja_virt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_nota_item_produto`
--

DROP TABLE IF EXISTS `tb_nota_item_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_nota_item_produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantidade` double NOT NULL,
  `nota_fiscal_compra_id` bigint NOT NULL,
  `produto_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nota_fiscal_compra_fk` (`nota_fiscal_compra_id`),
  KEY `produto_nota_item_fk` (`produto_id`),
  CONSTRAINT `nota_fiscal_compra_fk` FOREIGN KEY (`nota_fiscal_compra_id`) REFERENCES `tb_nota_fiscal_compra` (`id`),
  CONSTRAINT `produto_nota_item_fk` FOREIGN KEY (`produto_id`) REFERENCES `tb_produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_pessoa_fisica`
--

DROP TABLE IF EXISTS `tb_pessoa_fisica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pessoa_fisica` (
  `id` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_pessoa_juridica`
--

DROP TABLE IF EXISTS `tb_pessoa_juridica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pessoa_juridica` (
  `id` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `cnpj` varchar(255) NOT NULL,
  `insc_estadual` varchar(255) NOT NULL,
  `insc_municipal` varchar(255) DEFAULT NULL,
  `nome_fantasia` varchar(255) DEFAULT NULL,
  `razao_social` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_produto`
--

DROP TABLE IF EXISTS `tb_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alerta_qtd_estoque` bit(1) DEFAULT NULL,
  `altura` double NOT NULL,
  `ativo` bit(1) NOT NULL,
  `descricao` text NOT NULL,
  `largura` double NOT NULL,
  `link_youtube` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `peso` double NOT NULL,
  `profundidade` double NOT NULL,
  `qtd_alerta_estoque` int DEFAULT NULL,
  `qtd_clique` int DEFAULT NULL,
  `qtd_estoque` int NOT NULL,
  `tipo_unidade` varchar(255) NOT NULL,
  `valor_venda` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_status_rastreio`
--

DROP TABLE IF EXISTS `tb_status_rastreio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_status_rastreio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `centro_distribuicao` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `venda_compra_loja_virt_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `venda_compra_loja_virt_status_rastreio_fk` (`venda_compra_loja_virt_id`),
  CONSTRAINT `venda_compra_loja_virt_status_rastreio_fk` FOREIGN KEY (`venda_compra_loja_virt_id`) REFERENCES `tb_vd_cp_loja_virt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_atual_senha` date NOT NULL,
  `login` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `pessoa_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_usuario_validaPessoaInsert` BEFORE INSERT ON `tb_usuario` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tb_usuario_validaPessoaUpdate` BEFORE UPDATE ON `tb_usuario` FOR EACH ROW begin
	set @valor = 0;
	select validaChavePessoa(NEW.pessoa_id) into @valor;

    if(@valor <= 0) then
		SET NEW.pessoa_id = NULL;
	end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tb_usuario_acesso`
--

DROP TABLE IF EXISTS `tb_usuario_acesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario_acesso` (
  `usuario_id` bigint NOT NULL,
  `acesso_id` bigint NOT NULL,
  UNIQUE KEY `unique_acesso_user` (`usuario_id`,`acesso_id`),
  KEY `acesso_fk` (`acesso_id`),
  CONSTRAINT `acesso_fk` FOREIGN KEY (`acesso_id`) REFERENCES `tb_acesso` (`id`),
  CONSTRAINT `usuario_fk` FOREIGN KEY (`usuario_id`) REFERENCES `tb_usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_vd_cp_loja_virt`
--

DROP TABLE IF EXISTS `tb_vd_cp_loja_virt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_vd_cp_loja_virt` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_entrega` date NOT NULL,
  `data_venda` date NOT NULL,
  `dia_entrega` varchar(255) NOT NULL,
  `valor_desconto` decimal(38,2) DEFAULT NULL,
  `valor_frete` decimal(38,2) NOT NULL,
  `valor_total` decimal(38,2) NOT NULL,
  `cupom_desconto_id` bigint DEFAULT NULL,
  `endereco_cobranca_id` bigint NOT NULL,
  `endereco_entrega_id` bigint NOT NULL,
  `forma_pagamento_id` bigint NOT NULL,
  `nota_fiscal_venda_id` bigint NOT NULL,
  `pessoa_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_44dmdjcdoj4few4sapi741mpe` (`nota_fiscal_venda_id`),
  KEY `cupom_desconto_fk` (`cupom_desconto_id`),
  KEY `endereco_cobranca_fk` (`endereco_cobranca_id`),
  KEY `endereco_entrega_fk` (`endereco_entrega_id`),
  KEY `forma_pagamento_fk` (`forma_pagamento_id`),
  CONSTRAINT `cupom_desconto_fk` FOREIGN KEY (`cupom_desconto_id`) REFERENCES `tb_cupom_desconto` (`id`),
  CONSTRAINT `endereco_cobranca_fk` FOREIGN KEY (`endereco_cobranca_id`) REFERENCES `tb_endereco` (`id`),
  CONSTRAINT `endereco_entrega_fk` FOREIGN KEY (`endereco_entrega_id`) REFERENCES `tb_endereco` (`id`),
  CONSTRAINT `forma_pagamento_fk` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `tb_forma_pagamento` (`id`),
  CONSTRAINT `nota_fiscal_venda_fk` FOREIGN KEY (`nota_fiscal_venda_id`) REFERENCES `tb_nota_fiscal_venda` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-04 22:30:07
