
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
import javax.servlet.http.HttpSession;


public class ListarAsistencias_Srv extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Creando datos para mandar esto manda un alumno con una coleccion que contiene mes, clases
        Map< Alumnos_MB, Map<String, List<String>>> mapaPrincipal = new LinkedHashMap<>();
        
        Map<String, List<Integer>> mapaInterno2 =  new LinkedHashMap<>();
        
        //Obtenemos el id del grupo del que revisaremos las asistencias
        int idGrupo  = Integer.parseInt(request.getParameter("idGrupo"));
        List<Alumnos_MB> al = Asistencias_ListarAsistencias_DAO.consultarAlumnoGrupo(idGrupo); 
        Grupos_MB uwu = Asistencias_ListarAsistencias_DAO.consultarGrupo(idGrupo);
        
        //Creamos una lista de meses que tendra el periodo
        List<String> primerPeriodo=new ArrayList<>();
        
        //Marcamos los meses del periodo
        if(uwu.getPeriodo().equals("Enero-Junio")){
            primerPeriodo = Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio");
        }else if(uwu.getPeriodo().equals("Agosto-Diciembre")){
            primerPeriodo=Arrays.asList("Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        }else if(uwu.getPeriodo().equals("Verano")){
            primerPeriodo=Arrays.asList("Julio");
        }
        
        //Lista de clases 
        List<Clases_MB> clases=new ArrayList<>();
        
        // Lista de meses maximos por periodo         
        int mes1=0;
        int mes2=0;
        int mes3=0;
        int mes4=0;
        int mes5=0;
        int mes6=0;
        
        //Por cada alumno inscito
        for(Alumnos_MB aux:al){
            // Lista de meses maximos por periodo         
            mes1=0;
            mes2=0;
            mes3=0;
            mes4=0;
            mes5=0;
            mes6=0;
            Map<String, List<String>> mapaInterno =  new LinkedHashMap<>();
            //Por cada mes
            for(String aa :primerPeriodo){
                //Llenamos las clases del grupo en el mes
                
                clases= Asistencias_ListarAsistencias_DAO.consultarClases(idGrupo,aa);
                //Añadimos el mes y la clases en las que el usuario partizo, (aqui llama todas, pero el metodo pone una paloma o cruz dependiendo si asistio o no)
                mapaInterno.put(aa, Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa));
                mapaInterno2.put(aa, Asistencias_ListarAsistencias_DAO.clasesFechas(idGrupo, clases, aa));
                //Aumentamos la cantidad de clase que tuvo cada mes, Dependiendo del periodo
                if(uwu.getPeriodo().equals("Agosto-Diciembre")){                    
                    switch(aa){
                        case "Agosto":                            
                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes1++;
                            }
                            break;
                        case "Septiembre":

                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes2++;
                            }
                            break;
                        case "Octubre":

                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes3++;
                            }
                            break;
                        case "Noviembre":

                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes4++;
                            }
                            break;
                        case "Diciembre":
                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes5++;
                            }
                            break;
                       
                    }
                    
                }else if(uwu.getPeriodo().equals("Enero-Junio")){
                    switch(aa){
                        case "Enero":
                            
                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes1++;
                            }
                            break;
                        case "Febrero":

                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes2++;
                            }
                            break;
                        case "Marzo":

                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes3++;
                            }
                            break;
                        case "Abril":

                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes4++;
                            }
                            break;
                        case "Mayo":
                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes5++;
                            }

                            break;
                        case "Junio":
                            for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                                mes6++;
                            }                        
                            break;
                    }
                
                }else{
                    for(String b:Asistencias_ListarAsistencias_DAO.revisarsiVino(aux.getNoControl(), clases,aa) ){
                        mes1++;
                    }
                }
            }
               
            mapaPrincipal.put(aux, mapaInterno);
             
        }
        
        
        //Mandamos los atributos para la vista
        request.setAttribute("periodo",uwu.getPeriodo());
        //Cantidad de dias por cada mes que se manda, si 
        
        request.setAttribute("mes1",mes1);
        
        
        if(uwu.getPeriodo().equals("Enero-Junio")||uwu.getPeriodo().equals("Agosto-Diciembre")){
            request.setAttribute("mes2", mes2);
            request.setAttribute("mes3",mes3);
            request.setAttribute("mes4", mes4);
            request.setAttribute("mes5",mes5);
            
        }
        
        //Si el periodo es Enero Junio, se manda el mes de Junio 
        if(uwu.getPeriodo().equals("Enero-Junio")){
          request.setAttribute("mes6", mes6);  
        }
        
        
        request.setAttribute("meses",primerPeriodo);
        request.setAttribute("mesdia", mapaInterno2);
        request.setAttribute("asistencias",mapaPrincipal);
        request.getRequestDispatcher("/views/Asistencias/Paginas/ListadoAsistencias_View.jsp").forward(request, response);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        

    }
    

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
