
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Crear Grupo</title>
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-modal.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/bootstrap-datepicker.estandarITT.css" rel="stylesheet">
        <link href="../../css/cssgenerados/styles.css" rel="stylesheet" type="text/css"/>
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
                        Crear Grupo         
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">
                    <form id="FormCrearGrupo">
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-5">
                                <label>Cupo:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputCupo" 
                                           id="cupo" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Cupo" 
                                           required 
                                           minlength="1"
                                           maxlength="80"                                             
                                           title="Cupo">
                                </div>
                            </div>
                            
                            <div class="col-md-5">
                                <label>Numero de Grupo:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputoGrupo" 
                                           id="noGrupo" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="noGrupo" 
                                           required 
                                           minlength="1"
                                           maxlength="80"                                             
                                           title="noGrupo">
                                </div>
                            </div>
                           
                            
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            
                             <div class="col-md-5">
                                <label >Maestro</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <select name="inputMaestro" 
                                            id="maestro" 
                                            placeholder="Maestro"
                                            class="form-control input-sm"                                             
                                            required >
                                        <c:forEach var="maestro" items="${maestros}">
                                            <option value="${maestro.idMaestros}">${maestro.nombre} ${maestro.apPaterno} ${maestro.apMaterno}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                           
                            <div class="col-md-5">
                                <label >Actividad Extraescolar</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <select name="inputActividadExtraescolar" 
                                            id="idActividad" 
                                            placeholder="ActividadExtraescolar"
                                            disabled="true"
                                            
                                            class="form-control input-sm"                                             
                                            required >
                                        <option value="${actividad.idActividad_Extraescolar}">${actividad.nombre}</option>
                                                                                   
                                    </select>
                                </div>
                            </div>
                            
                            
                                                                
                        </div>
                       
                        
                        
                        <div class="row" style="margin-bottom: 20px">
                            
                            <div class="col-md-5">
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
                            
                            <div class="col-md-5">
                                <label>Horas totales a la semana:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputCupo" 
                                           id="horastotales" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="horastotales" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           
                                           title="horastotales">
                                </div>
                            </div>
                            
                             
                        </div>
                           <div class="row" style="margin-bottom: 20px">                           
                           
                            
                             <div class="col-md-5">
                                <label >Dias</label>
                                
                                <div class="input-group checkbox-columnas">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                        <label>
                                            <input type="checkbox" id="lunes" name="lunes" value="Lunes"> Lunes 
                                            <input type="time" id="lunesInicio" name="lunesInicio"  style="display:none">
                                            <input type="time" id="lunesFin" name="lunesFin" style="display:none">
                                        </label> 
                                        
                                        <label>
                                            <input type="checkbox"  id="martes" name="martes" value="Martes"> Martes  
                                            <input type="time" id="martesInicio" name="martesInicio"  style="display:none">
                                            <input type="time" id="martesFin" name="martesFin" style="display:none">
                                        </label>                                        
                                        <label>
                                            <input type="checkbox" id="miercoles" name="miercoles" value="Miercoles"> Miercoles 
                                            <input type="time" id="miercolesInicio" name="miercolesInicio"  style="display:none">
                                            <input type="time" id="miercolesFin" name="miercolesFin" style="display:none">
                                        </label>                                          
                                        <label>
                                            <input type="checkbox" id="jueves" name="jueves" value="Jueves"> Jueves
                                            <input type="time" id="juevesInicio" name="juevesInicio"  style="display:none">
                                            <input type="time" id="juevesFin" name="juevesFin" style="display:none">
                                        </label>                                                                                
                                        <label>
                                            <input type="checkbox" id="viernes" name="viernes" value="Viernes"> Viernes
                                            <input type="time" id="viernesInicio" name="viernesInicio"  style="display:none">
                                            <input type="time" id="viernesFin" name="viernesFin" style="display:none">
                                        </label> 
                                        
                                        <label>
                                            <input type="checkbox" id="sabado" name="sabado" value="Sabado"> Sabado 
                                            <input type="time" id="sabadoInicio" name="sabadoInicio"  style="display:none">
                                            <input type="time" id="sabadoFin" name="sabadoFin" style="display:none">
                                        </label>  
                                       
                                        
                                </div>
                            </div>
                            
                            
                        </div> 
                                           
                        <div class="row" style="margin-top: 30px">                             
                            <div class="col-md-12" align="right">                                
                                <button id="btnRegistrar" form="FormCrearGrupo" type="submit"   class="btn btn-primary btn-sm " style="font-size:14px">
                                    <i class="fa fa-save"></i> 
                                </button>    
                                 <a title="Regresar" id="btnregresar" href="/creditosextraescolares/app/actividadextraescolar/listaractividad.do" class="btn btn-sm btn-danger" style="font-size:14px"><i class="fa fa-reply"></i></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        

        <script type="text/javascript" src ="\CDN-ITT\js\jquery.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery-ui.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\base.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\spinner.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery.dataTables.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\tablas.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\catalogos-modal.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\catalogos-tablas.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\bootstrap-datepicker.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\bootstrap-datepicker.es.estandarITT.js"></script>
        
        
       
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        
        <script src="../../js/jsgenerados/gruposCrearGrupo.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
