
package Servlets.Usuarios;

import ManageBean.Alumnos.Alumnos_MB;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import com.google.gson.Gson;
import dao.Login.Login_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import masterDAO.AlumnosmDAO;
import masterDAO.Empleado;
import masterDAO.Usuario;



public class Login_Srv extends HttpServlet {
    protected String myParm = null;
    protected String myContextParam = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        myContextParam = request.getSession().getServletContext().getInitParameter("AppName");
        
        response.setContentType("text/html;charset=UTF-8");        
        PrintWriter out = response.getWriter();
        String us  =  request.getParameter("itt_username");
        String pas = request.getParameter("itt_password");        
        
        
        GenericResponse<Empleado> respuesta = new GenericResponse();
        Login_DAO.revisaUsuarioInt(us, pas,respuesta);
        
        
        if(respuesta.getStatus()== Validaciones.VALIDATION_EXP){
            HttpSession session = request.getSession(true);       
            
            session.setAttribute("usuario", respuesta.getResponseObject());
            Empleado usua = respuesta.getResponseObject();
            if(usua.getNombrePuesto().equals("Estudiante")){
                
                Usuario uwu= Login_DAO.convertir(respuesta.getResponseObject());
                //respuesta.setResponseObject(uwu);
                session.setAttribute("usuarioAlu", uwu);
                
                
            }else{
                Empleado usuarioenSesion = (Empleado) session.getAttribute("usuario");
                session.setAttribute("rol",usuarioenSesion.getNombrePuesto());
            }
            
            
            //session.setAttribute("rol", respuesta.getRol() );
            //session.setAttribute("mensaje", 0 + "");
        }else{
            
                //GenericResponse<Empleado> respuesta2 = new GenericResponse();
               

                Login_DAO.validaUsuario(us, pas, respuesta);
                String token = Login_DAO.getToken(respuesta.getResponseObject().getIdEmpleado(), respuesta);

                if (respuesta.getStatus() == Validaciones.VALIDATION_EXP) {

                    HttpSession session = request.getSession(true);
                    String horaActual = new SimpleDateFormat("HHmm:ss").format(Calendar.getInstance().getTime());
                    session.setAttribute("user", respuesta.getResponseObject());
                    Empleado usuarioEnSesion = (Empleado) session.getAttribute("usuario");
                    session.setAttribute("hora", horaActual);
                    //session.setAttribute("nivel", nivel + "");
                    session.setAttribute("mensaje", 0 + "");
                    
                    
                }else{
                    
                    GenericResponse resp = new GenericResponse<>();
                    AlumnosmDAO.getAlumno(us,pas,resp);
                    GenericResponse<Empleado> emp = new GenericResponse();
                    Alumnos_MB al = (Alumnos_MB) resp.getResponseObject();
                    
                    
                    if(al!=null){
                        
                        HttpSession session = request.getSession(true); 
                        session.setAttribute("usuario", respuesta.getResponseObject());
                        Empleado usuarioenSesion = (Empleado) session.getAttribute("usuario");
                        session.setAttribute("rol",usuarioenSesion.getNombrePuesto());
                    }else{
                        response.sendRedirect("app/Index/Home.do");
                    }
                    
                }

                
                
                
        }
        
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(respuesta));
        } catch (Exception e) {
                    Logger.getLogger(Login_Srv.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
