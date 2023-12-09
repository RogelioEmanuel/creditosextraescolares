
package dao.maestros;

import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Maestros_EliminarMaestros_DAO {
    
    public static void eliminar(int idMaestro, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        try {
            if (conn != null) {
                String delete = "delete from maestros where idMaestros = ?";
                PreparedStatement ps = conn.prepareStatement(delete);
                ps.setInt(1, idMaestro);
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
            Logger.getLogger(Maestros_EliminarMaestros_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
