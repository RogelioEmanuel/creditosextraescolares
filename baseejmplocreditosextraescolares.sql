-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-12-2023 a las 18:28:42
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `baseejmplocreditosextraescolares`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_registro_historico` ()   BEGIN
    -- Paso 2
    INSERT INTO registrohistorico (estado, tipo, nocontrolAlumno,periodo,nombreactividad,anio) SELECT  estado, tipo, noControlAlumno,periodo,NombreActividad,anio FROM creditosextraescolares 
    WHERE fecha_creacion < CURDATE() - INTERVAL 1 YEAR;

    -- Paso 3
    DELETE FROM creditosextraescolares
   WHERE fecha_creacion < CURDATE() - INTERVAL 1 YEAR;

    -- Eliminar de la tabla 'grupos_y_alumno'
    DELETE FROM grupos_y_alumno
   WHERE fecha_creacion < CURDATE() - INTERVAL 1 YEAR;

    -- Eliminar de la tabla 'horariosgrupo'
    DELETE FROM horariosgrupo
    WHERE fecha_creacion < CURDATE() - INTERVAL 1 YEAR;

    -- Eliminar de la tabla 'asistencia'
    DELETE FROM asistencia
    WHERE fecha_creacion < CURDATE() - INTERVAL 1 YEAR;
    
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad_extraescolar`
--

