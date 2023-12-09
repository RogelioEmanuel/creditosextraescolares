
package Servlets.Grupos;

import ManageBean.Grupos.Grupos_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.Constantes;
import Utilidades.GenericResponse;
import dao.Grupos.Grupos_ListarGrupos_DAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ListarGrupos_Srv extends HttpServlet {

   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        int idActividad = Integer.parseInt(request.getParameter("idActividad")); 
        
        HttpSession session = request.getSession();
        session.setAttribute("idActividad", idActividad);        
        GenericResponse respuesta = new GenericResponse<>(); 
        
        List<Grupos_MB> grupos = new ArrayList<>();  
        grupos = Grupos_ListarGrupos_DAO.consultar(idActividad);
        
                
        request.setAttribute("grupos", grupos);        
        
        

                
        request.getRequestDispatcher("/views/Grupos/Paginas/ListadoGrupos_View.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
