/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.ActividadExtraescolar;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import Utilidades.GenericResponse;
import dao.actividadextraesscolar.ActividadExtraescolar_ListarActividad_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ListaActividadesDisponibles extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         List<ActividadExtraescolar_MB> actividades = new ArrayList<>();
        HttpSession session = request.getSession();
        GenericResponse respuesta = new GenericResponse<>();
                
        actividades = ActividadExtraescolar_ListarActividad_DAO.consultar();
        request.setAttribute("actividades", actividades);
       
       
       
        request.getRequestDispatcher("/views/Alumnos/Paginas/ListarActividadesDisponibles.jsp").forward(request, response);
    
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
