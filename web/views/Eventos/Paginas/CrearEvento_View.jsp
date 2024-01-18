
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="rolUsuario" value="${usuario.nombrePuesto}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Crear Evento</title>
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-modal.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/catalogos-tablas.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/bootstrap-datepicker.estandarITT.css" rel="stylesheet">
    </head>
    <header><c:choose>
                <c:when test="${rolUsuario eq 'Estudiante'}">
                    <%@include file="../../templates/HeaderAlumno_View.jsp" %>
                </c:when>
                <c:when test="${rolUsuario eq 'Admin'}">
                   <%@include file="../../templates/Header_View.jsp" %></header>
                </c:when>
                <c:when test="${rolUsuario eq 'Maestro'}">
                    <%@include file="../../templates/HeaderMaestro_View.jsp" %>
                </c:when>
                <c:otherwise>
                    <%-- Manejo para otros roles o situaciones --%>
                    <%@include file="../../templates/HeaderInvitado_View.jsp" %>
                </c:otherwise>
            </c:choose>
        
        
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
                        Crear Evento       
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
                                        <option value="Interno">Interno</option>
                                        <option value="Externo">Externo</option>
                                        
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
                                <label>Numero de participantes totales Masculinos</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputnumeroparticipantesm" 
                                           id="numeroparticipantesm" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="numeroparticipantesm" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           title="numeroparticipantesm">
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <label>Numero de participantes totales Femeninos</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputnumeroparticipantesf" 
                                           id="numeroparticipantesf" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="numeroparticipantesf" 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           title="numeroparticipantesf">
                                </div>
                            </div>
                            
                        </div>
                        
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Numero de participantes Masculinos internos</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputnumeroparticipantesmi" 
                                           id="numeroparticipantesmi" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="numeroparticipantesmi" 
                                           required 
                                           disabled
                                           minlength="1"
                                           maxlength="80"  
                                           title="numeroparticipantesmi"
                                           value="0">
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <label>Numero de participantes Femeninos internos</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputnumeroparticipantesfi" 
                                           id="numeroparticipantesfi" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="numeroparticipantesfi" 
                                           required 
                                           disabled
                                           minlength="1"
                                           maxlength="80"  
                                           title="numeroparticipantesfi"
                                           value="0">
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
                                            <option value="${actividad.idActividad_Extraescolar}">${actividad.nombre} </option>
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
                                           minlength="1"
                                           maxlength="80"  
                                           title="resultado">
                                </div>
                            </div>
                            
                            
                            
                        </div>
                        <div class="row" style="margin-top: 30px">                             
                            <div class="col-md-12" align="right">                                
                                <button id="btnRegistrar" form="FormCrearEvento" type="submit"  class="btn btn-primary btn-sm " style="font-size:14px">
                                    <i class="fa fa-save"></i> 
                                </button>    
                                <a title="Regresar" id="regresar" href="/creditosextraescolares/index.jsp" class="btn btn-sm btn-danger" style="font-size:14px"><i class="fa fa-reply"></i></a>

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
        
        
       
        <script src="../../js/jsgenerados/Inicio.js" type="text/javascript"></script>        
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>   
        <script src="../../js/jsgenerados/eventosCrearEvento.js" type="text/javascript"></script>
        
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
