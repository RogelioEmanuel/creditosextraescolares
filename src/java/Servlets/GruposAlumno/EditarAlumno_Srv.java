
package Servlets.GruposAlumno;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Grupos.Grupos_MB;
import Servlets.ActividadExtraescolar.EditarActividadExtraescolar_Srv;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.actividadextraesscolar.ActividadExtraescolar_EditarActividad_DAO;
import dao.gruposyalumno.GruposAlumno_Selectivo_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EditarAlumno_Srv extends HttpServlet {

   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String noControl = request.getParameter("noControl");
        int id =Integer.parseInt(request.getParameter("idGrupo"));
        Alumnos_MB al = GruposAlumno_Selectivo_DAO.consultarAlumno(noControl,id);
        request.setAttribute("alumno", al);
        request.getRequestDispatcher("/views/Alumnos/Paginas/EstadoAlumno_View.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         PrintWriter out = response.getWriter();
        
        String nocontrol = request.getParameter("noControl");        
        String selectivo = request.getParameter("selectivo");
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
        int noReinscripcion = Integer.parseInt(request.getParameter("nReinscripcion"));
        
               
        HttpSession session = request.getSession();             
        GenericResponse resp = new GenericResponse();             
        Alumnos_MB rogelio = GruposAlumno_Selectivo_DAO.consultarAlumno(nocontrol,idGrupo);
        rogelio.setSelectivo(selectivo);
        rogelio.setNoReinscripcion(noReinscripcion);
        Grupos_MB grupo = new Grupos_MB();
        grupo.setIdGrupo(idGrupo);
        if(GruposAlumno_Selectivo_DAO.candidatoValido(rogelio)){
            GruposAlumno_Selectivo_DAO.actualizarAlumno(rogelio, grupo, resp);
        }else{
            
            resp.setMensaje("La situacion del alumno no permite considerarlo como selectivo");
            resp.setStatus(801);
        }   
                
       
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {
            Logger.getLogger(EditarActividadExtraescolar_Srv.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
