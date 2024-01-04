
package Servlets.PlandeTrabajo;

import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.maestros.Maestros_EliminarMaestros_DAO;
import dao.plandetrabajo.Planes_EliminarPlan_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emanuel
 */
public class EliminarPlanesdeTrabajo_Srv extends HttpServlet {

    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        GenericResponse resp = new GenericResponse<>();
        
        //Obtener los usuarios eliminados consultando
        
        //Obtener el id de la invitacion
        
        int idPlan = Integer.parseInt(request.getParameter("idPlan"));        
        HttpSession session = request.getSession();
        session.setAttribute("idPlan", idPlan);  

        Planes_EliminarPlan_DAO.eliminar(idPlan, resp);
        
        
        
        
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {

        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
