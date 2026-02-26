/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.PlandeTrabajo;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.PlandeTrabajo.PlanTrabajo_MB;
import Utilidades.GenericResponse;
import dao.Grupos.Grupos_ListarGrupos_DAO;
import dao.plandetrabajo.Planes_ListarPlanes_DAO;
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
public class ListarPlanesdeTrabajo_Srv extends HttpServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        
        HttpSession session = request.getSession();
               
        GenericResponse respuesta = new GenericResponse<>(); 
        
        List<PlanTrabajo_MB> planes = new ArrayList<>();
        
        planes = Planes_ListarPlanes_DAO.consultar();
        
        request.setAttribute("planes", planes);        
            
         request.getRequestDispatcher("/views/Planes_de_Trabajo/Paginas/ListarPlanes_View.jsp").forward(request, response);
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
