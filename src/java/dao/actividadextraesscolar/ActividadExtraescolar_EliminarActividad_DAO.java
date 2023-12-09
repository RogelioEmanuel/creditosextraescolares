/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.actividadextraesscolar;

import Utilidades.GenericResponse;
import config.conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utilidades.Constantes;
import Utilidades.Validaciones;

/**
 *
 * @author Emanuel
 */
public class ActividadExtraescolar_EliminarActividad_DAO {
    
    
    public static void eliminar(int idActividad_Extraescolar, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        try {
            if (conn != null) {
                String delete = "delete from actividad_extraescolar where idActividad_Extraescolar = ?";
                PreparedStatement ps = conn.prepareStatement(delete);
                ps.setInt(1, idActividad_Extraescolar);
                ps.executeUpdate();
                if (!conn.isClosed()) {
                    cone.desconectar();
                }
                if (ps != null) {
                    ps.close();
                }
                respuesta.setMensaje("Ok");
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                respuesta.setResponseObject(null);
            }
        } catch (SQLException ex) {
            respuesta.setStatus(Validaciones.VALIDATION_ERROR);
            respuesta.setMensaje(ex.toString());
            respuesta.setResponseObject(null);
            Logger.getLogger(ActividadExtraescolar_EliminarActividad_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
