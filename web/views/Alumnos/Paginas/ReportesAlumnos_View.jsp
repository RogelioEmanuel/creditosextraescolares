
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Reportes por grupo </title>       
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <body>
        <header><%@include file="../../templates/Header_View.jsp" %></header>
        <div id="pageLoader">
            <div id="pageSpinner">
                <%@include file="../../templates/spinner.estandarITT.jsp" %>
            </div>
        </div>
        <div class="container">
            <div id="PanelForm" class="panel panel-primary" style="margin-top: 2em">
                <label id="labelIdGrupo" style="display: none;">${idGrupo}</label>
                <div class="panel-heading">
                    <h2 class="panel-title" id="HEADINGDP">
                        Reportes por grupo       
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">

                    
                        
                        <div class="row" style="margin-top: 30px">
                            <div class="col-md-12" align="center">
                                                                 
                                <button style="font-size:14px" id="generarReporte1" class="btn btn-primary btn-sm">
                                    <i class="fa fa-file"></i> Generar Reporte Lista de Alumnos Inscritos al grupo
                                </button>
                                <button id="generarReporte"   class="btn btn-primary btn-sm" style="font-size:14px">
                                    <i class="fa fa-file"></i> Generar Reporte Alumnos Inscritos por carrera
                                </button>    
                                <button id="generarReporte2"   class="btn btn-primary btn-sm" style="font-size:14px">
                                    <i class="fa fa-file"></i> Generar Reporte Final
                                </button> 

                            </div>
                        </div>
                    
                </div>
                                          
                <hr>                           
                <div style="margin-top: 30px" id="divReporte">
                    <iframe width="100%" height="800px" src="" id="ApartadoPDF"></iframe>
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
        <script src="../../js/jsgenerados/alumnosReportes.js" type="text/javascript"></script>
        
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
