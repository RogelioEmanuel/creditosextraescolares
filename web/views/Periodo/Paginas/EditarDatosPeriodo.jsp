<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Editar periodo</title>
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
    <header><%@include file="../../templates/Header_View.jsp" %></header>
    <body>
        
         <div class="container">
            <div id="PanelForm" class="panel panel-primary" style="margin-top: 2em">
                <div class="panel-heading">
                    
                    <h2 class="panel-title" id="HEADINGDP">
                        Editar Periodo       
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">
                    <form id="FormEditarPeriodo">
                        
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
                                        <option value="Enero-Junio" <c:if test="${periodo.periodo eq 'Enero-Junio'}">
                                                                        selected
                                                                    </c:if>>Enero-Junio</option>
                                        <option value="Verano" <c:if test="${periodo.periodo eq 'Verano'}">
                                                                        selected
                                                                </c:if>>Verano</option>
                                        <option value="Agosto-Diciembre" <c:if test="${periodo.periodo eq 'Agosto-Diciembre'}">
                                                                            selected
                                                                         </c:if>>Agosto-Diciembre</option>
                                        
                                    </select>
                                </div>
                            </div>
                            
                             
                        </div>
                                           
                                            
                        <div class="row" style="margin-bottom: 20px">
                               
                               <div class="col-md-4">
                                <label >Fecha Inicio:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    <input name="inputFechaInicio" 
                                           id="fechaInicio" 
                                           autocomplete="off"
                                           date-date-format="dd/mm/yyyy"
                                           placeholder="dd/mm/yyyy" 
                                           maxlength="10"
                                           value="${fechaI}"
                                           class="form-control input-sm datepicker" 
                                           required >
                                </div>
                                </div>
                                           
                            <div class="col-md-6">
                                <label>Dias inscripcion</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="insscripcion" 
                                           id="insscripcion" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="inscripcion " 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           value="${periodo.insscripcion}"
                                           title="inscripcion">
                                </div>
                            </div>
                            
                            
                            
                        </div>
                                           
                        <div class="row" style="margin-bottom: 20px">
                               
                               <div class="col-md-4">
                                <label >Fecha Fin</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    <input name="inputFechaFin" 
                                           id="fechaFin" 
                                           autocomplete="off"
                                           date-date-format="dd/mm/yyyy"
                                           placeholder="dd/mm/yyyy" 
                                           maxlength="10"
                                           value="${fechaF}"
                                           class="form-control input-sm datepicker" 
                                           required >
                                </div>
                                </div>
                                           
                            <div class="col-md-6">
                                <label>Dias cierre</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="cierre" 
                                           id="cierre" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="cierre " 
                                           required 
                                           minlength="1"
                                           maxlength="80"  
                                           value="${periodo.cierre}"
                                           title="cierre">
                                </div>
                            </div>
                                                        
                        </div>                   
                                           
                        <div class="row" style="margin-top: 30px">                             
                            <div class="col-md-12" align="right">                                
                                <button id="btnRegistrar" form="FormEditarPeriodo" type="submit"   class="btn btn-primary btn-sm " style="font-size:14px">
                                    <i class="fa fa-save"></i>Guardar 
                                </button>    
                                 <a title="Regresar" id="btnregresar" href="/creditosextraescolares/app/Index/Home.do" class="btn btn-sm btn-danger" style="font-size:14px"><i class="fa fa-reply"></i>Regresar</a>
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
        
        <script src="../../js/jsgenerados/periodoEditarPeriodo.js" type="text/javascript"></script>
        
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
