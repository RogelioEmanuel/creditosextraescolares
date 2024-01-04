
$("#FormInscribirAlumno").on("click", "#btnRegistrar", function (evento) {
    
    evento.preventDefault();
    var noControl = $("#nControl").val();
    var idGrupo =$("#labelIdGrupo").text();
    var nReinscripcion = $("#nReinscripcion").val();
    var selectivo = $("#selectivo").val();
    
    editarDatosActividad(noControl,idGrupo,nReinscripcion,selectivo);
            
});

$("#FormEditarActividad").on("click", "#btnRegresar", function (evento) {
   evento.preventDefault();
    window.location.reload();
});

function editarDatosActividad(noControl,idGrupo,nReinscripcion,selectivo) {
    $("#pageLoader").show();
    
    
    
    $.ajax({
        url: '../../app/gruposalumno/editaralumnogrupo.do',
        type: 'POST',
        dataType: 'json',
        data: {idActividad: $("#idActividad").val(),
            noControl: noControl,
            idGrupo: idGrupo,
            nReinscripcion: nReinscripcion,            
            selectivo: selectivo,
            },
        success: function (respuesta) {
            $("#pageLoader").hide();
            
            TituloMensaje = "Alumno modificado";
            Mensaje = "El alumno modificado";
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