
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Inscribir Alumno</title>
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
    <header><%@include file="../../templates/HeaderMaestro_View.jsp" %></header>
    <body>
       
            
        <div class="container">
            <div id="PanelForm" class="panel panel-primary" style="margin-top: 2em">
                <div class="panel-heading">
                    <label id="labelIdGrupo" style="display: none;">${idGrupo}</label>
                    <h2 class="panel-title" id="HEADINGDP">
                        Inscribir Alumno          
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">
                    <form id="FormInscribirAlumno">
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-5">
                                <label>Numero de Control:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputCupo" 
                                           id="nControl" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="nControl" 
                                           required 
                                           minlength="1"
                                           maxlength="80"                                             
                                           title="nControl">
                                </div>
                            </div>
                            
                            <div class="col-md-5">
                                <label>Numero de Reinscripcion:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                                    <input name="inputoGrupo" 
                                           id="nReinscripcion" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="nReinscripcion" 
                                           required 
                                           minlength="1"
                                           maxlength="80"                                             
                                           title="nReinscripcion">
                                </div>
                            </div>
                           
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            
                             <div class="col-md-5">
                                <label >Selectivo</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <select name="inputMaestro" 
                                            id="selectivo" 
                                            placeholder="selectivo"
                                            class="form-control input-sm"                                             
                                            required >
                                        <option value="Si">Si</option>
                                        <option value="No">No</option>
                                    </select>
                                </div>
                            </div>                                                                                           
                        </div>
                                   
                        <div class="row" style="margin-top: 30px">                             
                            <div class="col-md-12" align="right">                                
                                <button id="btnRegistrar" form="FormInscribirAlumno" type="submit"   class="btn btn-primary btn-sm " style="font-size:14px">
                                    <i class="fa fa-save"></i> Inscribir
                                </button>    
                                 <a title="Regresar" id="btnregresar" href="/creditosextraescolares/app/actividadextraescolar/listaractividad.do" class="btn btn-sm btn-danger" style="font-size:14px"><i class="fa fa-reply"></i>
                                  Regresar</a>
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
        
        <script src="../../js/jsgenerados/alumnosAgregarAlumno.js" type="text/javascript"></script>
       
        <script src="../../js/lib/bootbox.min.js" type="text/javascript"></script>   
        <script src="../../js/jsgenerados/funciones.js" type="text/javascript"></script>
        <script src="../../js/jsgenerados/constantes.js" type="text/javascript"></script>
    </body>
    <footer><%@include file="../../templates/Footer_View.jsp" %></footer>
</html>
