/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Eventos;

import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.eventos.Eventos_EliminarEvento_DAO;
import dao.maestros.Maestros_EliminarMaestros_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emanuel
 */
public class EliminarEventos_Srv extends HttpServlet {

   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        GenericResponse resp = new GenericResponse<>();
        
        //Obtener los usuarios eliminados consultando
        
        //Obtener el id de la invitacion
        
        int idEvento = Integer.parseInt(request.getParameter("idEvento"));        
        HttpSession session = request.getSession();
        //session.setAttribute("idEvento", idEvento);  

        Eventos_EliminarEvento_DAO.eliminar(idEvento, resp);
        
        
        
        
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {

        }
        
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
