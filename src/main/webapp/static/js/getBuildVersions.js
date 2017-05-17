function getBuildVersions(tegId, tar) {

    var name = $(tar).val();
    $.ajax({
        url: '/getBuildVersion?projectName='.concat(name),
        success: function (html) {
            $(tegId).html(html);
        }
    });
}