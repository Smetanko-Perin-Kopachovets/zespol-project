$(document).ready(function () {

    function showToast() {
        var toast = document.getElementById("snackbar");
        toast.className = "show";
        setTimeout(function(){ toast.className = toast.className.replace("show", ""); }, 3000);
    }

});