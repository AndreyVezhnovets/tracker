function htmlLoader(url, tegId) {
    $.ajax({
        url: url,
        success: function (html) {
            $(tegId).html(html);
        }
    });
}
function htmlLoader2(url, tegId, mail) {
    $.ajax({
        url: url.concat(mail),
        success: function (html) {
            $(tegId).html(html);
        }
    });
}