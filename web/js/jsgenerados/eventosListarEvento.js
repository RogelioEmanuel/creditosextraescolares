$("#btnEditar").hide();
$("#btnEliminar").hide();
$("#grpBtnGuardar").hide();
$("#btnCancelar").hide();

let SeEstaModificando = false;
let id_fila = "";

var tblListaEventos = $('#tblListaEventos').DataTable({
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


$("#divGrpEventos").on('click', '#tblListaEventos tbody tr td', function () {
    if (!SeEstaModificando) {
        DatosDeLaFila = tblListaEventos.row($(this)).data();
        id_fila = $(this).closest('tr').attr("id");
        if (!$(this).parent('tr').hasClass("selected")) {
            $("#btnEditar").show();
            $("#btnEliminar").show();            
            $("#btnAgregar").hide();
            $("#btnRegresar").hide();
            $("#btnCancelar").show();
            
            
        } else {
            $("#btnEditar").hide();
            $("#btnEliminar").hide();            
            $("#btnAgregar").show();
            $("#btnRegresar").show();
            $("#btnCancelar").hide();
        }
        selectLib("tblListaEventos", $(this).parent().attr('id'));
    }

});

filtradoColumna(tblListaEventos, "tblListaEventos");
crearTabla("tblListaEventos", updateRegistro, updateRegistro, updateRegistro, updateRegistro);
$("#tblListaEventos_filter").hide();

function updateRegistro() {

}

//Funciones Eliminacion

$("#divGrpEventos").on("click", "#btnEliminar", function (evento) {
    let id = id_fila;
    mensajeConfirmacion(iconoInfo,id);
});

function mensajeConfirmacion(icono, id) {
    bootbox.confirm({
        title: icono+" <b>¿Estás Seguro?</b>",
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-12>' +
                '<h6 class=panel-title>' + "Se eliminará el Evento y <b>NO</b> podrá ser recuperado" + '</h6>' +
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
                eliminarGrupo(id);
            } else {
                
            }
        }
    });
};

$("#divGrpEventos").on("click", "#btnAgregar", function (evento) {
  
    
    mostrarAgregarGrupo();
    
  
});

function eliminarGrupo(id) {
    $("#pageLoader").show();
    $.ajax({
        url: '../../app/grupos/eliminargrupo.do',
        type: 'POST',
        dataType: 'json',
        data: {idGrupo: id},
        success: function (respuesta) {
            $("#pageLoader").hide();
            if (respuesta.status === 0) {
                TituloMensaje = "¡Evento eliminado!";
                Mensaje = "El Eventos se ha eliminado correctamente";
                mensajeRedirect(iconoCorrecto, TituloMensaje, Mensaje, '../../app/actividadextraescolar/listaractividad.do');
                
            } else {
                TituloMensaje = "ERROR: ";
                Mensaje = respuesta.mensaje;
                mostrarMensaje(iconoError,TituloMensaje, Mensaje);
            }

        },
        error: function (jqXHR, exception) {
            $("#pageLoader").hide();
            TituloMensaje = "ERROR";
            Mensaje = "Ocurrió un error en el servidor";
            mostrarMensaje(iconoError,TituloMensaje, Mensaje);
        }

    });
}


// Edicion funciones



$("#divGrpEventos").on("click", "#btnEditar", function (evento) {
    $("#grpBtnGuardar").hide();
    $("#grpBtnAgregar").show();
    let id = id_fila;
    
    if(id !== null && id !== undefined){
        mostrarEditarGrupo(id);
    }else{
        TituloMensaje = "ERROR";
        Mensaje = "Es nulo el ID";
        mostrarMensaje(iconoError,TituloMensaje, Mensaje);
    }
    
});

function mostrarEditarGrupo(id) {
    $("#pageLoader").show();
    
    
    if(id!==null){
        $.ajax({
            url: '../../app/grupos/editargrupo.do',
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

function mostrarAgregarGrupo() {
    $("#pageLoader").show();
    
        $.ajax({
            url: '../../app/grupos/creargrupo.do',
            type: 'GET',
            dataType: 'html',
            data: {},
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
    
    
}


$("[id^='btnDetallar']").on("click", function (evento) {
    // Obtener el ID dinámico desde el ID del botón
    let id = $(this).attr("id").replace("btnDetallar", "");
    
    if(id !== null && id !== undefined){
        mostrarDetalleEvento(id);
    }else{
        TituloMensaje = "ERROR";
        Mensaje = "Es nulo el ID";
        mostrarMensaje(iconoError,TituloMensaje, Mensaje);
    }
    
    
});

function mostrarDetalleEvento(id) {
    $("#pageLoader").show();
        
    if(id!==null){
        $.ajax({
            url: '../../app/grupos/detallargrupo.do',
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

