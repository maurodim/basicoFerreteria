-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-03-2017 a las 18:34:39
-- Versión del servidor: 10.1.9-MariaDB
-- Versión de PHP: 7.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bbgestion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `ID` int(11) NOT NULL,
  `BARRAS` varchar(30) DEFAULT NULL,
  `NOMBRE` varchar(49) DEFAULT NULL,
  `SERVICIO` double DEFAULT NULL,
  `COSTO` double DEFAULT NULL,
  `PRECIO` double DEFAULT NULL,
  `MINIMO` int(11) DEFAULT NULL,
  `STOCK` int(11) DEFAULT NULL,
  `PROVEEDOR` int(11) DEFAULT NULL,
  `RUBRON` varchar(12) DEFAULT NULL,
  `ALTA` varchar(19) DEFAULT NULL,
  `INHABILITADO` int(11) NOT NULL DEFAULT '0',
  `idRubro` int(11) NOT NULL DEFAULT '1',
  `equivalencia` double NOT NULL DEFAULT '1',
  `modificaPrecio` int(11) NOT NULL DEFAULT '0',
  `modificaServicio` int(11) NOT NULL DEFAULT '0',
  `recargo` double NOT NULL DEFAULT '0',
  `servicio1` double DEFAULT '0',
  `idcombo` int(11) DEFAULT '0',
  `actualizacion` int(11) NOT NULL DEFAULT '0',
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `billetes`
--