CREATE TABLE `actividad_extraescolar` (
  `idActividad_Extraescolar` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `actividad_extraescolar`
--

INSERT INTO `actividad_extraescolar` (`idActividad_Extraescolar`, `nombre`, `tipo`, `status`, `descripcion`, `fecha_creacion`) VALUES
(5, 'Hap', 'Deportivo', 1, 'Golpes', '2023-12-14 08:41:26');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `Nocontrol` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `semestre` int(11) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `regular` int(11) DEFAULT NULL,
  `correo` varchar(45) NOT NULL,
  `sexo` int(11) NOT NULL,
  `carrrera` varchar(10) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia`
--

CREATE TABLE `asistencia` (
  `idasistencia` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `idgrupo` int(11) DEFAULT NULL,
  `nocontrolAlumno` int(11) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `creditosextraescolares`
--

CREATE TABLE `creditosextraescolares` (
  `idCreditoExtraescolar` int(11) NOT NULL,
  `periodo` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `tipo` varchar(45) NOT NULL,
  `noControlAlumno` int(11) DEFAULT NULL,
  `Anio` int(11) NOT NULL,
  `NombreActividad` varchar(45) NOT NULL,
  `idGrupo` int(11) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `nombreAlumno` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE `eventos` (
  `idEventos` int(11) NOT NULL,
  `NombreEvento` varchar(100) NOT NULL,
  `numParticipantesH` int(11) DEFAULT 0,
  `numParticipantesM` int(11) DEFAULT 0,
  `InstitucionOrganizadora` varchar(255) DEFAULT NULL,
  `tipoevento` varchar(45) NOT NULL,
  `periodo` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `anio` int(11) NOT NULL,
  `resultado` varchar(200) NOT NULL,
  `idActividad` int(11) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `eventos`
--



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos`
--

CREATE TABLE `grupos` (
  `idGrupo` int(11) NOT NULL,
  `NoGrupo` int(11) NOT NULL,
  `cupo` int(11) DEFAULT NULL,
  `idActividad_extraescolar` int(11) DEFAULT NULL,
  `idMaestros` int(11) DEFAULT NULL COMMENT 'Pendiente de contratar',
  `periodo` varchar(45) NOT NULL,
  `totalhorassemanal` int(11) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos_y_alumno`
--

CREATE TABLE `grupos_y_alumno` (
  `idgruposyalumno` int(11) NOT NULL,
  `idgrupo` int(11) DEFAULT NULL,
  `nocontrolalumno` int(11) DEFAULT NULL,
  `selectivo` int(11) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horariosgrupo`
--

CREATE TABLE `horariosgrupo` (
  `idHorarioGrupo` int(11) NOT NULL,
  `idGrupo` int(11) NOT NULL,
  `Dia` varchar(45) NOT NULL,
  `HoraInicio` varchar(11) NOT NULL,
  `HoraFinal` varchar(11) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `maestros`
--

CREATE TABLE `maestros` (
  `idMaestros` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apPaterno` varchar(255) DEFAULT NULL,
  `apMaterno` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `correo` varchar(45) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `RFC` varchar(20) NOT NULL,
  `CURP` varchar(20) NOT NULL,
  `banco` varchar(45) NOT NULL,
  `clave` varchar(20) NOT NULL,
  `Sexo` char(1) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `maestros`
--



-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planesactividades`
--

CREATE TABLE `planesactividades` (
  `id_plan` int(11) NOT NULL,
  `idactividad_extraescolar` int(11) DEFAULT NULL,
  `idmaestro` int(11) DEFAULT NULL,
  `programa_semana_1` varchar(255) DEFAULT NULL,
  `plataforma_semana_1` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_1` varchar(255) DEFAULT NULL,
  `programa_semana_2` varchar(255) DEFAULT NULL,
  `plataforma_semana_2` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_2` varchar(255) DEFAULT NULL,
  `programa_semana_3` varchar(255) DEFAULT NULL,
  `plataforma_semana_3` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_3` varchar(255) DEFAULT NULL,
  `programa_semana_4` varchar(255) DEFAULT NULL,
  `plataforma_semana_4` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_4` varchar(255) DEFAULT NULL,
  `programa_semana_5` varchar(255) DEFAULT NULL,
  `plataforma_semana_5` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_5` varchar(255) DEFAULT NULL,
  `programa_semana_6` varchar(255) DEFAULT NULL,
  `plataforma_semana_6` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_6` varchar(255) DEFAULT NULL,
  `programa_semana_7` varchar(255) DEFAULT NULL,
  `plataforma_semana_7` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_7` varchar(255) DEFAULT NULL,
  `programa_semana_8` varchar(255) DEFAULT NULL,
  `plataforma_semana_8` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_8` varchar(255) DEFAULT NULL,
  `programa_semana_9` varchar(255) DEFAULT NULL,
  `plataforma_semana_9` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_9` varchar(255) DEFAULT NULL,
  `programa_semana_10` varchar(255) DEFAULT NULL,
  `plataforma_semana_10` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_10` varchar(255) DEFAULT NULL,
  `programa_semana_11` varchar(255) DEFAULT NULL,
  `plataforma_semana_11` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_11` varchar(255) DEFAULT NULL,
  `programa_semana_12` varchar(255) DEFAULT NULL,
  `plataforma_semana_12` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_12` varchar(255) DEFAULT NULL,
  `programa_semana_13` varchar(255) DEFAULT NULL,
  `plataforma_semana_13` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_13` varchar(255) DEFAULT NULL,
  `programa_semana_14` varchar(255) DEFAULT NULL,
  `plataforma_semana_14` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_14` varchar(255) DEFAULT NULL,
  `programa_semana_15` varchar(255) DEFAULT NULL,
  `plataforma_semana_15` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_15` varchar(255) DEFAULT NULL,
  `programa_semana_16` varchar(255) DEFAULT NULL,
  `plataforma_semana_16` varchar(255) DEFAULT NULL,
  `llevara_acabo_semana_16` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registrohistorico`
--

CREATE TABLE `registrohistorico` (
  `idRegistrohistorico` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) NOT NULL,
  `nocontrolAlumno` int(11) DEFAULT NULL,
  `periodo` varchar(20) NOT NULL,
  `nombreactividad` varchar(45) NOT NULL,
  `anio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `registrohistorico`
--

INSERT INTO `registrohistorico` (`idRegistrohistorico`, `estado`, `tipo`, `nocontrolAlumno`, `periodo`, `nombreactividad`, `anio`) VALUES
(3, '0', '', NULL, 'prueba', 'probando', 2023);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad_extraescolar`
--
ALTER TABLE `actividad_extraescolar`
  ADD PRIMARY KEY (`idActividad_Extraescolar`);

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`Nocontrol`);

--
-- Indices de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD PRIMARY KEY (`idasistencia`),
  ADD KEY `idgrupo` (`idgrupo`),
  ADD KEY `nocontrolAlumno` (`nocontrolAlumno`);

--
-- Indices de la tabla `creditosextraescolares`
--
ALTER TABLE `creditosextraescolares`
  ADD PRIMARY KEY (`idCreditoExtraescolar`),
  ADD KEY `noControlAlumno` (`noControlAlumno`);

--
-- Indices de la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`idEventos`);

--
-- Indices de la tabla `grupos`
--
ALTER TABLE `grupos`
  ADD PRIMARY KEY (`idGrupo`),
  ADD KEY `idActividad_extraescolarfk` (`idActividad_extraescolar`),
  ADD KEY `idMaestro_FK` (`idMaestros`);

--
-- Indices de la tabla `grupos_y_alumno`
--
ALTER TABLE `grupos_y_alumno`
  ADD PRIMARY KEY (`idgruposyalumno`),
  ADD KEY `idgrupo` (`idgrupo`),
  ADD KEY `nocontrolalumno` (`nocontrolalumno`);

--
-- Indices de la tabla `horariosgrupo`
--
ALTER TABLE `horariosgrupo`
  ADD PRIMARY KEY (`idHorarioGrupo`),
  ADD KEY `grupo_FK` (`idGrupo`);

--
-- Indices de la tabla `maestros`
--
ALTER TABLE `maestros`
  ADD PRIMARY KEY (`idMaestros`);

--
-- Indices de la tabla `planesactividades`
--
ALTER TABLE `planesactividades`
  ADD PRIMARY KEY (`id_plan`);

--
-- Indices de la tabla `registrohistorico`
--
ALTER TABLE `registrohistorico`
  ADD PRIMARY KEY (`idRegistrohistorico`),
  ADD KEY `nocontrolAlumno` (`nocontrolAlumno`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividad_extraescolar`
--
ALTER TABLE `actividad_extraescolar`
  MODIFY `idActividad_Extraescolar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `Nocontrol` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  MODIFY `idasistencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `creditosextraescolares`
--
ALTER TABLE `creditosextraescolares`
  MODIFY `idCreditoExtraescolar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `eventos`
--
ALTER TABLE `eventos`
  MODIFY `idEventos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `grupos`
--
ALTER TABLE `grupos`
  MODIFY `idGrupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de la tabla `grupos_y_alumno`
--
ALTER TABLE `grupos_y_alumno`
  MODIFY `idgruposyalumno` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `horariosgrupo`
--
ALTER TABLE `horariosgrupo`
  MODIFY `idHorarioGrupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT de la tabla `maestros`
--
ALTER TABLE `maestros`
  MODIFY `idMaestros` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `planesactividades`
--
ALTER TABLE `planesactividades`
  MODIFY `id_plan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `registrohistorico`
--
ALTER TABLE `registrohistorico`
  MODIFY `idRegistrohistorico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD CONSTRAINT `asistencia_ibfk_1` FOREIGN KEY (`idgrupo`) REFERENCES `grupos` (`idGrupo`),
  ADD CONSTRAINT `asistencia_ibfk_2` FOREIGN KEY (`nocontrolAlumno`) REFERENCES `alumnos` (`Nocontrol`);

--
-- Filtros para la tabla `creditosextraescolares`
--
ALTER TABLE `creditosextraescolares`
  ADD CONSTRAINT `creditosextraescolares_ibfk_1` FOREIGN KEY (`noControlAlumno`) REFERENCES `alumnos` (`Nocontrol`);

--
-- Filtros para la tabla `grupos`
--
ALTER TABLE `grupos`
  ADD CONSTRAINT `grupos_ibfk_1` FOREIGN KEY (`idActividad_extraescolar`) REFERENCES `actividad_extraescolar` (`idActividad_Extraescolar`),
  ADD CONSTRAINT `idActividad_extraescolar_fk` FOREIGN KEY (`idActividad_extraescolar`) REFERENCES `actividad_extraescolar` (`idActividad_Extraescolar`) ON DELETE SET NULL,
  ADD CONSTRAINT `idActividad_extraescolarfk` FOREIGN KEY (`idActividad_extraescolar`) REFERENCES `actividad_extraescolar` (`idActividad_Extraescolar`) ON DELETE SET NULL,
  ADD CONSTRAINT `idMaestro_FK` FOREIGN KEY (`idMaestros`) REFERENCES `maestros` (`idMaestros`) ON DELETE SET NULL;

--
-- Filtros para la tabla `grupos_y_alumno`
--
ALTER TABLE `grupos_y_alumno`
  ADD CONSTRAINT `grupos_y_alumno_ibfk_1` FOREIGN KEY (`idgrupo`) REFERENCES `grupos` (`idGrupo`),
  ADD CONSTRAINT `grupos_y_alumno_ibfk_2` FOREIGN KEY (`nocontrolalumno`) REFERENCES `alumnos` (`Nocontrol`);

--
-- Filtros para la tabla `horariosgrupo`
--
ALTER TABLE `horariosgrupo`
  ADD CONSTRAINT `grupo_FK` FOREIGN KEY (`idGrupo`) REFERENCES `grupos` (`idGrupo`) ON DELETE CASCADE,
  ADD CONSTRAINT `grupos_fk` FOREIGN KEY (`idGrupo`) REFERENCES `grupos` (`idGrupo`),
  ADD CONSTRAINT `idGrupo_FK` FOREIGN KEY (`idGrupo`) REFERENCES `grupos` (`idGrupo`) ON DELETE CASCADE;

--
-- Filtros para la tabla `registrohistorico`
--
ALTER TABLE `registrohistorico`
  ADD CONSTRAINT `registrohistorico_ibfk_2` FOREIGN KEY (`nocontrolAlumno`) REFERENCES `alumnos` (`Nocontrol`);

DELIMITER $$
--
-- Eventos
--
CREATE DEFINER=`root`@`localhost` EVENT `registrohistorico` ON SCHEDULE EVERY 1 DAY STARTS '2023-12-14 11:28:22' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    
    CALL insertar_registro_historico();
END$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
