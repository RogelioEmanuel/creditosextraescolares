


function mostrarEditarMaestro(id) {
    $("#pageLoader").show();
    
    
    if(id!==null){
        $.ajax({
            url: '../../app/maestros/editarinfo.do',
            type: 'GET',
            dataType: 'html',
            data: {idMaestro:id},
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

$("#FormEditarMaestro").on("click", "#btnEditar", function (evento) {
    evento.preventDefault();
    var id=$("#idMaestro").val();
    
    if(id !== null && id !== undefined){
        mostrarEditarMaestro(id);
    }else{
        TituloMensaje = "ERROR";
        Mensaje = "Es nulo el ID";
        mostrarMensaje(iconoError,TituloMensaje, Mensaje);
    }
    
});