CREATE TABLE `billetes` (
  `id` int(11) NOT NULL DEFAULT '0',
  `descripcion` varchar(30) NOT NULL DEFAULT '',
  `valor` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caja`
--

CREATE TABLE `caja` (
  `numero` bigint(11) NOT NULL DEFAULT '0',
  `numeroSucursal` int(11) NOT NULL DEFAULT '0',
  `numeroUsuario` int(11) NOT NULL DEFAULT '0',
  `tipoMovimiento` int(11) NOT NULL DEFAULT '0',
  `saldoinicial` double NOT NULL DEFAULT '0',
  `estado` int(11) NOT NULL DEFAULT '0',
  `tipo` int(11) NOT NULL DEFAULT '0',
  `saldofinal` double DEFAULT NULL,
  `fechacierre` varchar(30) DEFAULT NULL,
  `diferencia` double DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coeficienteslistas`
--

CREATE TABLE `coeficienteslistas` (
  `id` int(11) NOT NULL DEFAULT '0',
  `coeficiente` double NOT NULL DEFAULT '0',
  `descripcion` varchar(30) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `coeficienteslistas`
--

INSERT INTO `coeficienteslistas` (`id`, `coeficiente`, `descripcion`) VALUES
(1, 1, 'base');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `combo`
--

CREATE TABLE `combo` (
  `id` int(11) NOT NULL DEFAULT '0',
  `fecha` date DEFAULT NULL,
  `idarticulo` int(11) NOT NULL DEFAULT '0',
  `cantidad` double DEFAULT NULL,
  `articulopadre` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobantes`
--

CREATE TABLE `comprobantes` (
  `numero` int(11) NOT NULL DEFAULT '0',
  `descripcion` varchar(4) NOT NULL DEFAULT '',
  `destinatarioCondicion` int(11) NOT NULL DEFAULT '0',
  `descargaStock` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `depositos`
--

CREATE TABLE `depositos` (
  `numero` int(11) NOT NULL DEFAULT '0',
  `descripcion` varchar(100) NOT NULL DEFAULT '',
  `direccion` varchar(200) NOT NULL DEFAULT '',
  `telefono` varchar(200) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `depositos`
--

INSERT INTO `depositos` (`numero`, `descripcion`, `direccion`, `telefono`) VALUES
(1, 'casa central', 'lamadradid 2552', '4555555');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fallas`
--

CREATE TABLE `fallas` (
  `st` text NOT NULL,
  `estado` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fiscal`
--

CREATE TABLE `fiscal` (
  `fecha` varchar(8) NOT NULL DEFAULT '',
  `tipo` varchar(3) NOT NULL DEFAULT '011',
  `pto` varchar(5) NOT NULL DEFAULT '00002',
  `numero` varchar(20) NOT NULL DEFAULT '',
  `gravado` double NOT NULL DEFAULT '0',
  `alicuota` varchar(4) NOT NULL DEFAULT '0005',
  `impuesto` double NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL,
  `total` double NOT NULL DEFAULT '0',
  `fechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` int(11) NOT NULL DEFAULT '0',
  `idcliente` int(11) NOT NULL DEFAULT '1',
  `tipoClienteId` int(11) NOT NULL DEFAULT '99',
  `razon` varchar(30) NOT NULL DEFAULT '',
  `cuit` varchar(20) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ivaventas`
--

CREATE TABLE `ivaventas` (
  `id` int(11) NOT NULL,
  `comprobante` varchar(4) NOT NULL,
  `fecha` varchar(10) NOT NULL,
  `numero` varchar(14) NOT NULL,
  `idcliente` int(11) NOT NULL,
  `cliente` varchar(35) NOT NULL,
  `condicion` varchar(4) NOT NULL,
  `cuit` varchar(11) NOT NULL,
  `neto` varchar(15) NOT NULL,
  `iva` varchar(15) NOT NULL,
  `total` varchar(15) NOT NULL,
  `periodo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `listcli`
--

CREATE TABLE `listcli` (
  `COD_CLIENT` varchar(6) DEFAULT NULL,
  `RAZON_SOCI` varchar(60) DEFAULT NULL,
  `DOMICILIO` varchar(30) DEFAULT NULL,
  `COND_VTA` int(11) NOT NULL DEFAULT '0',
  `TELEFONO_1` varchar(30) DEFAULT NULL,
  `LISTADEPRECIO` int(11) NOT NULL DEFAULT '0',
  `NUMERODECUIT` varchar(30) DEFAULT NULL,
  `CUPODECREDITO` double DEFAULT NULL,
  `empresa` varchar(3) DEFAULT NULL,
  `codmmd` int(11) NOT NULL DEFAULT '0',
  `saldo` double DEFAULT '0',
  `saldoactual` double DEFAULT '0',
  `TIPO_IVA` int(11) NOT NULL DEFAULT '0',
  `COEFICIENTE` int(11) NOT NULL DEFAULT '1',
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `listcli`
--

INSERT INTO `listcli` (`COD_CLIENT`, `RAZON_SOCI`, `DOMICILIO`, `COND_VTA`, `TELEFONO_1`, `LISTADEPRECIO`, `NUMERODECUIT`, `CUPODECREDITO`, `empresa`, `codmmd`, `saldo`, `saldoactual`, `TIPO_IVA`, `COEFICIENTE`, `id`) VALUES
('999999', 'CONSUMIDOR FINAL', 'NN', 1, '11', 1, '1', 0, 'sd', 1, 0, 0, 1, 1, 1),
('2', 'CAF 21', 'PADRE QUIROGA 2230', 1, '12', 1, '33678412649', NULL, 'sd', 2, 0, 0, 1, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientosarticulos`
--

CREATE TABLE `movimientosarticulos` (
  `tipoMovimiento` int(11) NOT NULL DEFAULT '0',
  `idArticulo` int(11) NOT NULL DEFAULT '0',
  `cantidad` double NOT NULL DEFAULT '0',
  `numeroDeposito` int(11) NOT NULL DEFAULT '0',
  `tipoComprobante` int(11) NOT NULL DEFAULT '0',
  `numeroComprobante` int(11) NOT NULL DEFAULT '0',
  `numeroCliente` int(11) NOT NULL DEFAULT '0',
  `fechaComprobante` varchar(30) NOT NULL DEFAULT '',
  `numeroUsuario` int(11) NOT NULL DEFAULT '0',
  `precioDeCosto` double NOT NULL DEFAULT '0',
  `precioDeVenta` double NOT NULL DEFAULT '0',
  `precioServicio` double NOT NULL DEFAULT '0',
  `estado` int(11) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idcaja` bigint(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `idcomprobante` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientoscaja`
--

CREATE TABLE `movimientoscaja` (
  `numeroUsuario` int(11) NOT NULL DEFAULT '0',
  `idCliente` int(11) DEFAULT NULL,
  `numeroSucursal` int(11) NOT NULL DEFAULT '0',
  `numeroComprobante` int(11) NOT NULL DEFAULT '0',
  `tipoComprobante` int(11) NOT NULL DEFAULT '0',
  `monto` double DEFAULT NULL,
  `tipoMovimiento` int(11) NOT NULL DEFAULT '0',
  `idCaja` int(11) NOT NULL DEFAULT '0',
  `cantidad` double DEFAULT NULL,
  `pagado` int(11) NOT NULL DEFAULT '0',
  `observaciones` varchar(100) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `tipoCliente` int(11) DEFAULT NULL,
  `id` bigint(11) NOT NULL,
  `fiscal` int(11) NOT NULL DEFAULT '0',
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientosclientes`
--

CREATE TABLE `movimientosclientes` (
  `numeroProveedor` int(11) NOT NULL DEFAULT '0',
  `monto` double NOT NULL DEFAULT '0',
  `pagado` int(11) DEFAULT NULL,
  `numeroComprobante` int(11) DEFAULT NULL,
  `idRemito` int(11) DEFAULT NULL,
  `idUsuario` int(11) NOT NULL DEFAULT '0',
  `idCaja` int(11) NOT NULL DEFAULT '0',
  `fechaPago` varchar(20) DEFAULT NULL,
  `tipoComprobante` int(11) DEFAULT NULL,
  `idSucursal` int(11) NOT NULL DEFAULT '0',
  `estado` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `idcomprobante` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientosdesucursales`
--

CREATE TABLE `movimientosdesucursales` (
  `depOrigen` int(11) DEFAULT NULL,
  `depDestino` int(11) DEFAULT NULL,
  `idArticulo` int(11) NOT NULL DEFAULT '0',
  `cantidad` double NOT NULL DEFAULT '0',
  `confirmado` int(11) DEFAULT NULL,
  `numeroRemito` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `diferencia` double DEFAULT NULL,
  `idUsuarioRecep` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientosproveedores`
--

CREATE TABLE `movimientosproveedores` (
  `numeroProveedor` int(11) NOT NULL DEFAULT '0',
  `monto` double NOT NULL DEFAULT '0',
  `pagado` int(11) DEFAULT NULL,
  `numeroComprobante` varchar(30) DEFAULT NULL,
  `idRemito` int(11) DEFAULT NULL,
  `idUsuario` int(11) NOT NULL DEFAULT '0',
  `idCaja` int(11) NOT NULL DEFAULT '0',
  `fechaPago` varchar(20) DEFAULT NULL,
  `tipoComprobante` int(11) DEFAULT NULL,
  `idSucursal` int(11) NOT NULL DEFAULT '0',
  `estado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientosusuarios`
--

CREATE TABLE `movimientosusuarios` (
  `numeroUsuario` int(11) NOT NULL DEFAULT '0',
  `tipoAcceso` int(11) DEFAULT NULL,
  `entrada` varchar(30) NOT NULL DEFAULT '',
  `estado` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `ID` int(11) DEFAULT NULL,
  `nombre` varchar(19) DEFAULT NULL,
  `DOMICILIO` varchar(100) DEFAULT NULL,
  `LOCALIDAD` varchar(8) DEFAULT NULL,
  `TELEFONO` varchar(10) DEFAULT NULL,
  `INHABILITADO` int(11) DEFAULT NULL,
  `numero` int(11) NOT NULL DEFAULT '0',
  `mail` varchar(200) NOT NULL DEFAULT '',
  `saldo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE `sucursal` (
  `numero` int(11) NOT NULL DEFAULT '0',
  `descripcion` varchar(100) NOT NULL DEFAULT '',
  `direccion` varchar(100) NOT NULL DEFAULT '',
  `telefono` varchar(100) NOT NULL DEFAULT '',
  `deposito` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `sucursal`
--

INSERT INTO `sucursal` (`numero`, `descripcion`, `direccion`, `telefono`, `deposito`) VALUES
(1, 'POCO PRECIO 1', '1', '', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoacceso`
--

CREATE TABLE `tipoacceso` (
  `numero` int(11) NOT NULL DEFAULT '0',
  `descripcion` varchar(20) NOT NULL DEFAULT '',
  `nivel` int(11) NOT NULL DEFAULT '0',
  `menu1` int(11) NOT NULL DEFAULT '0',
  `menu2` int(11) NOT NULL DEFAULT '0',
  `menu3` int(11) NOT NULL DEFAULT '0',
  `menu4` int(11) NOT NULL DEFAULT '0',
  `menu5` int(11) NOT NULL DEFAULT '0',
  `menu6` int(11) NOT NULL DEFAULT '0',
  `menu7` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipoacceso`
--

INSERT INTO `tipoacceso` (`numero`, `descripcion`, `nivel`, `menu1`, `menu2`, `menu3`, `menu4`, `menu5`, `menu6`, `menu7`) VALUES
(1, 'administrador', 1, 1, 1, 1, 1, 1, 1, 1),
(2, 'cajero', 2, 0, 0, 1, 1, 0, 1, 0),
(3, 'prueba', 3, 0, 0, 1, 1, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocomprobantes`
--

CREATE TABLE `tipocomprobantes` (
  `numero` int(11) NOT NULL DEFAULT '0',
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  `numeroActivo` int(11) NOT NULL DEFAULT '0',
  `numeroSucursal` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipocomprobantes`
--

INSERT INTO `tipocomprobantes` (`numero`, `descripcion`, `numeroActivo`, `numeroSucursal`) VALUES
(1, 'ticket', 0, 1),
(2, 'FCA A', 0, 1),
(3, 'remito proveedor', 0, 1),
(4, 'remito interno', 0, 1),
(5, 'factura proveedor A', 0, 1),
(6, 'factura proveedor', 0, 1),
(7, 'ticket', 0, 2),
(8, 'FCA A', 0, 2),
(9, 'ticket', 0, 3),
(10, 'FCA A', 0, 3),
(11, 'recibo de pago', 0, 1),
(12, 'mov caja', 1, 0),
(13, 'gasto fijo', 0, 1),
(14, 'ticket', 0, 4),
(15, 'FCA A', 0, 4),
(16, 'ticket', 0, 5),
(17, 'FCA A', 0, 5),
(18, 'remito de ajuste de stock', 0, 1),
(19, 'TICKET', 0, 7),
(20, 'FACTURA PROVEEDOR', 0, 7),
(21, 'FCA A', 0, 7),
(22, 'ticket', 0, 6),
(23, 'FCA A', 0, 6),
(81, 'TFA', 0, 1),
(82, 'TFB', 0, 1),
(112, 'NCA', 0, 1),
(113, 'NCB', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipomovimientos`
--

CREATE TABLE `tipomovimientos` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `DESCRIPCION` varchar(30) NOT NULL DEFAULT '',
  `DESTINOOPERACION` int(11) NOT NULL DEFAULT '0',
  `MULTIPLOOP` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipomovimientos`
--

INSERT INTO `tipomovimientos` (`ID`, `DESCRIPCION`, `DESTINOOPERACION`, `MULTIPLOOP`) VALUES
(1, 'Ventas', 1, 1),
(2, 'Compras', 1, 1),
(3, 'Devolucion de Ventas', 1, 1),
(4, 'Retiro Efectivo', 2, -1),
(5, 'Recepcion de mercaderias', 1, 1),
(6, 'Traslado de mercaderias', 1, 1),
(7, 'Ingreso de Caja', 2, 1),
(8, 'Devolucion de compras', 1, 1),
(9, 'saldo inicial', 2, 1),
(10, 'Cerrar Caja', 2, -1),
(11, 'Pago a Proveedores', 2, -1),
(12, 'Gastos de Caja', 2, -1),
(13, 'Cobro de Cuenta Corriente Clie', 2, 1),
(14, 'ajuste de stock', 1, 1),
(15, 'ajuste de saldo', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `numero` int(11) NOT NULL DEFAULT '0',
  `nombre` varchar(70) NOT NULL DEFAULT '',
  `direccion` varchar(200) NOT NULL DEFAULT '',
  `localidad` varchar(70) NOT NULL DEFAULT '',
  `telefono` varchar(100) NOT NULL DEFAULT '',
  `mail` varchar(100) NOT NULL DEFAULT '',
  `nombreUsuario` varchar(100) NOT NULL DEFAULT '',
  `clave` varchar(100) NOT NULL DEFAULT '',
  `autorizacion` int(11) NOT NULL DEFAULT '0',
  `numeroTipoAcceso` int(11) NOT NULL DEFAULT '0',
  `sucursal` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`numero`, `nombre`, `direccion`, `localidad`, `telefono`, `mail`, `nombreUsuario`, `clave`, `autorizacion`, `numeroTipoAcceso`, `sucursal`) VALUES
(1, 'administrador', '', '', '', '', 'ADM', 'adm', 1, 1, 1),
(2, 'ELIEL', '', '', '', '', 'ELIEL', 'ELIEL', 2, 2, 7);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `billetes`
--
ALTER TABLE `billetes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `caja`
--
ALTER TABLE `caja`
  ADD PRIMARY KEY (`numero`);

--
-- Indices de la tabla `coeficienteslistas`
--
ALTER TABLE `coeficienteslistas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `combo`
--
ALTER TABLE `combo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `comprobantes`
--
ALTER TABLE `comprobantes`
  ADD PRIMARY KEY (`numero`);

--
-- Indices de la tabla `depositos`
--
ALTER TABLE `depositos`
  ADD PRIMARY KEY (`numero`);

--
-- Indices de la tabla `fiscal`
--
ALTER TABLE `fiscal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `numero_2` (`numero`);

--
-- Indices de la tabla `ivaventas`
--
ALTER TABLE `ivaventas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `listcli`
--
ALTER TABLE `listcli`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `codmmd` (`codmmd`);

--
-- Indices de la tabla `movimientosarticulos`
--
ALTER TABLE `movimientosarticulos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `movimientoscaja`
--
ALTER TABLE `movimientoscaja`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fecha` (`fecha`);

--
-- Indices de la tabla `movimientosclientes`
--
ALTER TABLE `movimientosclientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`numero`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`numero`);

--
-- Indices de la tabla `tipoacceso`
--
ALTER TABLE `tipoacceso`
  ADD PRIMARY KEY (`numero`);

--
-- Indices de la tabla `tipocomprobantes`
--
ALTER TABLE `tipocomprobantes`
  ADD PRIMARY KEY (`numero`);

--
-- Indices de la tabla `tipomovimientos`
--
ALTER TABLE `tipomovimientos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`numero`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulos`
--
ALTER TABLE `articulos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `fiscal`
--
ALTER TABLE `fiscal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ivaventas`
--
ALTER TABLE `ivaventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `listcli`
--
ALTER TABLE `listcli`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `movimientosarticulos`
--
ALTER TABLE `movimientosarticulos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `movimientoscaja`
--
ALTER TABLE `movimientoscaja`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `movimientosclientes`
--
ALTER TABLE `movimientosclientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
