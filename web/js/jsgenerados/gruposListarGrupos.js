$("#btnEditar").hide();
$("#btnEliminar").hide();
$("#grpBtnGuardar").hide();
$("#btnCancelar").hide();

let SeEstaModificando = false;
let id_fila = "";

var tblListaGrupos = $('#tblListaGrupos').DataTable({
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


$("#divGrpGrupos").on('click', '#tblListaGrupos tbody tr td', function () {
    if (!SeEstaModificando) {
        DatosDeLaFila = tblListaGrupos.row($(this)).data();
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
        selectLib("tblListaGrupos", $(this).parent().attr('id'));
    }

});

filtradoColumna(tblListaGrupos, "tblListaGrupos");
crearTabla("tblListaGrupos", updateRegistro, updateRegistro, updateRegistro, updateRegistro);
$("#tblListaGrupos_filter").hide();

function updateRegistro() {

}

//Funciones Eliminacion

$("#divGrpGrupos").on("click", "#btnEliminar", function (evento) {
    let id = id_fila;
    mensajeConfirmacion(iconoInfo,id);
});

function mensajeConfirmacion(icono, id) {
    bootbox.confirm({
        title: icono+" <b>¿Estás Seguro?</b>",
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-12>' +
                '<h6 class=panel-title>' + "Se eliminará el grupo y <b>no</b> podrá ser recuperado" + '</h6>' +
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

$("#divGrpGrupos").on("click", "#btnAgregar", function (evento) {
  
    var idActividad=$("#labelIdActividad").text();
    mostrarAgregarGrupo(idActividad);
    
  
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
                TituloMensaje = "¡Grupo eliminado!";
                Mensaje = "el Grupo se ha eliminado correctamente";
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



$("#divGrpGrupos").on("click", "#btnEditar", function (evento) {
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

function mostrarAgregarGrupo(id) {
    $("#pageLoader").show();
    
        $.ajax({
            url: '../../app/grupos/creargrupo.do',
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
    
    
}


$("[id^='btnDetallar']").on("click", function (evento) {
    // Obtener el ID dinámico desde el ID del botón
    let id = $(this).attr("id").replace("btnDetallar", "");
    
    if(id !== null && id !== undefined){
        mostrarDetalleGrupo(id);
    }else{
        TituloMensaje = "ERROR";
        Mensaje = "Es nulo el ID";
        mostrarMensaje(iconoError,TituloMensaje, Mensaje);
    }
    
    
});

function mostrarDetalleGrupo(id) {
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