/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Asistencias;

import ManageBean.Alumnos.Alumnos_MB;
import ManageBean.Asistencia.Clases_MB;
import ManageBean.Grupos.Grupos_MB;
import dao.Asistencias.Asistencias_ListarAsistencias_DAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emanuel
 */
public class ListarAsistencias_Srv extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Creando datos para mandar
        Map< Alumnos_MB, Map<String, List<String>>> mapaPrincipal = new LinkedHashMap<>();
        Map<String, List<String>> mapaInterno =  new LinkedHashMap<>();
        Map<String, List<Integer>> mapaInterno2 =  new LinkedHashMap<>();
        
        int idGrupo  = Integer.parseInt(request.getParameter("idGrupo"));
        List<Alumnos_MB> al = Asistencias_ListarAsistencias_DAO.consultarAlumnoGrupo(idGrupo);        
        
        Grupos_MB uwu = Asistencias_ListarAsistencias_DAO.consultarGrupo(idGrupo);
        List<String> primerPeriodo=new ArrayList<>();
        
        if(uwu.getPeriodo().equals("Enero-Junio")){
            primerPeriodo = Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio");
        }else if(uwu.getPeriodo().equals("Agosto-Diciembre")){
            primerPeriodo=Arrays.asList("Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        }else if(uwu.getPeriodo().equals("Verano")){
            primerPeriodo=Arrays.asList("Julio");
        }
        // Lista de meses de enero a junio
        List<Clases_MB> clases=new ArrayList<>();
        int mes1=0;
        int mes2=0;
        int mes3=0;
        int mes4=0;
        int mes5=0;
        int mes6=0;
        for(Alumnos_MB aux:al){
            for(String aa :primerPeriodo){
                
                clases= Asistencias_ListarAsistencias_DAO.consultarClases(idGrupo,aa);
                mapaInterno.put(aa, Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa));
                System.out.println(aa+  " "+ Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa));
                switch(aa){
                    case "Enero":
                        // Código para Enero
                        
                        for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                            mes1++;
                        }
                        break;
                    case "Febrero":
                        // Código para Febrero
                        for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                            mes2++;
                        }
                        break;
                    case "Marzo":
                        // Código para Marzo
                        for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                            mes3++;
                        }
                        break;
                    case "Abril":
                        // Código para Abril
                        for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                            mes4++;
                        }
                        break;
                    case "Mayo":
                        for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                            mes5++;
                        }
                        // Código para Mayo
                        break;
                    case "Junio":
                        for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                            mes6++;
                        }
                        // Código para Junio
                        break;
                }
                //System.out.println(aa+" "+ Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa));
            }
        }
        List<Integer> diasPrimerSemestre = new ArrayList<>();
        for(String clasaux :primerPeriodo){
            //diasPrimerSemestre.add(clasaux.getDia());
            //System.out.println("Serv"+clasaux);
        }

        // Lista de meses de agosto a diciembre
        //List<String> segundoPeriodo = Arrays.asList("Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        
        //mapaInterno.put(segundoPeriodo, diasSegundoSemestre);
        
        for(Alumnos_MB alu  : al ){
            mapaPrincipal.put(alu, mapaInterno);
        }
        request.setAttribute("enero",mes1);
        request.setAttribute("febrero", mes2);
        request.setAttribute("marzo",mes3);
        request.setAttribute("abril", mes4);
        request.setAttribute("mayo",mes5);
        request.setAttribute("junio", mes6);
        
        request.setAttribute("meses",primerPeriodo);
        request.setAttribute("mesdia", mapaInterno);
        request.setAttribute("asistencias",mapaPrincipal);
        request.getRequestDispatcher("/views/Asistencias/Paginas/ListadoAsistencias_View.jsp").forward(request, response);
        
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
