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
