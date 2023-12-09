package dao.Grupos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import config.conexion.ConexionMySQL;
import static dao.Grupos.Grupos_CrearGrupo_DAO.convertirActividad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Grupos_DetalleGrupo_DAO {
    
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
        java.util.Date fecha = rs.getDate("fecha_nacimiento");
        
        
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
        int noGrupo =rs.getInt("noGrupo");
        int cupo = rs.getInt("cupo");
        int idActividad = rs.getInt("idActividad_Extraescolar");
        int idMaestros = rs.getInt("idMaestros");
        String periodo = rs.getString("periodo");
        
        int totalhorassemanal = rs.getInt("totalhorassemanal"); 
        
                    
        
       Grupos_MB grupo = new Grupos_MB( idGrupo, noGrupo,  cupo,  periodo,  idActividad,  idMaestros,  totalhorassemanal);          
        
        
        return grupo;
    }
    
    private static HorariosGrupo_MB convertirhorario(ResultSet rs) throws SQLException {
        int idGrupo = rs.getInt("idGrupo");
        int idHorarioGrupo = rs.getInt("idHorarioGrupo");
        String dia = rs.getString("Dia");
        String horaInicio = rs.getString("HoraInicio");
        String horaFinal = rs.getString("HoraFinal");
        
       HorariosGrupo_MB grupo = new HorariosGrupo_MB( idHorarioGrupo, idGrupo,dia,horaInicio,horaFinal );          
        
        
        return grupo;
    }

    
    public static List<Maestros_MB> consultarMaestro() {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Maestros_MB> maestro = new ArrayList<>();

        try {
            if (conn != null) {
                String query = "SELECT idMaestros, nombre, apPaterno, apMaterno, fecha_nacimiento, correo,telefono, RFC,CURP, banco, clave,Sexo \n"
                        + "FROM maestros f \n";
                
                ps = conn.prepareStatement(query);
                
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    maestro.add(convertirMaestro(rs));                    
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
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
     
     
    public static ActividadExtraescolar_MB consultarActividad(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ActividadExtraescolar_MB actividades = new ActividadExtraescolar_MB();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT idActividad_Extraescolar, nombre, tipo, status, descripcion \n"
                        + "FROM actividad_extraescolar f \n"                        
                        + "WHERE idActividad_Extraescolar = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                while (rs.next()) {
                    actividades=convertirActividad(rs);
                    
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActividadExtraescolar_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return actividades;
    }
     
    public static Grupos_MB consultar(int idGrupo) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
       Grupos_MB grupo = new Grupos_MB();

        try {
            if (conn != null) {
                String query = "SELECT idGrupo,noGrupo, cupo, idActividad_extraescolar, idMaestros, periodo, totalhorassemanal \n"
                        + "FROM grupos f \n"
                        + "WHERE idGrupo = ?";
                
                ps = conn.prepareStatement(query);
                ps.setInt(1,idGrupo );
                rs = ps.executeQuery();
                while (rs.next()) {
                    grupo = convertir(rs);
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
                Logger.getLogger(Grupos_EditarGrupo_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        return grupo;
    }
    
           
    public static List<HorariosGrupo_MB> consultarhorario(int Grupo) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<HorariosGrupo_MB> horario = new ArrayList<>();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT  idHorarioGrupo, idGrupo, Dia, HoraInicio, HoraFinal \n"
                        + "FROM horariosgrupo \n"                        
                        + "WHERE idGrupo = ? ";
                ps = conn.prepareStatement(query);
                ps.setInt(1,Grupo );
                //ps.setString(2,dia );
                rs = ps.executeQuery();
                while (rs.next()) {
                    horario.add(convertirhorario(rs));
                      
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HorariosGrupo_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HorariosGrupo_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HorariosGrupo_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return horario;
    }
    
}
