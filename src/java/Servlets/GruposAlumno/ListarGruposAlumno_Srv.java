/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.GruposAlumno;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Alumnos.Alumnos_MB;
import Utilidades.GenericResponse;
import dao.actividadextraesscolar.ActividadExtraescolar_ListarActividad_DAO;
import dao.gruposyalumno.GruposAlumno_ListaAlumnos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ListarGruposAlumno_Srv extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Alumnos_MB> alumnos = new ArrayList<>();
        
        GenericResponse respuesta = new GenericResponse<>();
        int id = Integer.parseInt(request.getParameter("idGrupo"));                
        alumnos = GruposAlumno_ListaAlumnos_DAO.consultarAlumnoGrupo(id);
        request.setAttribute("alumnos", alumnos);
       
        /*for (Map.Entry<String, Timer> entry : Constantes.tareas.entrySet()) {
            String nombreTarea = entry.getKey();
            Timer tarea = entry.getValue();

        }*/
       
        request.getRequestDispatcher("/views/Alumnos/Paginas/ListadoAlumnos_View.jsp").forward(request, response);
        
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
