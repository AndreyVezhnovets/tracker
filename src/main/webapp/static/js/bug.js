$(document).ready(function () {
/*    $(function () {
        $(".datepicker").datepicker({
            minDate: '0'
        }).datepicker('setDate', new Date());
    });
    $(function () {
        var elem, size, text;
        var table = document.getElementById('myTable1');
        elem = table.getElementsByTagName('td');
        size = 100;
        for (var i = 0; i < elem.length; i++) {
            text = elem[i].innerHTML;
            if (text.length > size) {
                text = text.slice(0, 100);
                elem[i].innerHTML = text + '...';
            }
        }
    });*/

    $("#log_out").click(function () {
        location.replace("/logout");
    });

    $.fn.isNotEmpty = function (param) {
        if (param.length > 0)
            return true;
        return false;
    }

    $(window).load(function () {
        $.fn.hideMessage("high");
    });

    $.fn.showMessage = function (message) {
        $("#error_during_add_in").show("slow");
        $("#error_during_add_in").text(message);
    }

    $.fn.getUrl = function (parameterName, url) {
        var recursiveDecoded = decodeURIComponent($.param(parameterName));
        return url + recursiveDecoded;
    }
});