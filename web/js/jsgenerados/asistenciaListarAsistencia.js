$("#btnEditar").hide();
$("#btnEliminar").hide();
$("#grpBtnGuardar").hide();
$("#btnCancelar").hide();

let SeEstaModificando = false;

var tblListaAsistencias = $('#tblListaAsistencias').DataTable({
    "columnDefs": [
        {
            targets: [2],
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

filtradoColumna(tblListaAsistencias, "tblListaAsistencias");
crearTabla("tblListaAsistencias", updateRegistro, updateRegistro, updateRegistro, updateRegistro);
$("#tblListaAsistencias_filter").hide();

$("#divGrpAsistencias").on('click', '#tblListaAsistencias tbody tr td', function () {
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



function eliminar_registro() {
    bootbox.alert("*Se elimina el registro");
    $("#BTN-Eliminar").hide();
    $("#BTN-Agregar").show();
    SeEstaModificando = false;
    $("#CerrarConfirmEliminacion").trigger("click");
}

function updateRegistro() {

}


