$("#btnEditar").hide();
$("#btnEliminar").hide();
$("#grpBtnGuardar").hide();
$("#btnCancelar").hide();

let SeEstaModificando = false;

var tblListaAlumnos = $('#tblListaAlumnos').DataTable({
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

filtradoColumna(tblListaAlumnos, "tblListaAlumnos");
crearTabla("tblListaAlumnos", updateRegistro, updateRegistro, eliminar_registro, updateRegistro);
$("#tblListaAlumnos_filter").hide();

$("#divGrpAlumnos").on('click', '#tblListaAlumnos tbody tr td', function () {
    if (!SeEstaModificando) {
        DatosDeLaFila = tblListaAlumnos.row($(this)).data();
        id_fila = $(this).closest('tr').attr("id");
        if (!$(this).parent('tr').hasClass("selected")) {
            $("#btnEditar").show();
            $("#btnEliminar").show();            
            $("#btnAgregar").hide();
            $("#btnregresar").hide();
            $("#btnCancelar").show();
            $("#btnAsistencias").hide();
            $("#btnReporte").hide();
            
        } else {
            $("#btnEditar").hide();
            $("#btnEliminar").hide();            
            $("#btnAgregar").show();
            $("#btnregresar").show();
            $("#btnCancelar").hide();
            $("#btnAsistencias").show();
            $("#btnReporte").show();
        }
        selectLib("tblListaAlumnos", $(this).parent().attr('id'));
    }

});

$("#divGrpAlumnos").on("click", "#btnEliminar", function (evento) {
    let id = id_fila;
    mensajeConfirmacion(iconoInfo,id);
});

function eliminar_registro() {
    bootbox.alert("*Se elimina el registro");
    $("#BTN-Eliminar").hide();
    $("#BTN-Agregar").show();
    SeEstaModificando = false;
    $("#CerrarConfirmEliminacion").trigger("click");
}

function updateRegistro() {

}

//Inscripcion (Agregar)

function mostrarInscribirAlumno(id) {
    $("#pageLoader").show();
    
    
    if(id!==null){
        $.ajax({
            url: '../../app/gruposalumno/inscribiralumnogrupo.do',
            type: 'GET',
            dataType: 'html',
            data: {idGrupo:id                                
            },
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

$("#divGrpAlumnos").on("click", "#btnAgregar", function (evento) {
    var id = $("#labelIdGrupo").text();
    //var noControl = $("#noControl").val();
    //var noReinscripcion = $("#noReinscripcion").val();
    //var id = $("#labelIdGrupo").val();
    
    mostrarInscribirAlumno(id);
    
});


//Dar de baja (Eliminar)

$("#divGrpAlumnos").on("click", "#btnEliminar", function (evento) {
    let id = id_fila;
    var idGrupo= $("#labelIdGrupo").text();
    mensajeConfirmacion(iconoInfo,id,idGrupo);
});

function mensajeConfirmacion(icono, id,idGrupo) {
    bootbox.confirm({
        title: icono+" <b>¿Estás Seguro?</b>",
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-12>' +
                '<h6 class=panel-title>' + "Se dará  de baja el Alumno" + '</h6>' +
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
                eliminarActividad(id,idGrupo);
            } else {
                
            }
        }
    });
};

function eliminarActividad(id,idGrupo) {
    $("#pageLoader").show();
    $.ajax({
        url: '../../app/gruposalumno/eliminaralumno.do',
        type: 'POST',
        dataType: 'json',
        data: {noControl: id,
        idGrupo:idGrupo},
        success: function (respuesta) {
            $("#pageLoader").hide();
            if (respuesta.status === 0) {
                TituloMensaje = "¡Alumno dado de baja!";
                Mensaje = "El alumno se ha dado de baja correctamente";
                mensajeRedirect(iconoCorrecto, TituloMensaje, Mensaje, '../../app/gruposalumno/listargruposalumno.do?idGrupo='+ idGrupo);
                
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

//Selectivo (Editar)


$("#divGrpAlumnos").on("click", "#btnEditar", function (evento) {
    
    let id = id_fila;
    var idGrupo = $("#labelIdGrupo").text();
    if(id !== null && id !== undefined){
        mostrarEditarEstado(id,idGrupo);
    }else{
        TituloMensaje = "ERROR";
        Mensaje = "Es nulo el ID";
        mostrarMensaje(iconoError,TituloMensaje, Mensaje);
    }
    
});

function mostrarEditarEstado(id,idGrupo) {
    $("#pageLoader").show();
    
    
    if(id!==null){
        $.ajax({
            url: '../../app/gruposalumno/editaralumnogrupo.do',
            type: 'GET',
            dataType: 'html',
            data: {noControl:id,
                idGrupo:idGrupo
            },
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


//Vista Reportes


$("#divGrpAlumnos").on("click", "#btnReporte", function (evento) {
    
    //let id = id_fila;
    var idGrupo = $("#labelIdGrupo").text();
    //if(id !== null && id !== undefined){
    mostrarCrearReportes(idGrupo);
    //}else{
      //  TituloMensaje = "ERROR";
       // Mensaje = "Es nulo el ID";
       // mostrarMensaje(iconoError,TituloMensaje, Mensaje);
   // }
    
});

function mostrarCrearReportes(idGrupo) {
    $("#pageLoader").show();
    
    
    if(idGrupo!==null){
        $.ajax({
            url: '../../app/alumnos/reportesalumnoinscrito.do',
            type: 'GET',
            dataType: 'html',
            data: {
                idGrupo:idGrupo
            },
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

//Lita asistencias

$("#divGrpAlumnos").on("click", "#btnAsistencias", function (evento) {
    
    //let id = id_fila;
    var idGrupo = $("#labelIdGrupo").text();
    //if(id !== null && id !== undefined){
    mostrarLista(idGrupo);
    //}else{
      //  TituloMensaje = "ERROR";
       // Mensaje = "Es nulo el ID";
       // mostrarMensaje(iconoError,TituloMensaje, Mensaje);
   // }
    
});

function mostrarLista(idGrupo) {
    $("#pageLoader").show();
    
    
    if(idGrupo!==null){
        $.ajax({
            url: '../../app/asistencias/listarasistencias.do',
            type: 'GET',
            dataType: 'html',
            data: {
                idGrupo:idGrupo
            },
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