$(document).ready(
    function () {
        document.getElementById("workplaceContent").style.display = 'block';
        document.getElementById("timeContent").style.display = 'none';
        document.getElementById("downloadContent").style.display = 'none';
    }
);



function changeContent1() {
    document.getElementById("workplaceContent").style.display = 'none';
    document.getElementById("timeContent").style.display = 'block';
    document.getElementById("downloadContent").style.display = 'none';
    document.getElementById("workplace").classList.add('active');
    document.getElementById("time").classList.add('active');

}

function changeContent2() {
    document.getElementById("workplaceContent").style.display = 'none';
    document.getElementById("timeContent").style.display = 'none';
    document.getElementById("downloadContent").style.display = 'block';
    document.getElementById("workplace").classList.add('active');
    document.getElementById("time").classList.add('active');
    document.getElementById("download").classList.add('active');
}
