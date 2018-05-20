$(document).ready(function () {
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15, // Creates a dropdown of 15 years to control year,
        today: 'Today',
        clear: 'Clear',
        close: 'Ok',
        format: 'yyyy-mm-dd',
        closeOnSelect: false // Close upon selecting a date,
    });

    $('.timepicker').pickatime({
        default: 'now', // Set default time: 'now', '1:30AM', '16:30'
        fromnow: 0,       // set default time to * milliseconds from now (using with default = 'now')
        twelvehour: false, // Use AM/PM or 24-hour format
        donetext: 'OK', // text for done-button
        cleartext: 'Clear', // text for clear-button
        canceltext: 'Cancel', // Text for cancel-button
        autoclose: false, // automatic close timepicker
        ampmclickable: true, // make AM PM clickable
        aftershow: function () {
        } //Function for after opening timepicker
    });


});

function filterTable() {

    var input = document.getElementById("filterInput");
    var filter = input.value.toUpperCase();
    var rows = document.querySelector("#jobsTable tbody").rows;

    for (var i = 0; i < rows.length; i++) {
        var col1 = rows[i].cells[0].textContent.toUpperCase();
        var col2 = rows[i].cells[1].textContent.toUpperCase();
        var col3 = rows[i].cells[2].textContent.toUpperCase();
        var col4 = rows[i].cells[3].textContent.toUpperCase();
        var col5 = rows[i].cells[4].textContent.toUpperCase();

        if (col1.indexOf(filter) > -1
            || col2.indexOf(filter) > -1
            || col3.indexOf(filter) > -1
            || col4.indexOf(filter) > -1
            || col5.indexOf(filter) > -1) {

            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}
