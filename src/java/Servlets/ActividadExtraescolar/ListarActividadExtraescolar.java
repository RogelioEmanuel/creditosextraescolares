/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.ActividadExtraescolar;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import Utilidades.GenericResponse;
import dao.actividadextraesscolar.ActividadExtraescolar_ListarActividad_DAO;
import dao.gruposyalumno.GruposAlumno_Inscripcion_DAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emanuel
 */
public class ListarActividadExtraescolar extends HttpServlet {


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
        //processRequest(request, response);
        
        List<ActividadExtraescolar_MB> actividades = new ArrayList<>();
        
        GenericResponse respuesta = new GenericResponse<>();
                
        actividades = ActividadExtraescolar_ListarActividad_DAO.consultar();
        request.setAttribute("actividades", actividades);
       
        /*for (Map.Entry<String, Timer> entry : Constantes.tareas.entrySet()) {
            String nombreTarea = entry.getKey();
            Timer tarea = entry.getValue();

        }*/
       
        request.getRequestDispatcher("/views/Actividad_Extraescolar/Paginas/ListadoActividad_View.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
