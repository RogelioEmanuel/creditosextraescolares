
package Servlets.Maestros;

import ManageBean.Maestros.Maestros_MB;
import Servlets.ActividadExtraescolar.CrearActividadExtraescolar;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.maestros.Maestros_CrearMaestros_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import masterDAO.Empleado;

/**
 *
 * @author Emanuel
 */
public class CrearMaestro_Srv extends HttpServlet {

    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        request.getRequestDispatcher("/views/Maestros/Paginas/CrearMaestro_View.jsp").forward(request, response);

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //datos dede el request
        PrintWriter out = response.getWriter();    
        String nombre = request.getParameter("nombre");
        String appaterno = request.getParameter("appaterno");
        String apmaterno = request.getParameter("apmaterno");        
        String correo = request.getParameter("correo");
        String telefono =request.getParameter("telefonoMaestro") ;
        String banco = request.getParameter("banco");
        String rfc = request.getParameter("rfc");
        String curp = request.getParameter("curp");
        
        String direccion = request.getParameter("direccion");
        System.out.println(direccion);
        String claveinterbancaria= request.getParameter("claveinterbancaria")  ;
        String sexo = request.getParameter("sexo");
        
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("pass");
        
        
        //fechas
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");        
        String fecha_nacimiento = request.getParameter("fechanacimiento");        
        Date fechaMaestro=null;        
        try{
            
            fechaMaestro= f.parse(fecha_nacimiento);
            System.out.println("Fecha parseada correctamente: " + f.format(fechaMaestro));
        } catch (ParseException ex) {
            System.out.println("salio mal");
            Logger.getLogger(CrearMaestro_Srv.class.getName()).log(Level.SEVERE, null, ex);
            fechaMaestro=null;
            System.out.println("a ver"+ex);
        }
        
        
        
        HttpSession session = request.getSession();
        GenericResponse resp = new GenericResponse();
        
        
              
        Maestros_MB actividad = new Maestros_MB(nombre, appaterno, apmaterno,  correo,telefono,fechaMaestro,banco,curp,rfc,sexo,claveinterbancaria,direccion);
        
        
        
        System.out.println(actividad.getDireccion());
        
        Empleado a = new Empleado();
        a.setNombre(nombre);
        a.setApellidoPaterno(appaterno);
        a.setApellidoMaterno(apmaterno);
        a.setNombrePuesto("Maestro");
        a.setFechaNac(fecha_nacimiento);
        a.setUsuario(usuario);
        a.setCorreo(correo);
        Maestros_CrearMaestros_DAO.insertarUsuario(a, pass, resp);
        Maestros_CrearMaestros_DAO.insertar(actividad, resp);
        

                
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {
            Logger.getLogger(CrearActividadExtraescolar.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
