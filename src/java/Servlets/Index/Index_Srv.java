
package Servlets.Index;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import masterDAO.Empleado;



public class Index_Srv extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/Inicio/Index.jsp");
        
        //Evaluacion de Sesion
        HttpSession session = request.getSession();
        Empleado usuarioEnSesion = (Empleado) session.getAttribute("usuario");
        if ( usuarioEnSesion == null) {
            //response.sendRedirect("views/templates/error403.jsp");
            request.getRequestDispatcher("/views/Login/Login_View.jsp").forward(request, response);
        } else {
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
