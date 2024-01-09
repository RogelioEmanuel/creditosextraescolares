/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.ActividadExtraescolar;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import dao.actividadextraesscolar.ActividadExtraescolar_CrearActividad_DAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utilidades.GenericResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


/**
 *
 * @author Emanuel
 */
public class CrearActividadExtraescolar extends HttpServlet {

   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/views/Actividad_Extraescolar/Paginas/CrearActividad_View.jsp").forward(request, response);
    }

    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();    
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String tipo = request.getParameter("tipo"); 
        
        int status = 1;        
        HttpSession session = request.getSession();
        GenericResponse resp = new GenericResponse();
        
        
              
        ActividadExtraescolar_MB actividad = new ActividadExtraescolar_MB(nombre, descripcion, status,  tipo);
        
                
        ActividadExtraescolar_CrearActividad_DAO.insertar(actividad, resp);
        

                
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
