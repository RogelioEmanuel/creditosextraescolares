
package dao.Periodo;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Maestros.Maestros_MB;
import ManageBean.Periodo.Periodo;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import dao.maestros.Maestros_EditarMaestros_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class Periodo_DAO {
    
    
    public static Periodo consultar() {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Periodo periodo = new Periodo();

        try {
            if (conn != null) {
                String query = "SELECT idPeriodo, Periodo, FechaInicio, FechaFinal, Anio, diasInscipcion,diasCalificacion \n"
                        + "FROM periodoactual p \n"
                        + "WHERE idPeriodo = 1";
                
                ps = conn.prepareStatement(query);
               
                rs = ps.executeQuery();
                while (rs.next()) {
                    periodo = convertir(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Maestros_EditarMaestros_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Maestros_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Maestros_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return periodo;
    }
    
    
    private static Periodo convertir(ResultSet rs) throws SQLException {
        
        
        int idPeriodo = rs.getInt("idPeriodo");
        String periodo = rs.getString("Periodo");               
        Date fechaInicio = rs.getDate("FechaInicio");
        Date fechaFinal = rs.getDate("FechaFinal");
        int diasInscipcion = rs.getInt("diasInscipcion");
        int Anio = rs.getInt("Anio");
        int diasCalificacion = rs.getInt("diasCalificacion");
        
        Periodo periodo2 = new Periodo(idPeriodo,diasInscipcion,diasCalificacion,periodo,Anio,fechaInicio,fechaFinal);
           
        return periodo2;
    }
     
     
    public static void actualizarPeriodo(Periodo per, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        java.sql.Date fechaInicio = null;
        java.sql.Date fechaFin = null;
        
        
        if (per.getFecha_inicio()   != null) {
            fechaInicio = new java.sql.Date(per.getFecha_inicio().getTime());
        }
        if (per.getFecha_fin()   != null) {
            fechaFin = new java.sql.Date(per.getFecha_fin().getTime());
        }

       

        PreparedStatement ps = null;
        try {
            if (conn != null) {
                String update = "UPDATE periodoactual SET Periodo=?, FechaInicio=?, FechaFinal=?, diasInscipcion=?, diasCalificacion=? "
              + "WHERE idPeriodo=1";

                ps = conn.prepareStatement(update);
                ps.setString(1, per.getPeriodo());
                ps.setDate(2, fechaInicio);
                ps.setDate(3, fechaFin);
               // ps.setInt(4, per.getAnio());
                ps.setInt(4, per.getInsscripcion());
                ps.setInt(5, per.getCierre());
                
                ps.executeUpdate();

                respuesta.setMensaje("Ok");
                respuesta.setStatus(Validaciones.VALIDATION_EXP);
                respuesta.setResponseObject(null);
            }
        } catch (SQLException ex) {
            respuesta.setStatus(Validaciones.VALIDATION_ERROR);
            respuesta.setMensaje(ex.toString());
            respuesta.setResponseObject(null);
            Logger.getLogger(Maestros_EditarMaestros_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Maestros_EditarMaestros_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Maestros_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
