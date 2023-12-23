<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="/CDN-ITT/img/logo.png">
        <title>Crear Plan de Trabajo</title>
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
                        Crear Plan de Trabajo     
                    </h2>
                </div>
                <div class="panel panel-body" color: black; margin: 0">
                    <form id="FormCrearPlan">
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Actividad :</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="inputNombreActividadExtraescolar" 
                                           id="nombre" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="Nombre de la Actividad Extraescolar" 
                                           required 
                                           disabled="true"
                                           minlength="1"
                                           maxlength="80"  
                                           title="Nombre de la Actividad Extraescolar">
                                </div>
                            </div>                            
                            <div class="col-md-6">
                                <label >Maestro</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <select name="inputTipo" 
                                            id="tipo" 
                                            placeholder="Tipo"
                                            class="form-control input-sm"   
                                            disabled="true"
                                            required >
                                        <option value="Civico">Civico</option>
                                        <option value="Deportivo">Deportivo</option>
                                        <option value="Cultural">Cultural</option>
                                    </select>
                                </div>
                            </div>                            
                        </div>
                        
                        <div class="row" style="margin-bottom: 20px">                            
                            <div class="col-md-6">
                                <label >Programa a impartir semana 1</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana1" 
                                           id="programasemana1" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 1" 
                                           required 
                                           minlength="1"
                                           maxlength="80"
                                           disabled="true"
                                           title="programa semana 1">
                                </div>
                            </div>                            
                            <div class="col-md-6">
                                <label >Plataforma a Utilizar</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana1" 
                                           id="plataformasemana1" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 1" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 1">
                                </div>
                            </div>                               
                        </div>                       
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana1" 
                                              id="descripcionsemana1" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo haría?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 2</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana2" 
                                           id="programasemana2" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 2" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 2">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 2</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana2" 
                                           id="plataformasemana2" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 2" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 2">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana2" 
                                              id="descripcionsemana2" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo haría?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>                        
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 3</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana2" 
                                           id="programasemana3" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 3" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 3">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 3</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana3" 
                                           id="plataformasemana3" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 3" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 3">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana3" 
                                              id="descripcionsemana3" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo haría?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">                            
                            <div class="col-md-6">
                                <label >Programa a impartir semana 4</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana4" 
                                           id="programasemana4" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 4" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 4">
                                </div>
                            </div>                            
                            <div class="col-md-6">
                                <label >Plataforma a Utilizar</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana4" 
                                           id="plataformasemana4" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 4" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 4">
                                </div>
                            </div>                               
                        </div>                       
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana4" 
                                              id="descripcionsemana4" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo haría?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 5</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana5" 
                                           id="programasemana5" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 5" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 5">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 5</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana5" 
                                           id="plataformasemana5" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 5" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 5">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana5" 
                                              id="descripcionsemana5" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo haría?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>                       
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 6</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana6" 
                                           id="programasemana6" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 6" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 6">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 6</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana6" 
                                           id="plataformasemana6" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 6" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 6">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana6" 
                                              id="descripcionsemana6" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo haría?"></textarea>
                                </div>
                            </div>
                        </div>                                             
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 7</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana7" 
                                           id="programasemana7" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 7" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 7">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 7</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana7" 
                                           id="plataformasemana7" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 7" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 7">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará semana 7?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana7" 
                                              id="descripcionsemana7" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo harían?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 8</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana8" 
                                           id="programasemana8" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 8" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 8">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 8</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana8" 
                                           id="plataformasemana8" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 8" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 8">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará semana 8?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana8" 
                                              id="descripcionsemana8" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo harían?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 9</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana9" 
                                           id="programasemana9" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 9" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 9">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 9</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana9" 
                                           id="plataformasemana9" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 9" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 9">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará semana 9?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana9" 
                                              id="descripcionsemana9" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo harían?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 10</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana10" 
                                           id="programasemana10" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 10" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 10">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 10</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana10" 
                                           id="plataformasemana10" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 10" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 10">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará semana 10?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana10" 
                                              id="descripcionsemana10" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo harían?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 11</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana11" 
                                           id="programasemana11" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 11" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 11">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 11</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana11" 
                                           id="plataformasemana11" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 11" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 11">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará semana 11?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana11" 
                                              id="descripcionsemana11" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo harían?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 12</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana12" 
                                           id="programasemana12" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 12" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 12">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 12</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana12" 
                                           id="plataformasemana12" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 12" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 12">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará semana 12?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana12" 
                                              id="descripcionsemana12" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo harían?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 13</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana13" 
                                           id="programasemana13" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 13" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 13">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 13</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana13" 
                                           id="plataformasemana13" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 13" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 13">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará semana 13?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana13" 
                                              id="descripcionsemana13" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo harían?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 14</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana14" 
                                           id="programasemana14" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 14" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 14">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 14</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana14" 
                                           id="plataformasemana14" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 14" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 14">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará semana 14?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana14" 
                                              id="descripcionsemana14" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo harían?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 15</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana15" 
                                           id="programasemana15" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 15" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 15">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 15</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana15" 
                                           id="plataformasemana15" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 15" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 15">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará semana 15?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana15" 
                                              id="descripcionsemana15" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo harían?"></textarea>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-6">
                                <label>Programa a impartir semana 16</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="programasemana16" 
                                           id="programasemana16" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="programa semana 16" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="programa semana 16">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Plataforma a Utilizar semana 16</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input name="plataformasemana16" 
                                           id="plataformasemana16" 
                                           class="form-control input-sm" 
                                           autocomplete="off" 
                                           placeholder="plataforma semana 16" 
                                           disabled="true" 
                                           minlength="1"
                                           maxlength="80"  
                                           title="plataforma semana 16">
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom: 20px">
                            <div class="col-md-12">
                                <label>¿Cómo lo hará semana 16?</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                    <textarea style="resize: none" 
                                              name="inputDescripcionsemana16" 
                                              id="descripcionsemana16" 
                                              class="form-control input-sm" 
                                              autocomplete="off" 
                                              placeholder="¿Cómo lo harían?" 
                                              disabled="true" 
                                              minlength="1"
                                              rows="4"
                                              title="¿Cómo lo harían?"></textarea>
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 30px">                             
                            <div class="col-md-12" align="right">                                
                                <button id="generarReporte" form="FormCrearPlan"  class="btn btn-primary btn-sm" style="font-size:14px">
                                    <i class="fa fa-file"></i> Generar reporte
                                </button>     
                                <a title="Regresar" id="btnregresar" href="/creditosextraescolares/app/actividadextraescolar/listaractividad.do" class="btn btn-sm btn-danger" style="font-size:14px"><i class="fa fa-reply"></i></a>
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
