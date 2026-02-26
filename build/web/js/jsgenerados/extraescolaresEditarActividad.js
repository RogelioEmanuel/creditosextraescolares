
$("#FormEditarActividad").on("click", "#btnEditarActividad", function (evento) {
    
    evento.preventDefault();
    
    editarDatosActividad();
            
});

$("#FormEditarActividad").on("click", "#btnRegresar", function (evento) {
   evento.preventDefault();
    window.location.reload();
});

function editarDatosActividad() {
    $("#pageLoader").show();
    
    
    
    $.ajax({
        url: '../../app/actividadextraescolar/editaractividad.do',
        type: 'POST',
        dataType: 'json',
        data: {idActividad: $("#idActividad").val(),
            nombre: $("#nombreActividadExtraescolar").val(),
            tipo: $("#tipo").val(),
            status: $("#status").val(),            
            descripcion: $("#descripcion").val(),
            },
        success: function (respuesta) {
            $("#pageLoader").hide();
            
                TituloMensaje = "Actividad modificado";
                Mensaje = "La actividad modificado";
                mensajeRedirect(iconoCorrecto, TituloMensaje, Mensaje, '../../app/actividadextraescolar/listaractividad.do');
            
            
        },
        error: function (jqXHR, exception) {
            $("#pageLoader").hide();
            TituloMensaje = "ERROR";
            Mensaje = "Ocurrió un error en el servidor";
            mostrarMensaje(iconoError,TituloMensaje, Mensaje);
        }
    });
}