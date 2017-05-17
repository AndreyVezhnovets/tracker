$(document).ready(function () {

    $(function () {
        $(".datepicker").datepicker({
            minDate: '0'
        }).datepicker('setDate', new Date());
    });

    $("#log_out").click(function () {
        $.post('/logout')
            .done(function (data) {
                location.replace("/");
            })
            .fail(function (data) {
                $.fn.signInError("Loguot oblom!!");
            })
        ;
        // location.replace("/");
    });

    $("#list_of_project").click(function () {
        location.replace("/project");
    });

    $("#add_issue").click(function () {
        var Summary = $("#Summary").val();
        if ($.fn.isNotEmpty(Summary)) {
            var Description = $("#Description").val();
            var Status = $("#Status").val();
            var Type = $("#Type").val();
            var Priority = $("#Priority").val();
            var Project_name = $("#Project_name").val();
            var Build_found = $("#Build_found").val();
            var Assignee = $("#Assignee").val();
            var url = "/issue/add/new?";
            var parameterName = {
                summary: Summary,
                description: Description,
                status: Status,
                type: Type,
                priority: Priority,
                projectName: Project_name,
                buildFound: Build_found,
                assignee: Assignee
            };
            var mapping = $.fn.getUrl(parameterName, url);
            $.get(mapping)
                .done(function (data) {
                    location.replace("/");
                })
                .fail(function (data) {
                    $.fn.showMessage("New issue wasn't added!");
                });
        } else {
            $.fn.showMessage("Issue name cannot be empty!")
        }
    });

    $("#save_project").click(function () {
        var Name = $("#Name").val();
        if ($.fn.isNotEmpty(Name)) {
            var Description = $("#Description").val();
            var Build = $("#Build").val();
            var Manager = $("#Manager").val();
            var url = "/project/add/new?";
            var parameterName = {
                name: Name,
                description: Description,
                build: Build,
                manager: Manager,
            };
            var mapping = $.fn.getUrl(parameterName, url);
            $.get(mapping)
                .done(function (data) {
                    location.replace("/projects");
                })
                .fail(function (data) {
                    $.fn.showMessage("New project wasn't added!");
                });
        } else {
            $.fn.showMessage("Name cannot be empty!")
        }
    });

    $("#edit_project0").click(function () {
        var Name = $("#edit-Name").val();
        if ($.fn.isNotEmpty(Name)) {
            var Description = $("#edit-Description").val();
            var Build = $("#edit-Build").val();
            var Manager = $("#edit-Manager").val();
            var id = $("#old_idProject").text();
            var url = "/project/edit?";
            var parameterName = {
                name: Name,
                description: Description,
                build: Build,
                manager: Manager,
                id: id
            };
            var mapping = $.fn.getUrl(parameterName, url);
            $.get(mapping)
                .done(function (data) {
                    location.replace("/projects");
                })
                .fail(function (data) {
                    $.fn.showMessage("Rroject wasn't edited!");
                });
        } else {
            $.fn.showMessage("Name cannot be empty!")
        }
    });


    $("#project_name").focus(function () {
        $.fn.hideMessage("slow");
    });

    $.fn.hideMessage = function (speed) {
        $("#error_during_add_in").hide(speed);
    }

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