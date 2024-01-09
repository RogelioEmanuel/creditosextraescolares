
package Servlets.GruposAlumno;

import ManageBean.Alumnos.Alumnos_MB;
import Utilidades.GenericResponse;
import dao.gruposyalumno.GruposAlumno_ListaAlumnos_DAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import masterDAO.Empleado;

/**
 *
 * @author Emanuel
 */
public class ListarGruposAlumno_Srv extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Alumnos_MB> alumnos = new ArrayList<>();
        HttpSession session = request.getSession();
        GenericResponse respuesta = new GenericResponse<>();
        //Empleado a=(Empleado) session.getAttribute("usuario");
        
        
        int id = Integer.parseInt(request.getParameter("idGrupo"));                
        alumnos = GruposAlumno_ListaAlumnos_DAO.consultarAlumnoGrupo(id);
        request.setAttribute("alumnos", alumnos);
        request.setAttribute("idGrupo", id);
        
       
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
