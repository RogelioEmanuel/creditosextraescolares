
package dao.Grupos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import config.conexion.ConexionMySQL;
import dao.actividadextraesscolar.ActividadExtraescolar_EditarActividad_DAO;
import dao.maestros.Maestros_EditarMaestros_DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Grupos_ListarGrupos_DAO {
    
    private static Maestros_MB convertirMaestro(ResultSet rs) throws SQLException {
        
        
        int idMaestros = rs.getInt("idMaestros");
        String nombre = rs.getString("nombre");
        String apPaterno = rs.getString("apPaterno");
        String apMaterno = rs.getString("apMaterno");                   
        String correo = rs.getString("correo");
        String telefono = rs.getString("telefono");
        String rfc = rs.getString("rfc");
        String curp = rs.getString("curp");
        String banco = rs.getString("banco");
        String clave = rs.getString("clave");
        String sexo = rs.getString("sexo");       
        Date fecha = rs.getDate("fecha_nacimiento");
        
        
        Maestros_MB maestro = new Maestros_MB();   
        maestro.setApMaterno(apMaterno);
        maestro.setApPaterno(apPaterno);
        maestro.setBanco(banco);
        maestro.setClave(clave);
        maestro.setCorreo(correo);
        maestro.setCurp(curp);
        
        maestro.setFecha_nacimiento(fecha);
        maestro.setIdMaestros(idMaestros);
        maestro.setNombre(nombre);
        maestro.setRfc(rfc);
        maestro.setSexo(sexo);
        maestro.setTelefono(telefono);
           
        return maestro;
    }
    
    
     private static Grupos_MB convertir(ResultSet rs) throws SQLException {
        int idGrupo = rs.getInt("idGrupo");        
        int totalhorassemanal = rs.getInt("totalhorassemanal"); 
        int noGrupo = rs.getInt("noGrupo");
        int cupo = rs.getInt("cupo"); 
        int idActividad = rs.getInt("idActividad_extraescolar"); 
        int idMaestros = rs.getInt("idMaestros"); 
        String periodo = rs.getString("periodo");
        
        Grupos_MB grupo = new Grupos_MB();  
        
        grupo.setCupo(cupo);
        
        grupo.setIdActividad(idActividad);
        grupo.setIdGrupo(idGrupo);
        grupo.setIdMaestros(idMaestros);
        grupo.setPeriodo(periodo);
        grupo.setNoGrupo(noGrupo);
        grupo.setTotalhorassemanales(totalhorassemanal);
        
        Maestros_MB mas= new Maestros_MB();
        
        mas = consultarMaestro(idMaestros);
        int a= mas.getIdMaestros();               
        
         if(grupo.getIdMaestros()==0){
             grupo.setNombreMaestro("Pendiente de contratar");
         }else{
             grupo.setNombreMaestro(mas.getNombre()+" "+mas.getApPaterno());
         }
        return grupo;
        
        
    }
    
     public static List<Grupos_MB> consultar(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Grupos_MB> grupos = new ArrayList<>();
        
         
        
  
        try {
            if (conn != null) {
                String query = "SELECT idGrupo,noGrupo,  cupo,  idActividad_extraescolar, idMaestros, periodo, totalhorassemanal \n"
                        + "FROM Grupos  \n"
                        + "where idActividad_extraescolar =?"  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    grupos.add(convertir(rs));
                    
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupos_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return grupos;
    }
     
    /**
     *
     * @param idMaestro
     * @return
     */
    public static Maestros_MB consultarMaestro(int idMaestro) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Maestros_MB maestro = new Maestros_MB();

        try {
            if (conn != null) {
                String query = "SELECT idMaestros, nombre, apPaterno, apMaterno, fecha_nacimiento, correo,telefono, RFC,CURP, banco, clave,Sexo \n"
                        + "FROM maestros f \n"
                        + "WHERE IdMaestros = ?";
                
                ps = conn.prepareStatement(query);
                ps.setInt(1,idMaestro );
                rs = ps.executeQuery();
                while (rs.next()) {
                    maestro = convertirMaestro(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Maestros_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Grupos_ListarGrupos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return maestro;
    }
    
    
}
