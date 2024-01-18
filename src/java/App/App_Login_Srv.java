/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import ManageBean.Alumnos.Alumnos_MB;
import Servlets.Usuarios.Login_Srv;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import masterDAO.AlumnosmDAO;
import masterDAO.Empleado;
import masterDAO.Usuario;

/**
 *
 * @author Emanuel
 */
public class App_Login_Srv extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String strUsuario = "";
        String strPassword = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "itt_username":
                        if (cookie.getValue() != null) {
                            strUsuario = (cookie.getValue());
                        }
                        break;
                    case "itt_password":
                        if (cookie.getValue() != null) {
                            strPassword = (cookie.getValue());
                        }
                        break;
                }
            }
        }
        
        GenericResponse<Empleado> respuesta = new GenericResponse();
        PrintWriter out = response.getWriter();
        Login_DAO.revisaUsuarioInt(strUsuario, strPassword,respuesta);
        
        
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
               

                Login_DAO.validaUsuario(strUsuario, strPassword, respuesta);
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
                    AlumnosmDAO.getAlumno(strUsuario,strPassword,resp);
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
            Logger.getLogger(App_Login_Srv.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
