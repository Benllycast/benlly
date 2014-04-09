-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 07-03-2014 a las 21:55:14
-- Versión del servidor: 5.5.16
-- Versión de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `benlly`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conductor`
--

CREATE TABLE IF NOT EXISTS `conductor` (
  `idConductor` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idConductor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `conductor`
--

INSERT INTO `conductor` (`idConductor`, `nombre`) VALUES
(1, 'benlly');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `idlog` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` date DEFAULT NULL,
  `numero_dato` int(11) DEFAULT NULL,
  `valor_obbtenido` int(11) DEFAULT NULL,
  `crc` varchar(45) DEFAULT NULL,
  `consecutivo` int(11) DEFAULT NULL,
  `recorridoorig_idrecorridoorig` int(11) NOT NULL,
  PRIMARY KEY (`idlog`),
  KEY `fk_log_recorridoorig1` (`recorridoorig_idrecorridoorig`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `log`
--

INSERT INTO `log` (`idlog`, `fecha`, `hora`, `numero_dato`, `valor_obbtenido`, `crc`, `consecutivo`, `recorridoorig_idrecorridoorig`) VALUES
(1, '2014-02-28', '0000-00-00', 2, 2, '2', 1, 1),
(2, '2014-02-28', '2014-02-28', 3, 3, '3', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recorridoorig`
--

CREATE TABLE IF NOT EXISTS `recorridoorig` (
  `idrecorridoorig` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `vehiculo_idvehiculo` int(11) NOT NULL,
  `conductor_idConductor` int(11) NOT NULL,
  `hora_salida` varchar(45) DEFAULT NULL,
  `hora_llegada` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrecorridoorig`),
  KEY `fk_recorridoorig_vehiculo1` (`vehiculo_idvehiculo`),
  KEY `fk_recorridoorig_conductor1` (`conductor_idConductor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `recorridoorig`
--

INSERT INTO `recorridoorig` (`idrecorridoorig`, `nombre`, `vehiculo_idvehiculo`, `conductor_idConductor`, `hora_salida`, `hora_llegada`) VALUES
(1, 'mañanero', 1, 1, '7', '9');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sensor`
--

CREATE TABLE IF NOT EXISTS `sensor` (
  `idsensor` int(11) NOT NULL,
  `valor` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `vehiculo_idvehiculo` int(11) NOT NULL,
  `consecutivo_vehiculo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idsensor`),
  KEY `fk_sensor_vehiculo1` (`vehiculo_idvehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `sensor`
--

INSERT INTO `sensor` (`idsensor`, `valor`, `tipo`, `vehiculo_idvehiculo`, `consecutivo_vehiculo`) VALUES
(1, '1', '1', 1, 1),
(2, '2', '2', 1, 2),
(3, '3', '3', 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE IF NOT EXISTS `vehiculo` (
  `idvehiculo` int(11) NOT NULL,
  `placa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idvehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`idvehiculo`, `placa`) VALUES
(1, '345');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `fk_log_recorridoorig1` FOREIGN KEY (`recorridoorig_idrecorridoorig`) REFERENCES `recorridoorig` (`idrecorridoorig`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `recorridoorig`
--
ALTER TABLE `recorridoorig`
  ADD CONSTRAINT `fk_recorridoorig_conductor1` FOREIGN KEY (`conductor_idConductor`) REFERENCES `conductor` (`idConductor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_recorridoorig_vehiculo1` FOREIGN KEY (`vehiculo_idvehiculo`) REFERENCES `vehiculo` (`idvehiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sensor`
--
ALTER TABLE `sensor`
  ADD CONSTRAINT `fk_sensor_vehiculo1` FOREIGN KEY (`vehiculo_idvehiculo`) REFERENCES `vehiculo` (`idvehiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
