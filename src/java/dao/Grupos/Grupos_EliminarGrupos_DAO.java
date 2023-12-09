/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Grupos;

import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import dao.actividadextraesscolar.ActividadExtraescolar_EliminarActividad_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class Grupos_EliminarGrupos_DAO {
    
       
    public static void eliminar(int idGrupo, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        try {
            if (conn != null) {
                String delete = "delete from grupos where idGrupo = ?";
                PreparedStatement ps = conn.prepareStatement(delete);
                ps.setInt(1, idGrupo);
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
