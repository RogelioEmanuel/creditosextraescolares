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




filtradoColumna(tblListaGrupos, "tblListaGrupos");
crearTabla("tblListaGrupos", updateRegistro, updateRegistro, updateRegistro, updateRegistro);
$("#tblListaGrupos_filter").hide();

function updateRegistro() {

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
            url: '../../app/grupos/detalles/grupoalumno.do',
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