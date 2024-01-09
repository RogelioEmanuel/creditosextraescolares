
package dao.Login;

import ManageBean.Usuarios.Usuarios_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import config.conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import masterDAO.Empleado;
import masterDAO.Usuario;



/**
 *
 * @author Emanuel
 */
public class Login_DAO {
    
    public static void validaUsuario(String usuario, String contrasenia, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.MASTER_BD2, Constantes.MASTER_USER, Constantes.MASTER_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Empleado usuarioMB = null;

        try {
            if (conn != null) {
                String query = "SELECT * "
                        + "FROM empleados "
                        + "WHERE usuario = ?"
                        + "AND password = ? ;";
                ps = conn.prepareStatement(query);
                ps.setString(1, usuario);
                ps.setString(2, contrasenia);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        usuarioMB = new Empleado(rs.getInt("id_empleado"),
                                rs.getString("nombre"),
                                rs.getString("apellidoPa"),
                                rs.getString("apellidoMa"));
                    }
                    respuesta.setStatus(Validaciones.VALIDATION_EXP);
                    respuesta.setMensaje(getToken(usuarioMB.getIdEmpleado(), respuesta));
                    respuesta.setResponseObject(usuarioMB);
                } else {
                    respuesta.setMensaje(Constantes.MSJ_CREDENCIALES_ERRONEAS);
                    respuesta.setStatus(Validaciones.VALIDATION_ERROR_CREDENCIALES);
                    respuesta.setResponseObject(usuarioMB);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login_DAO.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setMensaje(ex.getMessage());
            respuesta.setStatus(Validaciones.VALIDATION_EXP_ERROR);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Login_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {

            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Login_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static String getToken(int idEmpleado, GenericResponse respuesta) {
        ConexionMySQL cone = new ConexionMySQL(Constantes.MASTER_BD2, Constantes.MASTER_USER, Constantes.MASTER_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String token = "";
        try {
            if (conn != null) {
                String query = "SELECT token from sesiones WHERE id_empleado = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, idEmpleado);
                rs = ps.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        token = rs.getString("token");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login_DAO.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setMensaje(ex.getMessage());
            respuesta.setStatus(Validaciones.VALIDATION_EXP_ERROR);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Login_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {

            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Login_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return token;
    }
    
    
    
    public static void revisaUsuarioInt(String usu, String pas, GenericResponse resp){
        ConexionMySQL cone = new ConexionMySQL(Utilidades.Constantes.EXTRAESCOLARESPRUEBA_BD, Utilidades.Constantes.EXTRAESCOLARESPRUEBA_USER, Utilidades.Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Empleado usuario = new Empleado();
        System.out.println("Si revisa");
        try {
            if (conn != null) {
                String query = "SELECT idUsuario,nombre_usuario, apellidoPaterno,apellidoMaterno,nombrePuesto FROM usuarios  \n"
                        + "WHERE Usuario = ? AND contrasenia = ?";
                
                ps = conn.prepareStatement(query);
                ps.setString(1,usu );
                ps.setString(2,pas );
                rs = ps.executeQuery();
                
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        usuario = new Empleado(rs.getInt("idUsuario"),
                                    rs.getString("nombre_usuario"),
                                    rs.getString("apellidoPaterno"),
                                    rs.getString("apellidoMaterno"),
                                    rs.getString("nombrePuesto"));


                    }
                    resp.setStatus(Validaciones.VALIDATION_EXP);
                    resp.setMensaje("Token");
                    resp.setResponseObject(usuario);
                    System.out.println("Funciona creo");
                } else {
                    resp.setMensaje(Utilidades.Constantes.MSJ_CREDENCIALES_ERRONEAS);
                    resp.setStatus(Validaciones.VALIDATION_ERROR_CREDENCIALES);
                    resp.setResponseObject(usuario);
                }
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios_MB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Login_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuarios_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuarios_MB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    

    public static Usuario convertir(Empleado responseObject) {
        
        
        Usuario us= new Usuario();
        
        
        us.setNombre(responseObject.getNombre());
        us.setNombrePuesto(responseObject.getNombrePuesto());
        us.setApellidoPaterno(responseObject.getApellidoPaterno());
        us.setApellidoMaterno(responseObject.getApellidoMaterno());
        us.setCorreo(responseObject.getCorreo());
        us.setCrear(responseObject.getCrear());
        us.setLeer(responseObject.getLeer());
        us.setEditar(responseObject.getEditar());
        us.setEliminar(responseObject.getEliminar());
        us.setIdUsuario(Integer.toString(responseObject.getIdEmpleado()));
        us.setFechaNac(responseObject.getFechaNac());
        
        return us;
    }
    
}
