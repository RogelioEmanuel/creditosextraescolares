$("#btnEditar").hide();
$("#btnEliminar").hide();
$("#grpBtnGuardar").hide();
$("#btnCancelar").hide();

let SeEstaModificando = false;
let id_fila = "";

var tblListaGrupos = $('#tblListaGrupos').DataTable({
    "columnDefs": [
        {
            targets: [8],
            orderable: false
        }
    ],
    "language": {
        "search": "Buscar:",
        "lengthMenu": "Mostrando _MENU_ registros por página",
        "zeroRecords": "No se ha encontrado ningún registro.",
        "info": "Mostrando página _PAGE_ de _PAGES_",

        emptyTable: "No se ha encontrado ningún registro.",
        "infoEmpty": "Sin registros",
        "oPaginate": {
            "sNext": "Siguiente",
            "sPrevious": "Anterior"
        }
    }
});

filtradoColumna(tblListaGrupos, "tblListaGrupos");
crearTabla("tblListaGrupos", updateRegistro, updateRegistro, updateRegistro, updateRegistro);
$("#tblListaGrupos_filter").hide();

function updateRegistro() {

}





function mensajeConfirmacion(icono, id) {
    bootbox.confirm({
        title: icono+" <b>¿Estás seguro que desea continuar con la inscripción?</b>",
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-12>' +
                '<h6 class=panel-title>' + "Para cambios o baja de actividad pasar con el jefe del departamento" + '</h6>' +
                '</div>' +
                '</div>',
        buttons: {
            confirm: {
                label: "Continuar",
                className: "btn-success"
            },
            cancel: {
                label: "Cancelar",
                className: "btn-danger"
            }
        },
        callback: function (result) {
            if (result) {
               var nReinscripcion = prompt("Ingresa el número de reinscripcion (1 si es la primera vez que te inscribes)");
               if(nReinscripcion!== null){
                   
                   if(!isNaN(nReinscripcion)&&nReincripcion>0){
                       inscribirGrupo(id, nReinscripcion);
                   }else{
                       alert('No ingresaste un número válido.');
                   }
                   
               }else{
                   alert('No ingresaste el número de reinscripcion');
               }
                
            } else {
                
            }
        }
    });
};


$("[id^='btnInscripcion']").on("click", function (evento) {
    // Obtener el ID dinámico desde el ID del botón
    let id = $(this).attr("id").replace("btnInscripcion", "");
    
    if(id !== null && id !== undefined){
        mensajeConfirmacion(iconoInfo,id);
    }else{
        TituloMensaje = "ERROR";
        Mensaje = "Es nulo el ID";
        mostrarMensaje(iconoError,TituloMensaje, Mensaje);
    }
    
    
});

function inscribirGrupo(id,nReinscripcion) {
    $("#pageLoader").show();
        
    if(id!==null){
        $.ajax({
            url: '../../app/grupos/inscripciongrupo.do',
            type: 'POST',
            dataType: 'html',
            data: {idGrupo:id,
            nReinscripcion:nReinscripcion
                },
            success: function (respuesta) {
                var respuestaObj = JSON.parse(respuesta);
                
                var a = respuestaObj.mensaje;
                var b= respuestaObj.status;
                if(b===0){
                    $("body").html(respuesta);
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