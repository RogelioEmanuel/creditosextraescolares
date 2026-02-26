$("#FormCrearGrupo").on("click", "#btnPlanes", function (evento) {
   evento.preventDefault();
   var idActividad = $("#idActividad").val();
   mostrarPlandeTrabajo(idActividad);
});


$("#FormCrearGrupo").on("click", "#btnAlumnos", function (evento) {
    evento.preventDefault()
    var id = $("#labelIdGrupo").text();
    
    if(id !== null && id !== undefined){
        mostrarAlumnosGrupo(id);
    }else{
        TituloMensaje = "ERROR";
        Mensaje = "Es nulo el ID";
        mostrarMensaje(iconoError,TituloMensaje, Mensaje);
    }
    
});

function mostrarAlumnosGrupo(id) {
    $("#pageLoader").show();
    
    
    if(id!==null){
        $.ajax({
            url: '../../app/gruposalumno/listargruposalumno.do',
            type: 'GET',
            dataType: 'html',
            data: {idGrupo:id},
            success: function (respuesta) {
                $("body").html(respuesta);
            },
            error: function (jqXHR, exception) {
                $("#pageLoader").hide();
                TituloMensaje = "ERROR";
                Mensaje = "Ocurrió un error en el servidor";
                mostrarMensaje(iconoError,TituloMensaje, Mensaje);
            }
        });
    }else{
        TituloMensaje = "ERROR";
        Mensaje = "Es nulo el ID";
        mostrarMensaje(iconoError,TituloMensaje, Mensaje);
    }
    
}

