
package Servlets.Grupos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import Servlets.ActividadExtraescolar.CrearActividadExtraescolar;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.Grupos.Grupos_ListarGrupos_DAO;
import dao.actividadextraesscolar.ActividadExtraescolar_CrearActividad_DAO;
import dao.creditos.Creditos_CrearCredito_DAO;
import dao.gruposyalumno.GruposAlumno_Inscripcion_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Map<Grupos_MB, List<HorariosGrupo_MB>> gruposConHorarios = new HashMap<>();
        request.setAttribute("grupos", grupos);
        List<String> diasSemana = Arrays.asList("lunes", "martes", "miercoles", "jueves", "viernes", "sabado");
        
        
        
        for(Grupos_MB grupo:grupos){
            List<HorariosGrupo_MB> horariosDelGrupo = GruposAlumno_Inscripcion_DAO.consultarhorario(grupo.getIdGrupo());
            
            List<HorariosGrupo_MB> horariosCompleta = new ArrayList<>();
            for (String dia : diasSemana) {
                // Verificar si hay un horario para este día
                boolean encontrado = false;
                for (HorariosGrupo_MB horario : horariosDelGrupo) {
                    if (dia.equals(horario.getDia())) {
                        horariosCompleta.add(horario);  // Agregar a la nueva lista
                        encontrado = true;
                        break;
                    }
                }

                // Si no se encontró un horario para este día, agregar una entrada predeterminada
                if (!encontrado) {
                    HorariosGrupo_MB horarioDefault = new HorariosGrupo_MB();
                    horarioDefault.setDia(dia);                
                    horarioDefault.setHoraInicio("--:--"); // Establecer hora de inicio predeterminada
                    horarioDefault.setHoraFinal("--:--");   // Establecer hora de fin predeterminada
                    horariosCompleta.add(horarioDefault);  // Agregar a la nueva lista
                }
                
            }
            
            
            gruposConHorarios.put(grupo, horariosCompleta);
        }
        
               
        request.setAttribute("gruposConHorarios", gruposConHorarios);
        
        request.getRequestDispatcher("/views/Grupos/Paginas/InscripcionGrupo.jsp").forward(request, response);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();    
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
        String nReinscripcions = request.getParameter("nReinscripcion");
        int nReinscripcion = Integer.parseInt(nReinscripcions);
        HttpSession session = request.getSession();
        GenericResponse resp = new GenericResponse();
        
        
         Grupos_MB grupo = GruposAlumno_Inscripcion_DAO.consultarGrupo(idGrupo);
        
         Alumnos_MB alumno = GruposAlumno_Inscripcion_DAO.consultarAlumno(17280330);
         if(GruposAlumno_Inscripcion_DAO.estaInscrito(alumno,grupo,resp)||GruposAlumno_Inscripcion_DAO.estaInscrito2(alumno, resp)){
             resp.setMensaje("El alumno ya se encuentra inscrito a un grupo");
             resp.setStatus(800);
         }else{
            GruposAlumno_Inscripcion_DAO.inscribir(alumno,grupo,resp,nReinscripcion); 
            if(Creditos_CrearCredito_DAO.consultarCreditoSel(alumno.getNoControl())){
                
            }else if(Creditos_CrearCredito_DAO.consultarCredito(alumno.getNoControl())){
                
            }else{
                Creditos_CrearCredito_DAO.insertar(alumno, grupo, resp);
            }
         }
         
         
                            
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {
            Logger.getLogger(CrearActividadExtraescolar.class.getName()).log(Level.SEVERE, null, e);
        }
        
        request.getRequestDispatcher("/views/Grupos/Paginas/InscripcionGrupo.jsp").forward(request, response);
       
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
