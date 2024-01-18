/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Maestros;

import ManageBean.Maestros.Maestros_MB;
import Servlets.ActividadExtraescolar.EditarActividadExtraescolar_Srv;
import Utilidades.GenericResponse;
import com.google.gson.Gson;
import dao.maestros.Maestros_EditarMaestros_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class EditarMaestro_Srv extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //PrintWriter out = response.getWriter();
        
         
        int idMaestro = Integer.parseInt(request.getParameter("idMaestro"));        
        HttpSession session = request.getSession();
        session.setAttribute("idMaestro", idMaestro);   
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Maestros_MB maestro = Maestros_EditarMaestros_DAO.consultar(idMaestro);
        
        String nombreMaestro = maestro.getNombre();
        String apPaterno = maestro.getApPaterno();
        String apMaterno = maestro.getApMaterno();
        String correo = maestro.getCorreo();
        String telefono = maestro.getTelefono();
        
        String rfc =maestro.getRfc();
        String curp = maestro.getCurp();
        String banco = maestro.getBanco();
        String clave = maestro.getClave();
        String sexo = maestro.getSexo();
        String direccion = maestro.getDireccion();
        String fecha_nacimiento="";
        
        if (maestro.getFecha_nacimiento() != null) {
            fecha_nacimiento = formatoFecha.format(maestro.getFecha_nacimiento());
        }
        
        
        
        
        
        request.setAttribute("nombre", nombreMaestro);
        request.setAttribute("appaterno", apPaterno);
        request.setAttribute("apmaterno", apMaterno);
        request.setAttribute("correo", correo);
        request.setAttribute("telefono", telefono);
        request.setAttribute("rfc", rfc);
        request.setAttribute("curp", curp);
        request.setAttribute("banco", banco);
        request.setAttribute("claveinterbancaria", clave);
        request.setAttribute("sexo", sexo);
        request.setAttribute("fecha_nacimiento", fecha_nacimiento);
        request.setAttribute("direccion",direccion);       
        
        request.getRequestDispatcher("/views/Maestros/Paginas/EditarMaestro_View.jsp").forward(request, response);



    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int idMaestro;
        
        String nombre = request.getParameter("nombre");
        String apPaterno = request.getParameter("appaterno");
        
        String apMaterno =request.getParameter("apmaterno");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefonoMaestro");        
        String rfc =request.getParameter("rfc");
        String curp = request.getParameter("curp");
        String banco = request.getParameter("banco");
        String clave =request.getParameter("claveinterbancaria");
        String sexo = request.getParameter("sexo");
        String fecha_nacimiento = request.getParameter("fechanacimiento");
        String direccion = request.getParameter("direccion");
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = null;
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("pass");
        
        try {
            
                fechaNacimiento= f.parse(fecha_nacimiento);
            
            
        } catch (ParseException ex) {
            fechaNacimiento = null;
            
        }
               
        HttpSession session = request.getSession();
        
        
        idMaestro = Integer.parseInt(session.getAttribute("idMaestro").toString());       
        GenericResponse resp = new GenericResponse();
        
        Maestros_MB maestro = new Maestros_MB(nombre, apPaterno, apMaterno,correo,telefono,fechaNacimiento,banco,curp,rfc,sexo,clave,direccion);      
        maestro.setIdMaestros(idMaestro);
            
        Maestros_EditarMaestros_DAO.actualizarMaestro(maestro, resp);
                
       
        try {
            response.setContentType("application/json");
            Gson json = new Gson();
            out.print(json.toJson(resp));
        } catch (Exception e) {
            Logger.getLogger(EditarActividadExtraescolar_Srv.class.getName()).log(Level.SEVERE, null, e);
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
