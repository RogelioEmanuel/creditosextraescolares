

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Confirmar Invitacion</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet">
        <link href="/CDN-ITT/css/spinner.estandarITT.css" rel="stylesheet">
    </head>
    <body>
        <header><%@include file="../templates/HeaderInvitado_View.jsp" %></header>
        <div class="container">
            <div id="pageLoader">
                <div id="pageSpinner">
                    <%@include file="../templates/spinner.estandarITT.jsp" %>
                </div>
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h2 class="panel-title">
                        Datos del evento
                    </h2>
                </div>
                <div class="panel-body">
                    
                </div>    
            </div>

        </div>
        <footer><%@include file="../templates/Footer_View.jsp"%></footer>

        <script type="text/javascript" src ="\CDN-ITT\js\jquery.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\jquery-ui.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\base.estandarITT.js"></script>
        <script type="text/javascript" src ="\CDN-ITT\js\spinner.estandarITT.js"></script>
        <script src="../../js/jsgenerados/Inicio.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script> 
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/eventosContenidoVacio_View.js" type="text/javascript"></script>
    </body>
</html>
