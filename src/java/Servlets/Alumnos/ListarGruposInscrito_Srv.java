
package Servlets.Alumnos;

import ManageBean.Grupos.Grupos_MB;
import Utilidades.GenericResponse;
import dao.Alumnos.AlumnosListarGruposInscrito_DAO;
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
import masterDAO.Usuario;

/**
 *
 * @author Emanuel
 */
public class ListarGruposInscrito_Srv extends HttpServlet {

    
   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario maestro= (Usuario) session.getAttribute("usuario");
        
        
        //System.out.println("Deberia"+maestro.getIdEmpleado());
        String idAlumno= maestro.getIdUsuario();
        
              
        GenericResponse respuesta = new GenericResponse<>(); 
        
        List<Grupos_MB> grupos = new ArrayList<>();  
        grupos = AlumnosListarGruposInscrito_DAO.consultarGr(idAlumno);
        
                
        request.setAttribute("grupos", grupos);      
        request.getRequestDispatcher("/views/Grupos/Paginas/ListarGruposAlumnos_View.jsp").forward(request, response);
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
