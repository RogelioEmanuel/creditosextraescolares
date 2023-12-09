package Servlets.Maestros;

import ManageBean.Maestros.Maestros_MB;
import Utilidades.GenericResponse;
import dao.maestros.Maestros_ListarMaestros_DAO;
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
public class ListarMaestro_Srv extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       
        List<Maestros_MB> maestros = new ArrayList<>();
        
        GenericResponse respuesta = new GenericResponse<>();
                
        maestros = Maestros_ListarMaestros_DAO.consultar();
        request.setAttribute("maestros", maestros);
       
        /*for (Map.Entry<String, Timer> entry : Constantes.tareas.entrySet()) {
            String nombreTarea = entry.getKey();
            Timer tarea = entry.getValue();

        }*/

     
         request.getRequestDispatcher("/views/Maestros/Paginas/ListadoMaestros_View.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
