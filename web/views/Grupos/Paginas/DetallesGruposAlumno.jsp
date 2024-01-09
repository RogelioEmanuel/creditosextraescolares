<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Crear Grupo</title>
        
    </head>
    <header><%@include file="../../templates/HeaderAlumno_View.jsp" %></header>
    <body>
        
         <div class="container">
            <div id="PanelForm" class="panel panel-primary" style="margin-top: 2em">
                <div class="panel-heading">
                    <label id="labelIdGrupo" style="display: none;">${idGrupo}</label>
                    <h2 class="panel-title" id="HEADINGDP">
                        Detalles Grupo  ${noGrupo}       
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
                                           disabled="true"
                                           value="${cupo}"
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
                                           disabled="true"
                                           value="${noGrupo}"
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
                                            disabled="true"
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
                                            disabled
                                            required >
                                        <option value="Enero-Junio" <c:if test="${periodo eq 'Enero-Junio'}">
                                                                        selected
                                                                    </c:if>>Enero-Junio</option>
                                        <option value="Verano" <c:if test="${periodo eq 'Verano'}">
                                                                        selected
                                                                </c:if>>Verano</option>
                                        <option value="Agosto-Diciembre" <c:if test="${periodo eq 'Agosto-Diciembre'}">
                                                                            selected
                                                                         </c:if>>Agosto-Diciembre</option>
                                        
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
                                           disabled="true"
                                           minlength="1"
                                           maxlength="80"  
                                           value="${totalhorassemanal}"
                                           title="horastotales">
                                </div>
                            </div>
                            
                        </div>
                           <div class="row" style="margin-bottom: 20px">                           
                           
                            
                             <div class="col-md-5">
                                <label >Dias</label>
                                
                                <div class="input-group checkbox-columnas">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                        <%-- Iterar sobre la lista de horarios en la página JSP --%>
                                    <c:forEach var="horario" items="${horarios}">
                                        <label>
                                            <input type="checkbox" id="${horario.dia}" name="${horario.dia}" value="${horario.dia}" ${horario.horaInicio != null && horario.horaFinal != null ? 'checked' : ''} disabled> ${horario.dia}
                                            <input type="time" id="${horario.dia}Inicio" name="${horario.dia}Inicio" value="${horario.horaInicio != null ? horario.horaInicio : ''}" disabled>
                                            <input type="time" id="${horario.dia}Fin" name="${horario.dia}Fin" value="${horario.horaFinal != null ? horario.horaFinal : ''}"  disabled>
                                        </label>
                                    </c:forEach>

                                </div>
                            </div>
                            
                            
                        </div> 
                                           
                        <div class="row" style="margin-top: 30px">                             
                            <div class="col-md-12" align="right">                                
                                 
                                 
                                 <a title="Regresar" id="btnRegresar" href="/creditosextraescolares/app/gruposalumno/listargruposinscrito.do" class="btn btn-sm btn-danger" ><i class="fa fa-reply"></i> Regresar</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        
        <script src="../../js/jsgenerados/Inicio.js" type="text/javascript"></script>        
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/gruposDetalleGruposAlumno.js" type="text/javascript"></script>
        
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
