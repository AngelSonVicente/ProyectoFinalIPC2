

CREATE TABLE  `categorias` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `decripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




CREATE TABLE  `empresa` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `cod_usuario` int NOT NULL,
  `mision` varchar(200) NOT NULL,
  `vision` varchar(200) NOT NULL,
  `titular_tarjeta` varchar(50) NOT NULL,
  `no_tarjeta` bigint NOT NULL,
  `codigo_seguridad` int NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `cod_usuario` (`cod_usuario`)
) 


CREATE TABLE  `entrevistas` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `codigo_oferta` int NOT NULL,
  `usuario` int NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `ubicacion` varchar(100) NOT NULL,
  `estado` varchar(30) NOT NULL,
  `notas` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `usuario` (`usuario`),
  KEY `codigo_oferta` (`codigo_oferta`)
)

CREATE TABLE  `historial_cobros` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `codigo_empresa` int NOT NULL,
  `monto` float NOT NULL,
  `fecha` date NOT NULL,
  `codigo_oferta` int DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_empresa` (`codigo_empresa`)
) 
CREATE TABLE  `invitados` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `cantidad_invitados` int NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE  `notificaciones` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `codigoUsuario` int DEFAULT NULL,
  `codigoUsuarioDestino` int NOT NULL,
  `codigoOferta` int NOT NULL,
  `contenido` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigoUsuario` (`codigoUsuario`),
  KEY `codigoUsuarioDestino` (`codigoUsuarioDestino`),
  KEY `codigoOferta` (`codigoOferta`)
) 


CREATE TABLE  `ofertas` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `codigo_empresa` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `categoria` int NOT NULL,
  `estado` varchar(30) NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `fecha_limite` date NOT NULL,
  `salario` float NOT NULL,
  `modalidad` varchar(30) NOT NULL,
  `ubicacion` varchar(100) NOT NULL,
  `detalles` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `usuario_elegido` int DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `categoria` (`categoria`),
  KEY `usuario_elegido` (`usuario_elegido`),
  KEY `codigo_empresa` (`codigo_empresa`)
)

CREATE TABLE  `ofertas_eliminadas` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `codigo_oferta` int NOT NULL,
  `motivo` varchar(200) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_oferta` (`codigo_oferta`)
) 


CREATE TABLE  `parametros` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `comision` float NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`codigo`)
) 

CREATE TABLE  `preferencias` (
  `codigo_usuario` int NOT NULL,
  `codigo_categoria` int NOT NULL,
  KEY `codigo_usuario` (`codigo_usuario`),
  KEY `codigo_categoria` (`codigo_categoria`)
)


CREATE TABLE  `solicitudes` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `codigo_oferta` int NOT NULL,
  `codigo_usuario` int NOT NULL,
  `mensaje` varchar(200) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_usuario` (`codigo_usuario`),
  KEY `codigo_oferta` (`codigo_oferta`)
)

CREATE TABLE  `solicitudes_retiradas` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `codigo_usuario` int NOT NULL,
  `codigo_oferta` int NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_usuario` (`codigo_usuario`),
  KEY `codigo_oferta` (`codigo_oferta`)
)

CREATE TABLE `telefonos` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `cod_usuario` int NOT NULL,
  `telefono` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `cod_usuario` (`cod_usuario`)
) 

CREATE TABLE IF `tokens` (
  `codigoUsuario` int NOT NULL,
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  KEY `codigoUsuario` (`codigoUsuario`)
) 

CREATE TABLE `usuarios` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `correo` varchar(70) NOT NULL,
  `cui` varchar(20) NOT NULL,
  `birth` date NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `cv` mediumblob,
  PRIMARY KEY (`codigo`)
) 
ALTER TABLE `empresa`
  ADD CONSTRAINT `empresa_ibfk_1` FOREIGN KEY (`cod_usuario`) REFERENCES `usuarios` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `entrevistas`
  ADD CONSTRAINT `entrevistas_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `entrevistas_ibfk_2` FOREIGN KEY (`codigo_oferta`) REFERENCES `ofertas` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `historial_cobros`
  ADD CONSTRAINT `historial_cobros_ibfk_1` FOREIGN KEY (`codigo_empresa`) REFERENCES `usuarios` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `notificaciones`
  ADD CONSTRAINT `notificaciones_ibfk_1` FOREIGN KEY (`codigoUsuario`) REFERENCES `usuarios` (`codigo`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `notificaciones_ibfk_2` FOREIGN KEY (`codigoUsuarioDestino`) REFERENCES `usuarios` (`codigo`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `notificaciones_ibfk_3` FOREIGN KEY (`codigoOferta`) REFERENCES `ofertas` (`codigo`) ON DELETE RESTRICT ON UPDATE RESTRICT;


ALTER TABLE `ofertas`
  ADD CONSTRAINT `ofertas_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`codigo`),
  ADD CONSTRAINT `ofertas_ibfk_2` FOREIGN KEY (`usuario_elegido`) REFERENCES `usuarios` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ofertas_ibfk_3` FOREIGN KEY (`codigo_empresa`) REFERENCES `usuarios` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `ofertas_eliminadas`
  ADD CONSTRAINT `ofertas_eliminadas_ibfk_1` FOREIGN KEY (`codigo_oferta`) REFERENCES `ofertas` (`codigo`) ON DELETE RESTRICT ON UPDATE RESTRICT;


ALTER TABLE `preferencias`
  ADD CONSTRAINT `preferencias_ibfk_1` FOREIGN KEY (`codigo_categoria`) REFERENCES `categorias` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `preferencias_ibfk_2` FOREIGN KEY (`codigo_usuario`) REFERENCES `usuarios` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `solicitudes`
  ADD CONSTRAINT `solicitudes_ibfk_1` FOREIGN KEY (`codigo_usuario`) REFERENCES `usuarios` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `solicitudes_ibfk_2` FOREIGN KEY (`codigo_oferta`) REFERENCES `ofertas` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `solicitudes_retiradas`
  ADD CONSTRAINT `solicitudes_retiradas_ibfk_1` FOREIGN KEY (`codigo_usuario`) REFERENCES `usuarios` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `solicitudes_retiradas_ibfk_2` FOREIGN KEY (`codigo_oferta`) REFERENCES `ofertas` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `telefonos`
  ADD CONSTRAINT `telefonos_ibfk_1` FOREIGN KEY (`cod_usuario`) REFERENCES `usuarios` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `tokens`
  ADD CONSTRAINT `tokens_ibfk_1` FOREIGN KEY (`codigoUsuario`) REFERENCES `usuarios` (`codigo`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

