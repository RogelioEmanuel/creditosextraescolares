$("#FormCrearPlan").on("click", "#btnRegistrar", function (evento) {
    evento.preventDefault();
    //var maestro = $("#maestro").val();
    //var actividades = $("#actividades").val();
    var id = $("#idPlan").text();
    alert(id);
    enviarDatosPlan(id);
    //mensajeConfirmacion(iconoInfo);

});

function enviarDatosPlan(id) {
    //Creamos un indicador de carga
    $("#pageLoader").show();
    
            // Semana 1
    var programasemana1 = $("#programasemana1").val();
    var plataformasemana1 = $("#plataformasemana1").val();
    var descripcionsemana1 = $("#descripcionsemana1").val();

    // Semana 2
    var programasemana2 = $("#programasemana2").val();
    var plataformasemana2 = $("#plataformasemana2").val();
    var descripcionsemana2 = $("#descripcionsemana2").val();

    // Semana 3
    var programasemana3 = $("#programasemana3").val();
    var plataformasemana3 = $("#plataformasemana3").val();
    var descripcionsemana3 = $("#descripcionsemana3").val();

    // Semana 4
    var programasemana4 = $("#programasemana4").val();
    var plataformasemana4 = $("#plataformasemana4").val();
    var descripcionsemana4 = $("#descripcionsemana4").val();

    // Semana 5
    var programasemana5 = $("#programasemana5").val();
    var plataformasemana5 = $("#plataformasemana5").val();
    var descripcionsemana5 = $("#descripcionsemana5").val();

    // Semana 6
    var programasemana6 = $("#programasemana6").val();
    var plataformasemana6 = $("#plataformasemana6").val();
    var descripcionsemana6 = $("#descripcionsemana6").val();

    // Semana 7
    var programasemana7 = $("#programasemana7").val();
    var plataformasemana7 = $("#plataformasemana7").val();
    var descripcionsemana7 = $("#descripcionsemana7").val();

    // Semana 8
    var programasemana8 = $("#programasemana8").val();
    var plataformasemana8 = $("#plataformasemana8").val();
    var descripcionsemana8 = $("#descripcionsemana8").val();

    // Semana 9
    var programasemana9 = $("#programasemana9").val();
    var plataformasemana9 = $("#plataformasemana9").val();
    var descripcionsemana9 = $("#descripcionsemana9").val();

    // Semana 10
    var programasemana10 = $("#programasemana10").val();
    var plataformasemana10 = $("#plataformasemana10").val();
    var descripcionsemana10 = $("#descripcionsemana10").val();

    // Semana 11
    var programasemana11 = $("#programasemana11").val();
    var plataformasemana11 = $("#plataformasemana11").val();
    var descripcionsemana11 = $("#descripcionsemana11").val();

    // Semana 12
    var programasemana12 = $("#programasemana12").val();
    var plataformasemana12 = $("#plataformasemana12").val();
    var descripcionsemana12 = $("#descripcionsemana12").val();

    // Semana 13
    var programasemana13 = $("#programasemana13").val();
    var plataformasemana13 = $("#plataformasemana13").val();
    var descripcionsemana13 = $("#descripcionsemana13").val();

    // Semana 14
    var programasemana14 = $("#programasemana14").val();
    var plataformasemana14 = $("#plataformasemana14").val();
    var descripcionsemana14 = $("#descripcionsemana14").val();

    // Semana 15
    var programasemana15 = $("#programasemana15").val();
    var plataformasemana15 = $("#plataformasemana15").val();
    var descripcionsemana15 = $("#descripcionsemana15").val();

    // Semana 16
    var programasemana16 = $("#programasemana16").val();
    var plataformasemana16 = $("#plataformasemana16").val();
    var descripcionsemana16 = $("#descripcionsemana16").val();

    
    
    
    var datos={
        //maestro: maestro,
        //actividades: actividades,
        idPlan: id,    
        programasemana1: programasemana1,
        plataformasemana1:plataformasemana1,
        descripcionsemana1:descripcionsemana1,
        programasemana2: programasemana2,
        plataformasemana2: plataformasemana2,
        descripcionsemana2: descripcionsemana2,
        programasemana3: programasemana3,
        plataformasemana3: plataformasemana3,
        descripcionsemana3: descripcionsemana3,
        programasemana4: programasemana4,
        plataformasemana4: plataformasemana4,
        descripcionsemana4: descripcionsemana4,
        programasemana5: programasemana5,
        plataformasemana5: plataformasemana5,
        descripcionsemana5: descripcionsemana5,
        programasemana6: programasemana6,
        plataformasemana6: plataformasemana6,
        descripcionsemana6: descripcionsemana6,
        programasemana7: programasemana7,
        plataformasemana7: plataformasemana7,
        descripcionsemana7: descripcionsemana7,
        programasemana8: programasemana8,
        plataformasemana8: plataformasemana8,
        descripcionsemana8: descripcionsemana8,
        programasemana9: programasemana9,
        plataformasemana9: plataformasemana9,
        descripcionsemana9: descripcionsemana9,
        programasemana10: programasemana10,
        plataformasemana10: plataformasemana10,
        descripcionsemana10: descripcionsemana10,
        programasemana11: programasemana11,
        plataformasemana11: plataformasemana11,
        descripcionsemana11: descripcionsemana11,
        programasemana12: programasemana12,
        plataformasemana12: plataformasemana12,
        descripcionsemana12: descripcionsemana12,
        programasemana13: programasemana13,
        plataformasemana13: plataformasemana13,
        descripcionsemana13: descripcionsemana13,
        programasemana14: programasemana14,
        plataformasemana14: plataformasemana14,
        descripcionsemana14: descripcionsemana14,
        programasemana15: programasemana15,
        plataformasemana15: plataformasemana15,
        descripcionsemana15: descripcionsemana15,
        programasemana16: programasemana16,
        plataformasemana16: plataformasemana16,
        descripcionsemana16: descripcionsemana16
    };
    
    //mandamos el formdata al servidor con un post
    $.ajax({
        url: '../../app/planes/editarplan.do',
        type: 'POST',
        dataType: 'json',
        data: datos,
        success: function (respuesta) {
            //ocultamos el indicador de carga
            $("#pageLoader").hide();
            
            TituloMensaje="Plan Modificado con Exito";
            Mensaje="Plan Modificado con Exito";
            var ruta  = "/creditosextraescolares/app/actividadextraescolar/listaractividad.do";
            mensajeRedirect(iconoCorrecto,TituloMensaje,Mensaje, ruta );
            //window.location = ruta;
            
            
        },
        error: function (jqXHR, exception) {
            $("#pageLoader").hide();
            TituloMensaje = "ERROR";
            Mensaje = "Ocurrió un error en el servidor";
            mostrarMensaje(iconoError, TituloMensaje, Mensaje);
            
        }
    });
}


;  
