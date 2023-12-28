$("#btnEditar").hide();
$("#btnEliminar").hide();
$("#grpBtnGuardar").hide();
$("#btnCancelar").hide();

let SeEstaModificando = false;

var tblListaAlumnos = $('#tblListaAlumnos').DataTable({
    "columnDefs": [
        {
            targets: [9],
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
            
            
        } else {
            $("#btnEditar").hide();
            $("#btnEliminar").hide();            
            $("#btnAgregar").show();
            $("#btnregresar").show();
            $("#btnCancelar").hide();
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