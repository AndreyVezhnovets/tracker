$(document).ready(function () {

    $("#log_in").click(function () {
        var mail = $("#user_login").val();
        var password = $("#user_password").val();
        var url = "/Alogin";
        if ($.fn.isNotEmpty(mail) & $.fn.isNotEmpty(password)) {
            if (mail.length > 5) {
                if (password.length > 5) {
                var parameterName = {
                    "mail": mail,
                    "password": password
                };
                $.post(url, parameterName)
                    .done(function (data) {
                        location.replace("/saveInSession");
                    })
                    .fail(function (data) {
                        $.fn.signInError("Login or password don't match!");
                    });
            } else {
                $.fn.signInError("password is to short!");
            }
            } else {
                $.fn.signInError("login is to short!");
            }
        } else {
            $.fn.signInError("Parameters cannot be empty!");
        }
    });

    $(window).load(function () {
        $.fn.hideMessage();
    });

    $("#edd_employee").click(function () {
        var name = $("#new_name").val();
        var surname = $("#new_surname").val();
        var mail = $("#new_mail").val();
        var password = $("#new_password").val();
        var confirmedPassword = $("#confirm_password").val();
        var position = $("#new_position").val();
        var url = "/index/registration?";
        if (name !== "" && surname !== "" && mail !== "" && password !== "" && confirmedPassword !== "") {
            if (password === confirmedPassword) {
                if (password.length > 5) {
                    var parameterName = {
                        "name": name,
                        "surname": surname,
                        "mail": mail,
                        "position": position,
                        "password": password,
                        "confirmedPassword": confirmedPassword
                    };
                    var mapping = $.fn.getUrl(parameterName, url);
                    $.get(mapping)
                        .done(function (data) {
                            location.replace("/employees");
                        })
                        .fail(function (data) {
                            $.fn.registrationError("Registration wasn't success");
                        });
                } else {
                    $.fn.registrationError("Password is'n secure!");
                }
            } else {
                $.fn.registrationError("Passwords don't match!");
            }
        } else {
            $.fn.registrationError("Parameters cannot be empty!");
        }
    });
    $("#update_employee").click(function () {
        var name = $("#new_name").val();
        var surname = $("#new_surname").val();
        var mail = $("#new_mail").val();
        var position = $("#new_position").val();
        var id = $("#old_id").text();
        var url = "/updateEmployee?";
        if ($.fn.noEmptyParams(name, surname, mail)) {
            var parameterName = {
                "name": name,
                "surname": surname,
                "mail": mail,
                "position": position,
                "id": id
            };
            var mapping = $.fn.getUrl(parameterName, url);
            $.get(mapping)
                .done(function (data) {
                    location.replace("/employees");
                })
                .fail(function (data) {
                    $.fn.registrationError("Updation wasn't success");
                });
        } else {
            $.fn.registrationError("Parameters cannot be empty!");
        }
    });

    $("#update_employee_password").click(function () {
        var password = $("#update_password").val();
        var confirm_password = $("#update_confirm_password").val();
        var id = $("#old_id").text();
        var url = "/updateEmployeePassword?";

        if ((password.length > 0) & (confirm_password.length > 0)) {
            if (password !== confirm_password) {
                $.fn.registrationError("is not match!");
            } else {
                var parameterName = {
                    "password": password,
                    "id": id
                };
                var mapping = $.fn.getUrl(parameterName, url);
                $.get(mapping)
                    .done(function (data) {
                        location.replace("/employees");
                    })
                    .fail(function (data) {
                        $.fn.registrationError("Updation wasn't success");
                    });
            }
        } else {
            $.fn.registrationError("Parameters cannot be empty!");
        }
    });

    $.fn.isNotEmpty = function (param) {
        if (param.length > 0)
            return true;
        return false;
    }

    $.fn.noEmptyParams = function (name, surname, login, password, confirmedPassword) {
        if ($.fn.isNotEmpty(name) & $.fn.isNotEmpty(surname) & $.fn.isNotEmpty(login) & $.fn.isNotEmpty(password) & $.fn.isNotEmpty(confirmedPassword))
            return true;
        return false;
    }
    $.fn.noEmptyParams = function (name, surname, mail) {
        if ($.fn.isNotEmpty(name) & $.fn.isNotEmpty(surname) & $.fn.isNotEmpty(mail))
            return true;
        return false;
    }

    $.fn.isPasswordMatch = function (password, confirmedPass) {
        return password === confirmedPass;
    }

    $.fn.getUrl = function (parameterName, url) {
        var recursiveDecoded = decodeURIComponent($.param(parameterName));
        return url + recursiveDecoded;
    }

    $.fn.hideMessage = function () {
        $("#error_during_sign_in").hide("slow");
        $("#error_during_log_in").hide("slow");
    }

    $.fn.registrationError = function (message) {
        $("#error_during_sign_in").show("slow");
        $("#error_during_sign_in").text(message);
    }

    $.fn.signInError = function (message) {
        $("#error_during_log_in").show("slow");
        $("#error_during_log_in").text(message);
    }

    $("#user_login").focus(function () {
        $.fn.hideMessage();
    });
    $("#user_password").focus(function () {
        $.fn.hideMessage();
    });
    $("#new_name").focus(function () {
        $.fn.hideMessage();
    });
    $("#new_surname").focus(function () {
        $.fn.hideMessage();
    });
    $("#new_login").focus(function () {
        $.fn.hideMessage();
    });
    $("#new_password").focus(function () {
        $.fn.hideMessage();
    });
    $("#confirm_password").focus(function () {
        $.fn.hideMessage();
    });
    $("#new_position").focus(function () {
        $.fn.hideMessage();
    });


});
