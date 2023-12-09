$(document).ready(function () {
    $("#pageLoader").hide();
    
    $('.datepicker').datepicker({
        language: "es",
        daysOfWeekHighlighted: "0,6", //Aquí se está indicando que los días 0 (domingo) y 6 (sábado) tengan un sombreado en el calendario
        calendarWeeks: true //Con esta opción se indica que el calendario deberá mostrar el número de semana (de todo el año) de los días mostrados.
    });

});


