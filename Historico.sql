SET GLOBAL event_scheduler = ON;


DELIMITER //
CREATE EVENT IF NOT EXISTS registrohistorico
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
BEGIN
    
    CALL insertar_registro_historico();
END //
DELIMITER ;



DELIMITER //

CREATE PROCEDURE insertar_registro_historico()
BEGIN
    -- Paso 2
    INSERT INTO registrohistorico (estado, tipo, nocontrolAlumno,periodo,nombreactividad,anio) SELECT  estado, tipo, noControlAlumno,periodo,NombreActividad,anio FROM creditosextraescolares ;
    --WHERE fecha_creacion < CURDATE() - INTERVAL 1 YEAR;

    -- Paso 3
    DELETE FROM creditosextraescolares;
   -- WHERE fecha_creacion < CURDATE() - INTERVAL 1 YEAR;

    -- Eliminar de la tabla 'grupos_y_alumno'
    DELETE FROM grupos_y_alumno;
   -- WHERE fecha_creacion < CURDATE() - INTERVAL 1 YEAR;

    -- Eliminar de la tabla 'horariosgrupo'
    DELETE FROM horariosgrupo;
    --WHERE fecha_creacion < CURDATE() - INTERVAL 1 YEAR;

    -- Eliminar de la tabla 'asistencia'
    DELETE FROM asistencia;
    --WHERE fecha_creacion < CURDATE() - INTERVAL 1 YEAR;
    
END //
DELIMITER ;



Revisar si se esta ejecutando
HOW EVENTS;
SHOW PROCESSLIST;
