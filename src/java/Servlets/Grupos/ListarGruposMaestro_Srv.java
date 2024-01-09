package Servlets.Grupos;

import ManageBean.Grupos.Grupos_MB;
import Utilidades.GenericResponse;
import dao.Grupos.Grupos_ListarGrupos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import masterDAO.Empleado;



public class ListarGruposMaestro_Srv extends HttpServlet {

    
  

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //int idMaestro = Integer.parseInt(request.getParameter("idMaestro")); 
        
        HttpSession session = request.getSession();
        Empleado maestro= (Empleado) session.getAttribute("usuario");
        
        
        //System.out.println("Deberia"+maestro.getIdEmpleado());
        int idMaestro= maestro.getIdEmpleado();
        
        session.setAttribute("idMaestro", idMaestro);        
        GenericResponse respuesta = new GenericResponse<>(); 
        
        List<Grupos_MB> grupos = new ArrayList<>();  
        grupos = Grupos_ListarGrupos_DAO.consultarGr(idMaestro);
        
                
        request.setAttribute("grupos", grupos);        
        
        request.getRequestDispatcher("/views/Grupos/Paginas/ListarGrupoMaestros_View.jsp").forward(request, response);
        
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
