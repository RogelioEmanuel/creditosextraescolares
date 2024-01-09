/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.CreditosExtraescolares;

import ManageBean.CreditoExtraescolar.CreditoExtraescolar_MB;
import ManageBean.Maestros.Maestros_MB;
import Utilidades.GenericResponse;
import dao.creditos.Creditos_ListarCreditos_DAO;
import dao.maestros.Maestros_ListarMaestros_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emanuel
 */
public class ListarCreditoExtraescolar_Srv extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        List<CreditoExtraescolar_MB> credito = new ArrayList<>();
        HttpSession session = request.getSession();
        GenericResponse respuesta = new GenericResponse<>();
                
        credito =Creditos_ListarCreditos_DAO.consultar();
        request.setAttribute("creditos", credito);
        request.getRequestDispatcher("/views/Creditos_Extraescolares/Paginas/ListadoCreditoExtraescolar_View.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
        //request.getRequestDispatcher("/views/Creditos_Extraescolares/Paginas/ListadoCreditoExtraescolar_View.jsp").forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
