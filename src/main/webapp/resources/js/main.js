document.addEventListener('DOMContentLoaded', function () {

    var $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

    if ($navbarBurgers.length > 0) {

        $navbarBurgers.forEach(function ($el) {
            $el.addEventListener('click', function () {

                var target = $el.dataset.target;
                var $target = document.getElementById(target);

                $el.classList.toggle('is-active');
                $target.classList.toggle('is-active');

            });
        });
    }
});

function showToast() {
    var toast = document.getElementById("snackbar");
    toast.className = "show";
    setTimeout(function () {
        toast.className = toast.className.replace("show", "");
    }, 3000);
}

function changeContent1() {
    document.getElementById("content1").style.display = 'block';
    document.getElementById("content2").style.display = 'none';
    document.getElementById("content3").style.display = 'none';
    document.getElementById("changeContent1").classList.add('is-active');
    document.getElementById("changeContent3").classList.remove('is-active');
    document.getElementById("changeContent2").classList.remove('is-active');
}

function changeContent2() {
    document.getElementById("content1").style.display = 'none';
    document.getElementById("content2").style.display = 'block';
    document.getElementById("content3").style.display = 'none';
    document.getElementById("changeContent2").classList.add('is-active');
    document.getElementById("changeContent1").classList.remove('is-active');
    document.getElementById("changeContent3").classList.remove('is-active');
}

function changeContent3() {
    document.getElementById("content1").style.display = 'none';
    document.getElementById("content2").style.display = 'none';
    document.getElementById("content3").style.display = 'block';
    document.getElementById("changeContent3").classList.add('is-active');
    document.getElementById("changeContent1").classList.remove('is-active');
    document.getElementById("changeContent2").classList.remove('is-active');
}