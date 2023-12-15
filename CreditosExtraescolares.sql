-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-12-2023 a las 21:26:47
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

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`Nocontrol`, `nombre`, `semestre`, `edad`, `regular`, `correo`, `sexo`, `carrrera`, `fecha_creacion`) VALUES
(17280330, 'Rogelio', 2, 13, 1, 'rvalenciag@toluca.tecnm.mx', 1, 'ISC', '2023-12-14 17:43:13');

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

INSERT INTO `eventos` (`idEventos`, `NombreEvento`, `numParticipantesH`, `numParticipantesM`, `InstitucionOrganizadora`, `tipoevento`, `periodo`, `fecha`, `anio`, `resultado`, `idActividad`, `fecha_creacion`) VALUES
(1, 'Conferencia de Tecnología', 50, 30, 'Universidad XYZ', 'externo', 'Semestral', '2023-03-15', 2023, '', 101, '2023-01-01 06:00:00'),
(3, 'Taller de Emprendimiento', 40, 20, 'Asociación Empresarial', 'externo', 'Trimestral', '2023-04-10', 2023, '', 103, '2023-01-03 14:45:00'),
(4, 'Competencia de Robótica', 60, 40, 'Escuela de Ingeniería', 'Interno', 'Enero-Junio', '2023-12-07', 0, 'Prueba2', 5, '2023-01-04 21:00:00'),
(5, 'Encuentro Cultural', 120, 80, 'Casa de la Cultura', 'externo', 'Mensual', '2023-02-05', 2023, '', 105, '2023-01-05 16:30:00'),
(6, 'Concurso de Pintura', 30, 25, 'Centro de Arte', 'Interno', 'Enero-Junio', '2023-12-07', 0, '', 5, '2023-01-07 00:20:00'),
(7, 'Seminario de Salud Mental', 70, 45, 'Hospital General', 'externo', 'Trimestral', '2023-05-22', 2023, '', 107, '2023-01-07 20:15:00'),
(8, 'Festival de Jazz', 100, 60, 'Sociedad Musical', 'externo', 'Anual', '2023-08-30', 2023, '', 108, '2023-01-09 02:45:00'),
(10, 'Foro de Innovación', 80, 40, 'Centro de Innovación', 'externo', 'Mensual', '2023-03-05', 2023, '', 110, '2023-01-10 18:00:00'),
(12, 'Charla sobre Sostenibilidad', 60, 35, 'Organización Ambiental', 'Interno', 'Enero-Junio', NULL, 0, 'uwu', 5, '2023-01-12 17:20:00');

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

--
-- Volcado de datos para la tabla `grupos`
--

INSERT INTO `grupos` (`idGrupo`, `NoGrupo`, `cupo`, `idActividad_extraescolar`, `idMaestros`, `periodo`, `totalhorassemanal`, `fecha_creacion`) VALUES
(39, 2313, 50, 5, 28, 'Enero-Junio', 1, '2023-12-15 06:21:06');

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

--
-- Volcado de datos para la tabla `horariosgrupo`
--

INSERT INTO `horariosgrupo` (`idHorarioGrupo`, `idGrupo`, `Dia`, `HoraInicio`, `HoraFinal`, `fecha_creacion`) VALUES
(53, 39, 'lunes', '00:21', '01:21', '2023-12-15 06:21:06');

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

INSERT INTO `maestros` (`idMaestros`, `nombre`, `apPaterno`, `apMaterno`, `fecha_nacimiento`, `correo`, `telefono`, `RFC`, `CURP`, `banco`, `clave`, `Sexo`, `direccion`, `fecha_creacion`) VALUES
(28, 'rogelio', 'Valencia', 'valencia', '2023-01-19', 'rogii40@gmail.com', '7292805506', 'VAGR991113HMC', 'VAGR991113HMCLNG08', 'Bancomer', '1234567897894561', 'M', 'dfghjkfd', '2023-12-13 22:16:25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planestrabajoactividades`
--

CREATE TABLE `planestrabajoactividades` (
  `id_plan` int(11) NOT NULL,
  `id_Actividad_Extraescolar` int(11) DEFAULT NULL,
  `idMaestros` int(11) DEFAULT NULL
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
(3, '0', '', NULL, 'prueba', 'probando', 2023),
(4, 'Liberado', 'Civico', 17280330, 'Enero-Junio', 'Hap ki do', 2023);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `semanasactividades`
--

CREATE TABLE `semanasactividades` (
  `id_semana` int(11) NOT NULL,
  `id_plan` int(11) DEFAULT NULL,
  `semana` int(11) DEFAULT NULL,
  `programa` varchar(255) DEFAULT NULL,
  `plataforma` varchar(255) DEFAULT NULL,
  `llevara_acabo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Indices de la tabla `planestrabajoactividades`
--
ALTER TABLE `planestrabajoactividades`
  ADD PRIMARY KEY (`id_plan`),
  ADD KEY `fk_maestros` (`idMaestros`),
  ADD KEY `fkactividadextraescolar` (`id_Actividad_Extraescolar`);

--
-- Indices de la tabla `registrohistorico`
--
ALTER TABLE `registrohistorico`
  ADD PRIMARY KEY (`idRegistrohistorico`),
  ADD KEY `nocontrolAlumno` (`nocontrolAlumno`);

--
-- Indices de la tabla `semanasactividades`
--
ALTER TABLE `semanasactividades`
  ADD PRIMARY KEY (`id_semana`),
  ADD KEY `id_plan` (`id_plan`);

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
  MODIFY `Nocontrol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17280331;

--
-- AUTO_INCREMENT de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  MODIFY `idasistencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `creditosextraescolares`
--
ALTER TABLE `creditosextraescolares`
  MODIFY `idCreditoExtraescolar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `eventos`
--
ALTER TABLE `eventos`
  MODIFY `idEventos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `grupos`
--
ALTER TABLE `grupos`
  MODIFY `idGrupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `grupos_y_alumno`
--
ALTER TABLE `grupos_y_alumno`
  MODIFY `idgruposyalumno` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `horariosgrupo`
--
ALTER TABLE `horariosgrupo`
  MODIFY `idHorarioGrupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT de la tabla `maestros`
--
ALTER TABLE `maestros`
  MODIFY `idMaestros` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `registrohistorico`
--
ALTER TABLE `registrohistorico`
  MODIFY `idRegistrohistorico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
-- Filtros para la tabla `planestrabajoactividades`
--
ALTER TABLE `planestrabajoactividades`
  ADD CONSTRAINT `fk_maestros` FOREIGN KEY (`idMaestros`) REFERENCES `maestros` (`idMaestros`) ON DELETE CASCADE,
  ADD CONSTRAINT `fkactividadextraescolar` FOREIGN KEY (`id_Actividad_Extraescolar`) REFERENCES `actividad_extraescolar` (`idActividad_Extraescolar`) ON DELETE CASCADE;

--
-- Filtros para la tabla `registrohistorico`
--
ALTER TABLE `registrohistorico`
  ADD CONSTRAINT `registrohistorico_ibfk_2` FOREIGN KEY (`nocontrolAlumno`) REFERENCES `alumnos` (`Nocontrol`);

--
-- Filtros para la tabla `semanasactividades`
--
ALTER TABLE `semanasactividades`
  ADD CONSTRAINT `semanasactividades_ibfk_1` FOREIGN KEY (`id_plan`) REFERENCES `planestrabajoactividades` (`id_plan`) ON DELETE CASCADE;

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
