
//Funciones Eliminacion

$("#FormInscribirAlumno").on("click", "#btnRegistrar", function (evento) {
    evento.preventDefault();
    var id = $("#labelIdGrupo").text();
    var nControl = $("#nControl").val();    
    var nReinscripcion = $("#nReinscripcion").val();
    var selectivo = $("#selectivo").val();
   inscribirGrupo(id,nControl,nReinscripcion,selectivo);
});



function inscribirGrupo(id,nControl,nReinscripcion,selectivo) {
    $("#pageLoader").show();
        
    if(id!==null){
        $.ajax({
            url: '../../app/gruposalumno/inscribiralumno.do',
            type: 'POST',
            dataType: 'html',
            data: {idGrupo:id,
            nControl:nControl,
            selectivo:selectivo,
            nReinscripcion:nReinscripcion
            },
            success: function (respuesta) {
                var respuestaObj = JSON.parse(respuesta);
                
                var a = respuestaObj.mensaje;
                var b= respuestaObj.status;
                if(b===0){
                    TituloMensaje = "Inscripcion Exitosa";
                    Mensaje = "Se ha inscrito correctamente al grupo";
                    mensajeRedirect(iconoCorrecto, TituloMensaje, Mensaje, '/creditosextraescolares/app/gruposalumno/listargruposalumno.do?idGrupo='+ id);
                }
                if(b===800){
                    mostrarMensaje(iconoInfo,"Respuesta",a);
                }
               
                
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

