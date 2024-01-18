
package Servlets.Grupos;

import ManageBean.ActividadExtraescolar.ActividadExtraescolar_MB;
import ManageBean.Grupos.Grupos_MB;
import ManageBean.HorariosGrupo.HorariosGrupo_MB;
import ManageBean.Maestros.Maestros_MB;
import Servlets.ActividadExtraescolar.EditarActividadExtraescolar_Srv;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.Grupos.Grupos_CrearGrupo_DAO;
import dao.Grupos.Grupos_EditarGrupo_DAO;
import dao.actividadextraesscolar.ActividadExtraescolar_EditarActividad_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EditarGrupos_Srv extends HttpServlet {

   

    
    protected boolean confirmacion(String inicio, String fin){   
       
        return (inicio != null && !inicio.isEmpty() && fin != null && !fin.isEmpty()) || (inicio == null && fin == null);
    }
    
    public void gestionarHorario(String dia, String inicio, String fin, GenericResponse resp) {
    if (confirmacion(inicio, fin)) {
        HorariosGrupo_MB horario = new HorariosGrupo_MB(dia, inicio, fin);
        Grupos_EditarGrupo_DAO.actualizarHorario(horario, resp);
    } else {
        HorariosGrupo_MB horario = new HorariosGrupo_MB(dia, inicio, fin);
        Grupos_EditarGrupo_DAO.eliminarHorario(horario.getIdHorarioGrupo(), resp);
    }
}
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //PrintWriter out = response.getWriter();
        
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));        
        HttpSession session = request.getSession();
        session.setAttribute("idGrupo", idGrupo);                  
        Grupos_MB grupo = Grupos_EditarGrupo_DAO.consultar(idGrupo);
        
        
        String periodo = grupo.getPeriodo();
        int idActividad = grupo.getIdActividad();
        
        int cupo = grupo.getCupo();
        int noGrupo =grupo.getNoGrupo();
        int horastotales = grupo.getTotalhorassemanales();
        int idMaestros = grupo.getIdMaestros();
        System.out.println("horas"+horastotales);
        
        request.setAttribute("noGrupo", noGrupo);
        request.setAttribute("cupo", cupo);
        request.setAttribute("periodo", periodo);        
        request.setAttribute("idMaestros", idMaestros);        
        request.setAttribute("idActividad_Extraescolar", idActividad);        
        request.setAttribute("totalhorassemanal", horastotales); 
        List<Maestros_MB> maestros = new ArrayList<>();  
        maestros = Grupos_CrearGrupo_DAO.consultarMaestro();  
        request.setAttribute("maestros", maestros);
       
       session.setAttribute("idActividad", idActividad); 
        
       ActividadExtraescolar_MB actividad = new ActividadExtraescolar_MB();  
       actividad = Grupos_CrearGrupo_DAO.consultarActividad(idActividad);
       
       
       //HorariosGrupo_MB horario = new HorariosGrupo_MB();
       
       
       
      
        List<HorariosGrupo_MB> horarios = new ArrayList<>();  
        horarios = Grupos_EditarGrupo_DAO.consultarhorario(idGrupo);  
        
        
        
        
        List<HorariosGrupo_MB> horariosCompleta = new ArrayList<>();  // Crear una nueva lista

        List<String> diasSemana = Arrays.asList("lunes", "martes", "miercoles", "jueves", "viernes", "sabado");
        for (String dia : diasSemana) {
            // Verificar si hay un horario para este día
            boolean encontrado = false;
            for (HorariosGrupo_MB horario : horarios) {
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
                horarioDefault.setHoraInicio(null); // Establecer hora de inicio predeterminada
                horarioDefault.setHoraFinal(null);   // Establecer hora de fin predeterminada
                horariosCompleta.add(horarioDefault);  // Agregar a la nueva lista
            }
        }
        request.setAttribute("horarios", horariosCompleta); 
        
                
       request.setAttribute("actividad", actividad);
       
        
        
        
        request.getRequestDispatcher("/views/Grupos/Paginas/EditarGrupos_View.jsp").forward(request, response);
    }
    
    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
         System.out.println(request.getParameter("idGrupo"));
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
        int noGrupo = Integer.parseInt(request.getParameter("noGrupo"));
        int cupo = Integer.parseInt( request.getParameter("cupo"));
        int idActividad = Integer.parseInt (request.getParameter("idActividad")); 
        int idMaestros = Integer.parseInt(request.getParameter("maestro"));
        String periodo = request.getParameter("periodo");
        int totalhorassemanal = Integer.parseInt(request.getParameter("horastotales"));
        
        
        //Horario por dias
        String lunesInicio = request.getParameter("lunesInicio");
        String lunesFin = request.getParameter("lunesFin");

        String martesInicio = request.getParameter("martesInicio");
        String martesFin = request.getParameter("martesFin");

        String miercolesInicio = request.getParameter("miercolesInicio");
        String miercolesFin = request.getParameter("miercolesFin");

        String juevesInicio = request.getParameter("juevesInicio");
        String juevesFin = request.getParameter("juevesFin");

        String viernesInicio = request.getParameter("viernesInicio");
        String viernesFin = request.getParameter("viernesFin");

        String sabadoInicio = request.getParameter("sabadoInicio");
        String sabadoFin = request.getParameter("sabadoFin");
        
        HttpSession session = request.getSession();
           
        GenericResponse resp = new GenericResponse();
        Grupos_MB grupo = new Grupos_MB(noGrupo, cupo,  periodo, idActividad,  idMaestros, totalhorassemanal);       
        grupo.setIdGrupo(idGrupo);       
        Grupos_EditarGrupo_DAO.actualizarGrupo(grupo, resp);
        
        //Falta agregar un actualizar horario
        gestionarHorario("lunes", lunesInicio, lunesFin, resp);
        gestionarHorario("martes", martesInicio, martesFin, resp);
        gestionarHorario("miercoles", miercolesInicio, miercolesFin, resp);
        gestionarHorario("jueves", juevesInicio, juevesFin, resp);
        gestionarHorario("viernes", viernesInicio, viernesFin, resp);
        gestionarHorario("sabado", sabadoInicio, sabadoFin, resp);
        
         
                
       
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
