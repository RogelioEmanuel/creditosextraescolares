$(document).ready(function () {
        // Manejar el evento de cambio en los menús desplegables
        $("select").change(function () {
            // Obtener el valor seleccionado
            var valorSeleccionado = $(this).val();

            // Obtener el ID de la fila
            var idFila = $(this).closest("tr").attr("id");

            // Mostrar en la consola (puedes hacer algo más con estos valores)
            console.log("ID de la fila:", idFila);
            console.log("Valor seleccionado:", valorSeleccionado);
        });
});