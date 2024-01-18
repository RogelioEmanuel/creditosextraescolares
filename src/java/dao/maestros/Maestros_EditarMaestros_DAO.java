package dao.maestros;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import masterDAO.Empleado;


public class Maestros_EditarMaestros_DAO {
    
    
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

    public static Maestros_MB consultar(int idActividad) {
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
                ps.setInt(1,idActividad );
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
    
    
    public static void actualizarMaestro(Maestros_MB maestro, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        java.sql.Date fechaNacimiento = null;
        
        if (maestro.getFecha_nacimiento() != null) {
            fechaNacimiento = new java.sql.Date(maestro.getFecha_nacimiento().getTime());
        }
       

       

        PreparedStatement ps = null;
        try {
            if (conn != null) {
                String update =" UPDATE maestros SET nombre=?, apPaterno=?, apMaterno=?, fecha_nacimiento=?,correo=?, telefono=?, RFC=?, CURP=?,banco=?,"
                        + " clave=?, sexo=?,direccion=? "
                        + "WHERE idMaestros=?";
                ps = conn.prepareStatement(update);
                ps.setString(1, maestro.getNombre());
                ps.setString(2, maestro.getApPaterno());
                ps.setString(3, maestro.getApMaterno());
                ps.setDate(4, fechaNacimiento);
                ps.setString(5, maestro.getCorreo());
                ps.setString(6, maestro.getTelefono());
                ps.setString(7, maestro.getRfc());
                ps.setString(8, maestro.getCurp());
                ps.setString(9, maestro.getBanco());
                ps.setString(10, maestro.getClave());
                ps.setString(11, maestro.getSexo());
                ps.setString(12, maestro.getDireccion());
                ps.setInt(13, maestro.getIdMaestros());
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
    
    
    public static void actualizarUsuario(Empleado empleado,String pass, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        java.sql.Date fechaNacimiento = null;
        
        
       

       

        PreparedStatement ps = null;
        try {
            if (conn != null) {
                String update =" UPDATE usuarios\n" +
                                    "SET \n" +
                                    "    Usuario = ?,\n" +
                                    "    nombre_usuario = ?,\n" +
                                    "    apellidoPaterno = ?,\n" +
                                    "    apellidoMaterno = ?,\n" +
                                    "    nombrePuesto = ?,\n" +
                                    "    fechaNacimiento = ?,\n" +
                                    "    correo = ?,\n" +
                                    "    contrasenia = ?\n" +
                                    "WHERE \n" +
                                    "    idUsuario = ?;";
                 ps = conn.prepareStatement(update);
                ps.setString(1, empleado.getNombre());  
                ps.setString(2, empleado.getUsuario());
                ps.setString(3,empleado.getApellidoPaterno());
                ps.setString(4, empleado.getApellidoMaterno());                 
                ps.setString(5,empleado.getNombrePuesto());
                ps.setString(6,empleado.getFechaNac());
                ps.setString(7, empleado.getCorreo());
                ps.setString(8, pass);    
                ps.setInt(9,empleado.getIdEmpleado());
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
