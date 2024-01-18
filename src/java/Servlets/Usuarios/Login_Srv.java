
package Servlets.Usuarios;

import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Periodo.Periodo;
import Utilidades.CaptchaGenerador;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import com.google.gson.Gson;
import dao.Login.Login_DAO;
import dao.Periodo.Periodo_DAO;
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
        String captcha = CaptchaGenerador.generateCaptcha();
        System.out.println("Captcha "+captcha);
        request.setAttribute("captchaValue", captcha);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        myContextParam = request.getSession().getServletContext().getInitParameter("AppName");
        
        response.setContentType("text/html;charset=UTF-8");        
        PrintWriter out = response.getWriter();
        String us  =  request.getParameter("itt_username");
        String pas = request.getParameter("itt_password");        
        String itt_captcha = request.getParameter("itt_captcha");
        String txtInput = request.getParameter("txtInput");
        GenericResponse resp2= new GenericResponse();
        
        
        
        if(CaptchaGenerador.removeSpaces(itt_captcha).equals(CaptchaGenerador.removeSpaces(txtInput))&&!itt_captcha.isEmpty()){
            
            GenericResponse<Empleado> respuesta = new GenericResponse();
            Login_DAO.revisaUsuarioInt(us, pas,respuesta);
            System.out.println("Busco en BD");
            //Si esta en la base de datos
            if(respuesta.getStatus()== Validaciones.VALIDATION_EXP){
                
                System.out.println("Encontro en BD");
                HttpSession session = request.getSession(true);       
                Periodo per = Periodo_DAO.consultar();            
                session.setAttribute("periodo", per);
                
                Empleado usua = respuesta.getResponseObject();
                if(usua.getNombrePuesto().equals("Estudiante")){
                    Usuario uwu= Login_DAO.convertir(respuesta.getResponseObject());
                    //respuesta.setResponseObject(uwu);
                    session.setAttribute("usuario", uwu);
                }else{
                    session.setAttribute("usuario", respuesta.getResponseObject());
                    Empleado usuarioenSesion = (Empleado) session.getAttribute("usuario");
                    session.setAttribute("rol",usuarioenSesion.getNombrePuesto());
                }


                //session.setAttribute("rol", respuesta.getRol() );
                //session.setAttribute("mensaje", 0 + "");
            }else{

                    //GenericResponse<Empleado> respuesta2 = new GenericResponse();
                    //Web service Terminado
                    System.out.println("Busco en Web Service");
                        GenericResponse<Alumnos_MB> resp = new GenericResponse<>();                        
                        AlumnosmDAO.getAlumno(us,pas,resp);
                        GenericResponse<Empleado> emp = new GenericResponse();
                        Alumnos_MB al = (Alumnos_MB) resp.getResponseObject();
                        System.out.println("Resp"+ resp.getMensaje());

                    if(al!=null){
                        System.out.println("Encontro en Web Service");
                        HttpSession session = request.getSession(true); 
                        Periodo per = Periodo_DAO.consultar();   
                        session.setAttribute("periodo", per);
                        Empleado usuarioenSesion = new Empleado();
                        usuarioenSesion.setNombrePuesto("Estudiante");
                        session.setAttribute("usu", usuarioenSesion);
                        Usuario us2 = new Usuario();
                        us2.setIdUsuario(us);
                        us2.setNombrePuesto("Estudiante");
                        us2.setNombre(al.getNombre());
                        session.setAttribute("rol",usuarioenSesion.getNombrePuesto());
                        session.setAttribute("usuario", us2);
                    
                    

                    }else {
                        
                        
                       //Si esta en el master
                        Login_DAO.validaUsuario(us, pas, respuesta);
                        String token = Login_DAO.getToken(respuesta.getResponseObject().getIdEmpleado(), respuesta);

                        if (respuesta.getStatus() == Validaciones.VALIDATION_EXP) {

                            HttpSession session = request.getSession(true);
                            String horaActual = new SimpleDateFormat("HHmm:ss").format(Calendar.getInstance().getTime());
                            session.setAttribute("user", respuesta.getResponseObject());
                            Empleado usuarioEnSesion = (Empleado) session.getAttribute("usuario");
                            session.setAttribute("rol",usuarioEnSesion.getNombrePuesto());
                            session.setAttribute("hora", horaActual);
                            //session.setAttribute("nivel", nivel + "");
                            session.setAttribute("mensaje", 0 + "");
                            Periodo per = Periodo_DAO.consultar();            
                            session.setAttribute("periodo", per);                         

                            
                            
                        
                        }else{
                            respuesta.setStatus(-404);
                            respuesta.setMensaje("Datos incorrectos");
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
        }else{
            
            
            try {
                response.setContentType("application/json");
                resp2.setStatus(15);
                resp2.setMensaje("Captcha Invalido");
                Gson json = new Gson();
                out.print(json.toJson(resp2));
            } catch (Exception e) {
                        Logger.getLogger(Login_Srv.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
       
        
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
