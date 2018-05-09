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

                        var additions = 0;
                        var deleted = 0;

                        $.each(user.weeks, function (j, week) {
                            console.log(week.a)
                            additions += week.a;
                            deleted += week.d;
                        });

                        array.push({
                            login: user.author.login,
                            commit: user.total,
                            img: user.author.avatar_url,
                            url: user.author.html_url,
                            add: additions,
                            delet: deleted

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
            "<p style='font-size: 21px;'>" + array[i].login + "<div class='divider'></div></p> " +
            "<p style='font-size: 20px;'><strong>" + array[i].commit +
            "</strong><span class='icon'>" +
            "<i class='fas fa-code-branch fa-xs'> </i>" +
            "</span>" +
            "</p> " +
            "<p style='font-size: 18px; color:green;'>" + array[i].add +
            "<span class='icon'>" +
            "<i class='fas fa-angle-up fa-xs'> </i>" +
            "</span>" +
            "</p> " +
            "<p style='font-size: 18px;'>" + array[i].delet +
            "<span class='icon'>" +
            "<i class='fas fa-angle-down fa-xs'> </i>" +
            "</span>" +
            "<div class='divider'></div>" +
            "</p> " +
            "<span class='icon is-centered' > " +
            "<img class='image' style='border-radius:50%; ' src='" + array[i].img + "'> </img> " +
            "</span>" +
            "<a style='margin:10px' href='" + array[i].url + "'>" +
            "<span class='icon'>" +
            "<i style='width:25px; height:25px;' class='fab fa-github fa-1x'> </i>" +
            "</span></a>" +
            "</div> " +
            "</div> " +
            "</article> " +
            "</div>");
    }
}

function hideLoader() {
    var loaderStat = document.getElementById('loaderStat');
    loaderStat.style.display = 'none';
}
