
package dao.plandetrabajo;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.Maestros.Maestros_MB;
import ManageBean.PlanSemana.PlanSemana_MB;
import ManageBean.PlandeTrabajo.PlanTrabajo_MB;
import Utilidades.Constantes;
import config.conexion.ConexionMySQL;
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


public class Planes_DetallarPlan_DAO {
    
    
    
    
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
    public static ActividadExtraescolar_MB convertirActividad(ResultSet rs) throws SQLException {
        int idActividad_Extraescolar = rs.getInt("idActividad_Extraescolar");
        String nombre = rs.getString("nombre");
        String tipo = rs.getString("tipo");
        int status = rs.getInt("status");            
        String descripcion = rs.getString("descripcion");
        ActividadExtraescolar_MB actividad = new ActividadExtraescolar_MB();  
        
        actividad.setIdActividad_Extraescolar(idActividad_Extraescolar);
        actividad.setNombre(nombre);
        actividad.setDescripcion(descripcion);
        actividad.setStatus(status);
        actividad.setTipo(tipo);
        return actividad;
    }
    private static Maestros_MB convertir(ResultSet rs) throws SQLException {
        
        
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
        String direccion =rs.getString("direccion");
        
        
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
        maestro.setDireccion(direccion);
           
        return maestro;
    }
    public static Maestros_MB consultar(int idMaestro) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Maestros_MB maestro = new Maestros_MB();

        try {
            if (conn != null) {
                String query = "SELECT idMaestros, nombre, apPaterno, apMaterno, fecha_nacimiento, correo,telefono, RFC,CURP, banco, clave,Sexo,direccion \n"
                        + "FROM maestros f \n"
                        + "WHERE IdMaestros = ?";
                
                ps = conn.prepareStatement(query);
                ps.setInt(1,idMaestro );
                rs = ps.executeQuery();
                while (rs.next()) {
                    maestro = convertir(rs);
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
        return maestro;
    }
      
    public static PlanTrabajo_MB consultarPlan(int id) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        PlanTrabajo_MB plantrabajo = new PlanTrabajo_MB();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT id_plan, id_Actividad_Extraescolar, idMaestros \n"
                        + "FROM planestrabajoactividades f \n"                        
                        + "where id_plan = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                while (rs.next()) {
                    plantrabajo=convertir2(rs);
                    
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

        return plantrabajo;
    }
    private static PlanTrabajo_MB convertir2(ResultSet rs) throws SQLException {
        int idActividad_Extraescolar = rs.getInt("id_Actividad_Extraescolar");
        
        int id_plan = rs.getInt("id_plan"); 
        int idMaestros = rs.getInt("idMaestros"); 
        PlanTrabajo_MB plan = new PlanTrabajo_MB();  
        plan.setActividadExtraescolar(idActividad_Extraescolar);
        plan.setIdPlan(id_plan);
        plan.setMaestro(idMaestros);
        Maestros_MB a = consultar(idMaestros);
        plan.setNomMaestro(a.getNombre());
        ActividadExtraescolar_MB b = consultarActividad(idActividad_Extraescolar);
        plan.setNomactividad(b.getNombre());
        return plan;
    }   
    public static List<PlanSemana_MB> consultarPlanSemanal(int id){
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PlanSemana_MB> plansemanales = new ArrayList<>();
        
         
        
  
        try {
            if (conn != null) {
                String query = "SELECT id_registro ,id_plan,semana,programa,  plataforma,  llevara_acabo \n"
                        + "FROM semanasactividades  \n"
                        + "where id_plan =?"  ;
                        
                        
                ps = conn.prepareStatement(query);
                ps.setInt(1,id );
                rs = ps.executeQuery();
                
                
                
                while (rs.next()) {
                    plansemanales.add(convertirplansemanal(rs));
                    
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

        return plansemanales;
        
        
    }    
    private static PlanSemana_MB convertirplansemanal(ResultSet rs) throws SQLException {
        
        
        int idRegistro = rs.getInt("id_registro");
        int id_plan = rs.getInt("id_plan");
        int semana = rs.getInt("semana");
        String programa = rs.getString("programa");
        String plataforma = rs.getString("plataforma");
        String llevara_acabo = rs.getString("llevara_acabo");
        
        PlanSemana_MB plan = new PlanSemana_MB(idRegistro,id_plan,semana,programa,plataforma,llevara_acabo);   
        
        return plan;
    }
    
    
    
}
