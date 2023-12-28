
package Servlets.Grupos;

import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import Utilidades.GenericResponse;
import dao.Grupos.Grupos_ListarGrupos_DAO;
import dao.gruposyalumno.GruposAlumno_Inscripcion_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emanuel
 */
public class InscripcionGrupos_Srv extends HttpServlet {

   
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
                
        GenericResponse respuesta = new GenericResponse<>(); 
        
        List<Grupos_MB> grupos = new ArrayList<>();  
        grupos = GruposAlumno_Inscripcion_DAO.consultar();
        Map<Integer, List<HorariosGrupo_MB>> gruposConHorarios = new HashMap<>();
        request.setAttribute("grupos", grupos);
        
        for(Grupos_MB grupo:grupos){
            List<HorariosGrupo_MB> horariosDelGrupo = GruposAlumno_Inscripcion_DAO.consultarhorario(grupo.getIdGrupo());
            gruposConHorarios.put(grupo.getIdGrupo(), horariosDelGrupo);
        }
        request.setAttribute("gruposConHorarios", gruposConHorarios);
        
        request.getRequestDispatcher("/views/Grupos/Paginas/InscripcionGrupo.jsp").forward(request, response);
        
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
