
function relocate(url, requestParamName, requestParam) {

    location.replace(url.concat('?').concat(requestParamName).concat('=').concat(requestParam));

}

function getRequest(url, requestParamName, requestParam) {
    $.ajax({
        url: url.concat('?').concat(requestParamName).concat('=').concat(requestParam),
        success: function () {
            // $(tegId).html(html);
        }
    });
}
function getRequest1(url, requestParamName, requestParam, issueId) {
    $.ajax({
        url: url.concat('?').concat(requestParamName).concat('=').concat(requestParam),
        success: function () {
            htmlLoader2( '/uploadFileList?issueId=', '#uploaded_files', issueId);
        }
    });
}