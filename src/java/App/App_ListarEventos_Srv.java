/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import ManageBean.Eventos.Evento_MB;
import ManageBean.Grupos.Grupos_MB;
import Utilidades.GenericResponse;
import Utilidades.Validaciones;
import com.google.gson.Gson;
import dao.Grupos.Grupos_ListarGrupos_DAO;
import dao.eventos.Eventos_ListarEventos_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emanuel
 */
public class App_ListarEventos_Srv extends HttpServlet {

        

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        GenericResponse resp = new GenericResponse<>();
        List<Evento_MB> evento = new ArrayList<>();
        
        int idMaestro = -1;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "idEmpleado":
                        if (cookie.getValue() != null) {
                            
                            idMaestro = Integer.parseInt((cookie.getValue()));
                            
                        }
                          evento = Eventos_ListarEventos_DAO.consultarEventos(idMaestro);
                        
                                                
                        break;
                }
            }
        }
        System.out.println("Etra al servlet");

        resp.setStatus(Validaciones.VALIDATION_EXP);
        resp.setMensaje("Ok");
        resp.setResponseObject(evento);
        
        
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
            String jsonResponse = json.toJson(resp);
            System.out.println("JSON Response: " + jsonResponse);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
