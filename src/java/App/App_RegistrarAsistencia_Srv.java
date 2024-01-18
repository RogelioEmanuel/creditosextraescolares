package App;

import Utilidades.GenericResponse;
import com.google.gson.Gson;
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
import masterDAO.Empleado;
import masterDAO.EmpleadoDao;


public class App_RegistrarAsistencia_Srv extends HttpServlet {

   
    

    
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
        
        int idGrupo = Integer.parseInt(jsonContent.get("idGrupo").toString());
        System.out.println(idGrupo);
        
        
        if(hayClase(idGrupo)){
            if(inscrito(qr,idGrupo)){
                AsistenciasRegistrar_DAO.insertarClase(idGrupo, resp);
                AsistenciasRegistrar_DAO.insertarAsistencia(qr,idGrupo , resp);
                resp.setResponseObject("Asistencia Guardada");
            }else{
                resp.setResponseObject("El alumno no está inscrito al grupo");
            }
        }else{
            resp.setResponseObject("No hay clase programada en este horario");
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
