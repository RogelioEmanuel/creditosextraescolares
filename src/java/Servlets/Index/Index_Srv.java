
package Servlets.Index;

import ManageBean.Periodo.Periodo;
import Utilidades.CaptchaGenerador;
import static Utilidades.Constantes.esAntesDeInscripciones;
import dao.Periodo.Periodo_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import masterDAO.Empleado;
import masterDAO.Usuario;



public class Index_Srv extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/Inicio/Index.jsp");
        Object usuarioEnSesion = session.getAttribute("usuario");
        //Evaluacion de Sesion
        
        if(session.getAttribute("usuario") instanceof  Empleado){
             usuarioEnSesion = (Empleado) session.getAttribute("usuario");
        }else if(session.getAttribute("usuario") instanceof  Usuario){
             usuarioEnSesion = (Usuario) session.getAttribute("usuario");
        }
        //Empleado usuarioEnSesion = (Empleado) 
        
        if ( usuarioEnSesion == null) {
            //response.sendRedirect("views/templates/error403.jsp");
            
            String captcha = CaptchaGenerador.generateCaptcha();
            
            //System.out.println("Captcha "+captcha);
            request.setAttribute("captchaValue", captcha);
            
            request.getRequestDispatcher("/views/Login/Login_View.jsp").forward(request, response);
        } else {
            Periodo per = Periodo_DAO.consultar();
            request.setAttribute("periodo", per);
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
