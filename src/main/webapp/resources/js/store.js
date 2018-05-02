
function showToast() {
    console.log("Get method");
    var toast = document.getElementById("snackbar");
    toast.className = "show";
    setTimeout(function () {
        toast.className = toast.className.replace("show", "");
    }, 3000);
}