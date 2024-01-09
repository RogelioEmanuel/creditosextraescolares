/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.ActividadExtraescolar;

import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.actividadextraesscolar.ActividadExtraescolar_EliminarActividad_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emanuel
 */
public class EliminarActividadExtraescolar_Srv extends HttpServlet {

  

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       // request.getRequestDispatcher("/views/Actividad_Extraescolar/Paginas/ListadoActividad_View.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        GenericResponse resp = new GenericResponse<>();
        
       
        //Obtener el id de la invitacion
        
        int idActividad = Integer.parseInt(request.getParameter("idActividad"));        
        HttpSession session = request.getSession();
        session.setAttribute("idActividad", idActividad);  
        
        

        ActividadExtraescolar_EliminarActividad_DAO.eliminar(idActividad, resp);
        
        
        
        
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {

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
