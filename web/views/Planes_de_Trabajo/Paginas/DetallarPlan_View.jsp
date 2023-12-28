<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Plan de trabajo detalles </title>
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
       <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="../../templates/spinner.estandarITT.jsp" %>
            </div>
        </div>
            
        <div class="container">
            <div id="PanelForm" class="panel panel-primary" style="margin-top: 2em">
                <div class="panel-heading">
                    <h2 class="panel-title" id="HEADINGDP">
                        Detalle Plan de Trabajo     
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">
                    <form id="FormCrearPlan">
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Actividad :</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <select name="actividades" 
                                            id="actividades" 
                                            placeholder="actividades"
                                            class="form-control input-sm"
                                            disabled="true"
                                            required >
                                            <option value="${plant.actividadExtraescolar}">${plant.nomactividad}</option>
                                    </select>
                                </div>
                            </div>                            
                            <div class="col-md-6">
                                <label >Maestro</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                   <select name="inputMaestro" 
                                            id="maestro" 
                                            placeholder="Maestro"
                                            class="form-control input-sm" 
                                            disabled="true"                                            
                                            required >
                                        
                                            <option value="${plant.maestro}">${plant.nomMaestro}</option>
                                        
                                    </select>
                                </div>
                            </div>                             
                        </div>
                        <c:forEach var="plan" items="${planessemanal}">
                            <div class="row" style="margin-bottom: 20px">                            
                                <div class="col-md-6">
                                    <label >Programa a impartir semana ${plan.noSemana}</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input name="programasemana${plan.noSemana}" 
                                               id="programasemana${plan.noSemana}" 
                                               class="form-control input-sm" 
                                               autocomplete="off" 
                                               placeholder="programa semana ${plan.noSemana}" 
                                               required 
                                               minlength="1"
                                               maxlength="80"
                                               disabled="true"
                                               value="${plan.programa}"
                                               title="programa semana ${plan.noSemana}">
                                    </div>
                                </div>                            
                                <div class="col-md-6">
                                    <label >Plataforma a Utilizar</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input name="plataformasemana${plan.noSemana}" 
                                               id="plataformasemana${plan.noSemana}" 
                                               class="form-control input-sm" 
                                               autocomplete="off" 
                                               placeholder="plataforma semana ${plan.noSemana}" 
                                               disabled="true" 
                                               minlength="1"
                                               maxlength="80" 
                                               value="${plan.plataforma}"
                                               title="plataforma semana ${plan.noSemana}">
                                    </div>
                                </div>                               
                            </div>                       
                            <div class="row" style="margin-bottom: 20px">
                                <div class="col-md-12">
                                    <label>¿Cómo lo hará?</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                        <textarea style="resize: none" 
                                                  name="inputDescripcionsemana${plan.noSemana}" 
                                                  id="descripcionsemana${plan.noSemana}" 
                                                  class="form-contro${plan.noSemana} input-sm" 
                                                  autocomplete="off" 
                                                  placeholder="¿Cómo lo harían?" 
                                                  disabled="true" 
                                                  minlength="1"
                                                  rows="4"
                                                  cols="130"
                                                  value="${plan.llevar_a_cabo}"
                                                  title="¿Cómo lo haría?">${plan.llevar_a_cabo}
                                        </textarea>
                                    </div>
                                </div>
                            </div>
                            <hr>
                        </c:forEach>
                       

                        <div class="row" style="margin-top: 30px">                             
                            <div class="col-md-12" align="right">                                
                                <button id="generarReporte" form="FormCrearPlan"  class="btn btn-primary btn-sm" style="font-size:14px">
                                    <i class="fa fa-file"></i> Generar reporte
                                </button>     
                                <a title="Regresar" id="btnregresar" href="/creditosextraescolares/app/planesdetrabajo/ListarPlan.do" class="btn btn-sm btn-danger" style="font-size:14px"><i class="fa fa-reply"></i>Regresar</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        
        <!-- JS de CDN -->

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
        
       
        <!--JS propios -->

        <script src="../../js/jsgenerados/planCrearPlan.js" type="text/javascript"></script>        
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.min.js" type="text/javascript"></script>
        <script src="../../js/lib/jspdf.plugin.autotable.min.js" type="text/javascript"></script>
        
        
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
