
package App;

import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.Asistencias.AsistenciasEventos_DAO;
import static dao.Asistencias.AsistenciasEventos_DAO.hayEvento;
import static dao.Asistencias.AsistenciasEventos_DAO.registrarAsistenciaEvento;
import dao.Asistencias.AsistenciasRegistrar_DAO;
import static dao.Asistencias.AsistenciasRegistrar_DAO.hayClase;
import static dao.Asistencias.AsistenciasRegistrar_DAO.inscrito;
import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emanuel
 */
public class App_RegistrarAsistenciaEvento_Srv extends HttpServlet {

    

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        GenericResponse resp = new GenericResponse<>();
        
        
        JsonReader reader = Json.createReader(request.getReader());
        JsonObject jsonContent = reader.readObject();
        String qr = jsonContent.get("qr").toString().replace('"', ' ').trim();
        String idEmpleado = jsonContent.get("idEmpleado").toString();
        
        int idEvento = Integer.parseInt(jsonContent.get("idEvento").toString());
        
        if(hayEvento(idEvento)){
            //if(inscrito(qr,idEvento)){
               // AsistenciasRegistrar_DAO.insertarClase(idEvento, resp);
                //AsistenciasRegistrar_DAO.insertarAsistencia(qr,idEvento , resp);
                if(!AsistenciasEventos_DAO.yaPaso(qr,idEvento)){
                    AsistenciasEventos_DAO.obtSex(qr,idEvento,resp);
                    registrarAsistenciaEvento(qr,idEvento,resp);                
                    resp.setResponseObject("Asistencia Guardada");
                }
                resp.setResponseObject("El alumno ya habia tomado asistencia");
            //}else{
                //resp.setResponseObject("El alumno no está inscrito a ningun grupo de esta actividad");
            //}
        }else{
            resp.setResponseObject("Este evento no se lleva a cabo hoy");
        }
        
        
        
       try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            Gson json = new Gson();
            String jsonResponse = json.toJson(resp);

            // Imprimir en la consola
            System.out.println("JSON Response: " + jsonResponse);

            // Imprimir en la respuesta HTTP
            out.print(jsonResponse);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
