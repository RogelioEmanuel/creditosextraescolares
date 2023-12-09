$("#btnEditar").hide();
$("#btnEliminar").hide();
$("#grpBtnGuardar").hide();
$("#btnCancelar").hide();

let SeEstaModificando = false;
let id_fila = "";
var tblListaActividadesExtraescolares = $('#tblListaActividadesExtraescolares').DataTable({
    "columnDefs": [
        {
            targets: [4],
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


filtradoColumna(tblListaActividadesExtraescolares, "tblListaActividadesExtraescolares");
crearTabla("tblListaActividadesExtraescolares", updateRegistro, updateRegistro, eliminar_registro, updateRegistro);
$("#tblListaActividadesExtraescolares_filter").hide();

$("#divGrpActividades").on('click', '#tblListaActividadesExtraescolares tbody tr td', function () {
    if (!SeEstaModificando) {
        DatosDeLaFila = tblListaActividadesExtraescolares.row($(this)).data();
        id_fila = $(this).closest('tr').attr("id");
        if (!$(this).parent('tr').hasClass("selected")) {
            $("#btnEditar").show();
            $("#btnEliminar").show();            
            $("#btnAgregar").hide();
            $("#btnregresar").hide();
            $("#btnCancelar").show();
            
            
        } else {
            $("#btnEditar").hide();
            $("#btnEliminar").hide();            
            $("#btnAgregar").show();
            $("#btnregresar").show();
            $("#btnCancelar").hide();
        }
        selectLib("tblListaActividadesExtraescolares", $(this).parent().attr('id'));
    }

});








function eliminar_registro() {
    bootbox.alert("*Se elimina el registro");
    $("#BTN-Eliminar").hide();
    $("#BTN-Agregar").show();
    SeEstaModificando = false;
    $("#CerrarConfirmEliminacion").trigger("click");
}

function mostrarEditarCredito(id) {
    $("#pageLoader").show();
    
    
    if(id!==null){
        $.ajax({
            url: '../../app/actividadextraescolar/editaractividad.do',
            type: 'GET',
            dataType: 'html',
            data: {idActividad:id},
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

function mostrarCredito(id) {
    $("#pageLoader").show();
        
    if(id!==null){
        $.ajax({
            url: '../../app/grupos/listargrupos.do',
            type: 'GET',
            dataType: 'html',
            data: {idActividad:id},
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


$("#divGrpCreditos").on("click", "#btnEditar", function (evento) {
    $("#grpBtnGuardar").hide();
    $("#grpBtnAgregar").show();
    let id = id_fila;
    
    if(id !== null && id !== undefined){
        mostrarEditarCredito(id);
    }else{
        TituloMensaje = "ERROR";
        Mensaje = "Es nulo el ID";
        mostrarMensaje(iconoError,TituloMensaje, Mensaje);
    }
    
});


function mensajeConfirmacion(icono, id) {
    bootbox.confirm({
        title: icono+" <b>¿Estás Seguro?</b>",
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-12>' +
                '<h6 class=panel-title>' + "Se eliminará la actividad y <b>no</b> podrá ser recuperada" + '</h6>' +
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
                eliminarCredito(id);
            } else {
                
            }
        }
    });
};

function eliminarCredito(id) {
    $("#pageLoader").show();
    $.ajax({
        url: '../../app/actividadextraescolar/eliminaractividad.do',
        type: 'POST',
        dataType: 'json',
        data: {idActividad: id},
        success: function (respuesta) {
            $("#pageLoader").hide();
            if (respuesta.status === 0) {
                TituloMensaje = "¡Actividad eliminada!";
                Mensaje = "La actividad se ha eliminado correctamente";
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

function updateRegistro() {

}
