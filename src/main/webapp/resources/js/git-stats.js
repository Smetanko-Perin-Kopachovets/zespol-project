$(document).ready(function () {
    var array = [];
    setTimeout(loadStats, 2000);

    function loadStats() {
        $.ajax({
                type: 'GET',
                url: 'https://api.github.com/repos/Smetanko-Perin-Kopachovets/zespol-project/stats/contributors',
                dataType: 'json',
                success: function (data) {
                    $.each(data, function (i, user) {
                        array.push({
                            login: user.author.login,
                            commit: user.total,
                            img: user.author.avatar_url
                        });
                    });

                    hideLoader();
                    showData(array);

                }
            }
        );
    }
});


function showData(array) {
    for (var i = 0; i < array.length; i++) {

        var gitStat = document.getElementById("git-stats");
        gitStat.innerHTML += (

            "<div class='tile is-parent has-text-centered' >" +
            "<article class='tile is-child box '> " +
            "<div class='level-item '> " +
            "<div> " +
            "<p style='font-size: 25px;'>" + array[i].login + "</p> " +
            "<p class='title'> " + array[i].commit +
            "<span class='icon'>" +
            "<i class='fas fa-code-branch fa-xs'> </i>" +
            "</span>" +
            "</p> " +
            "<div class='is-divider' ></div>" +
            "<span class='icon is-centered' > " +
            "<img class='image' style='border-radius:50%' src='" + array[i].img + "'> </img> " +
            "</span>" +
            "</div> " +
            "</div>     " +
            "</article> " +
            "</div>");
    }
}

function hideLoader() {
    var loaderStat = document.getElementById('loaderStat');
    loaderStat.style.display = 'none';
}
