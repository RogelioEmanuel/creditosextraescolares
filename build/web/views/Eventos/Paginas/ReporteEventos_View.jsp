
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Crear Evento</title>
        
    </head>
    <header><%@include file="../../templates/Header_View.jsp" %></header>
    <body>
        <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="../../templates/spinner.estandarITT.jsp" %>
            </div>
        </div>
                  
        <div class="container">
            <div id="PanelForm" class="panel panel-primary" style="margin-top: 2em">
                <div class="panel-heading">
                    <h2 class="panel-title" id="HEADINGDP">
                        Crear Reporte Evento       
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">
                    <form id="FormCrearEvento">
                        
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label >Actividad Extraescolar</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <select name="inputActividad" 
                                            id="actividad" 
                                            placeholder="actividad"
                                            class="form-control input-sm"                                             
                                            required >
                                        <c:forEach var="actividad" items="${actividad}">
                                            <option value="${actividad.idActividad_Extraescolar}">${actividad.nombre} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            
                             <div class="col-md-6">
                                <label>Periodo:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <select name="periodo" 
                                            id="periodo" 
                                            placeholder="periodo"
                                            class="form-control input-sm"                                             
                                            required >
                                        <option value="Enero-Junio">Enero-Junio</option>
                                        <option value="Verano">Verano</option>
                                        <option value="Agosto-Diciembre">Agosto-Diciembre</option>
                                        
                                    </select>
                                </div>
                            </div>
                                                                
                        </div>
                        
                        
                       
                        <div class="row" style="margin-bottom: 20px">
                            
                            <div class="col-md-6">
                                <label >Año del Evento:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="anioEvento" 
                                           id="anio" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Año actual" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           title="Año del Evento">
                                </div>
                            </div>
                            
                            
                                   
                            
                            
                        </div>
                        
                       
                        <div class="row" style="margin-top: 30px">                             
                            <div class="col-md-12" align="right">                                
                                <button id="generarReporte" form="FormCrearEvento" type="submit"  class="btn btn-primary btn-sm " style="font-size:14px">
                                    <i class="fa fa-save"></i> Generar Reporte
                                </button>    
                                <a title="Regresar" id="btnregresar" href="/creditosextraescolares/app/eventos/listarevento.do" class="btn btn-sm btn-danger" style="font-size:14px"><i class="fa fa-reply"></i> Regresar
                                </a>

                            </div>
                        </div>
                    </form>
                    
                    <div style="margin-top: 30px" id="divReporte">
                        <iframe width="100%" height="800px" src="" id="ApartadoPDF"></iframe>
                    </div>
                </div>
            </div>
        </div>
        
        

       
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/Inicio.js" type="text/javascript"></script>   
        <script src="../../js/jsgenerados/eventosReportes.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
        
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
