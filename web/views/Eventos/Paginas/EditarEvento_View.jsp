
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="usuario" value="${sessionScope.usuario}" />
<c:set var="rolUsuario" value="${usuario.nombrePuesto}" />
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Crear Evento</title>
        
    </head>
    <header>
        <c:choose>
            <c:when test="${rolUsuario eq 'Estudiante'}">
                <%@include file="../../templates/HeaderAlumno_View.jsp" %>
            </c:when>
            <c:when test="${rolUsuario eq 'Admin'}">
                <%@include file="../../templates/Header_View.jsp" %>
            </c:when>
            <c:when test="${rolUsuario eq 'Maestro'}">
                <%@include file="../../templates/HeaderMaestro_View.jsp" %>
            </c:when>
            <c:otherwise>
                <%-- Manejo para otros roles o situaciones --%>
                <%@include file="../../templates/HeaderInvitado_View.jsp" %>
            </c:otherwise>
        </c:choose>
    
    </header>
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
                        Editar Evento    <label id="labelIdEvento" style="display: none;">${idEvento}</label>   
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">
                    <form id="FormCrearEvento">
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Nombre del Evento:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="inputNombreEvento" 
                                           id="nombreEvento" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Nombre delEvento" 
                                           required 
                                           minlength="1"
                                           maxlength="80" 
                                           value="${evento.nombreEvento}"
                                           title="Nombre del Evento">
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <label>Institucion Organizadora:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="inputInstitucionOrganizadora" 
                                           id="institucionOrganizadora" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Institucion Organizadora" 
                                           required 
                                           minlength="1"
                                           maxlength="80" 
                                           value="${evento.institucionOrganizadora}"
                                           title="InstitucionOrganizadora">
                                </div>
                            </div>
                            
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label >Tipo Evento:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <select name="inputTipo" 
                                            id="tipoEvento" 
                                            placeholder="Tipo"
                                            class="form-control input-sm"                                             
                                            required >
                                        <option value="Interno" <c:if test="${evento.tipoEvento eq 'Interno'}">
                                                                    selected
                                                                </c:if>>Interno</option>
                                        <option value="Externo"<c:if test="${evento.tipoEvento eq 'Externo'}">
                                                                    selected
                                                               </c:if>>Externo</option>
                                        
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
                                                                
                        </div>
                        
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Numero de participantes Masculinos</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputnumeroparticipantesm" 
                                           id="numeroparticipantesm" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="numeroparticipantesm" 
                                           required 
                                           value="${evento.noParticipantesh}"
                                           minlength="1"
                                           maxlength="80"  
                                           title="numeroparticipantesm">
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <label>Numero de participantes Femeninos</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputnumeroparticipantesf" 
                                           id="numeroparticipantesf" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="numeroparticipantesf" 
                                           required 
                                           value="${evento.noParticipantesm}"
                                           minlength="1"
                                           maxlength="80"  
                                           title="numeroparticipantesf">
                                </div>
                            </div>
                            
                        </div>
                       
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label >Fecha</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    <input name="inputFecha" 
                                           id="fecha" 
                                           autocomplete="off"
                                           date-date-format="dd/mm/yyyy"
                                           placeholder="dd/mm/yyyy" 
                                           maxlength="10"
                                           value="${fecha}"
                                           class="form-control input-sm datepicker" 
                                           required >
                                </div>
                                
                            </div>
                            
                            
                            <div class="col-md-6">
                                <label >Actividad Extraescolar</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <select name="inputActividad" 
                                            id="actividad" 
                                            placeholder="actividad"
                                            class="form-control input-sm"                                             
                                            required >
                                        <c:forEach var="actividad" items="${actividad}">
                                            <option value="${actividad.idActividad_Extraescolar}" <c:if test="${evento.idActividad eq actividad.idActividad_Extraescolar}">
                                                                                                        selected
                                                                                                  </c:if>>${actividad.nombre} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                                   
                            
                            
                        </div>
                        
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Resultado</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="resultado" 
                                           id="resultado" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="resultado" 
                                           required 
                                           value="${evento.resultado}"
                                           minlength="1"
                                           maxlength="80"  
                                           title="resultado">
                                </div>
                            </div>
                            
                            
                            
                        </div>
                        <div class="row" style="margin-top: 30px">                             
                            <div class="col-md-12" align="right">                                
                                <button id="btnEditarActividad" form="FormCrearEvento" type="submit"  class="btn btn-primary btn-sm " style="font-size:14px">
                                    <i class="fa fa-save"></i> 
                                </button> 
                                <button id="btnregresar" form="FormCrearEvento" type="submit"  class="btn btn-sm btn-danger" style="font-size:14px">
                                    <i class="fa fa-reply"></i> 
                                </button> 
                                

                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        

        
        
        
        <script src="../../js/jsgenerados/Inicio.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>        
        <script src="../../js/jsgenerados/eventosEditarEvento.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
