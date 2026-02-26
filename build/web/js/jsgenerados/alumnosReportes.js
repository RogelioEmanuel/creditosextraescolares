$(document).ready(function () {
    $("#divReporte").hide();
});


$("#generarReporte").click(function () {
    var idGrupo= $("#labelIdGrupo").text();
    var a=2
    generarReporte(a,idGrupo);
});

$("#generarReporte1").click(function () {
    var idGrupo= $("#labelIdGrupo").text();
    var a=1;
    generarReporte(a,idGrupo);
});

$("#generarReporte2").click(function () {
    var idGrupo= $("#labelIdGrupo").text();
    var a=3;
    generarReporte(a,idGrupo);
});

function generarReporte(a,idGrupo) {
    $("#pageLoader").show();
    $.ajax({
        url: '../../app/alumnos/reportesalumnoinscrito.do',
        type: 'POST',
        dataType: 'json',
        data: {numero:a,
        idGrupo:idGrupo
        },
        success: function (respuesta) {
            $("#pageLoader").hide();
            if (respuesta.status === 0) {
                var byteCharacters = atob(respuesta.mensaje);
                var byteNumbers = new Array(byteCharacters.length);
                for (var i = 0; i < byteCharacters.length; i++) {
                    byteNumbers[i] = byteCharacters.charCodeAt(i);
                }
                var byteArray = new Uint8Array(byteNumbers);
                var blob = new Blob([byteArray], {type: 'application/pdf'});

                var url = URL.createObjectURL(blob);
                $("#ApartadoPDF").attr("src", url);
                $("#divReporte").show();
            }else{
                
                TituloMensaje = "ERROR";
                Mensaje = "No hay datos para este reporte";
                mostrarMensaje(iconoError, TituloMensaje, Mensaje);
            }
        },
        error: function (jqXHR, exception) {
            $("#pageLoader").hide();
            TituloMensaje = "ERROR";
            Mensaje = "Ocurrió un error en el servidor";
            mostrarMensaje(iconoError, TituloMensaje, Mensaje);
        }
    });
}

