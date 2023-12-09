/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.ActividadExtraescolar;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.actividadextraesscolar.ActividadExtraescolar_EditarActividad_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emanuel
 */
public class EditarActividadExtraescolar_Srv extends HttpServlet {

  

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //PrintWriter out = response.getWriter();
        
        int idActividad = Integer.parseInt(request.getParameter("idActividad"));        
        HttpSession session = request.getSession();
        session.setAttribute("idActividad", idActividad);   
        ActividadExtraescolar_MB actividad = ActividadExtraescolar_EditarActividad_DAO.consultar(idActividad);
        
        String nombreActividad = actividad.getNombre();
        String tipo = actividad.getTipo();
        String descripcion = actividad.getDescripcion();
        int status = actividad.getStatus();
        
        
        request.setAttribute("nombre", nombreActividad);
        request.setAttribute("tipo", tipo);
        request.setAttribute("status", status);
        request.setAttribute("descripcion", descripcion);

        
        
        request.getRequestDispatcher("/views/Actividad_Extraescolar/Paginas/EditarActividad_View.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        int idActividad;
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        int status = Integer.parseInt(request.getParameter("status"));
        String descripcion = request.getParameter("descripcion");
               
        HttpSession session = request.getSession();
        idActividad = Integer.parseInt(session.getAttribute("idActividad").toString());       
        GenericResponse resp = new GenericResponse();
        ActividadExtraescolar_MB actividad = new ActividadExtraescolar_MB(nombre,tipo,status,descripcion);        
        actividad.setIdActividad_Extraescolar(idActividad);       
        ActividadExtraescolar_EditarActividad_DAO.actualizarActividad(actividad, resp);
            
                
       
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {
            Logger.getLogger(EditarActividadExtraescolar_Srv.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
