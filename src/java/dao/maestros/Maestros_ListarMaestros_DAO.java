/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.maestros;


import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import config.conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emanuel
 */
public class Maestros_ListarMaestros_DAO {
    
    private static Maestros_MB convertir(ResultSet rs) throws SQLException {
        int idMaestro = rs.getInt("idMaestros");
        String nombre = rs.getString("nombre");
        String apPaterno = rs.getString("apPaterno");
        String apMaterno=rs.getString("apMaterno");
        String correo=rs.getString("correo");
        String telefono=rs.getString("telefono");       
        Date fecha = rs.getDate("fecha_nacimiento");
       // String banco= rs.getString("banco");
        //String curp=rs.getString("curp");
        //String RFC=rs.getString("rfc");
        //String sexo=rs.getString("sexo");
        //String clave=rs.getString("clave");
        //String direccion= rs.getString("direccion");
        System.out.println(fecha);
        
        
        Maestros_MB maestro = new Maestros_MB();        
        maestro.setApMaterno(apMaterno);
        maestro.setApPaterno(apPaterno);
        maestro.setCorreo(correo);        
        maestro.setIdMaestros(idMaestro);
        maestro.setNombre(nombre);
        maestro.setTelefono(telefono);
        //maestro.setBanco(banco);
        //maestro.setClave(clave);
        //maestro.setCorreo(correo);
        //maestro.setCurp(curp);
        //maestro.setDireccion(direccion);
        maestro.setFecha_nacimiento(fecha);
        //maestro.setSexo(sexo);
        return maestro;
    }
    
     public static List<Maestros_MB> consultar() {
        ConexionMySQL cone = new ConexionMySQL(Constantes.EXTRAESCOLARESPRUEBA_BD, Constantes.EXTRAESCOLARESPRUEBA_USER, Constantes.EXTRAESCOLARESPRUEBA_PASS);
        int statusConexion = cone.conectar();
        Connection conn = cone.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Maestros_MB> maestros = new ArrayList<>();
        
        
  
        try {
            if (conn != null) {
                String query = "SELECT idMaestros, nombre, appaterno, apmaterno, correo, telefono, fecha_nacimiento \n"
                        + "FROM maestros f \n"                        
                        + "limit 100";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    maestros.add(convertir(rs));
                    
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

        return maestros;
    }
    
}
