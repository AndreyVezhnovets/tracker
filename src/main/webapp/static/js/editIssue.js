function editIssue() {
    var Summary = $("#SummaryEdit").val();
    if ($.fn.isNotEmpty(Summary)) {
        var Description = $("#DescriptionEdit").val();
        var Status = $("#StatusEdit").val();
        var Type = $("#TypeEdit").val();
        var Priority = $("#PriorityEdit").val();
        var Project_name = $("#Project_nameEdit").val();
        var Build_found = $("#Build_found").val();
        var Assignee = $("#AssigneeEdit").val();
        var Resolution = $("#Resolution").val();
        var id = $("#old_idEdit").text();
        var url = "/issue/update?";
        var parameterName = {
            summary: Summary,
            description: Description,
            status: Status,
            type: Type,
            priority: Priority,
            projectName: Project_name,
            buildFound: Build_found,
            assignee: Assignee,
            id: id,
            resolution: Resolution
        };
        var mapping = $.fn.getUrl(parameterName, url);
        if (((Resolution !== "") & ((Status === "Closed") | (Status === "Resolved"))) | ((Status !== "Closed") && (Status !== "Resolved"))) {

            $.get(mapping)
                .done(function (data) {
                    location.replace("/issueSingle?issueId=" + id);
                })
                .fail(function (data) {
                    $.fn.showMessage("Rroject wasn't updated!");
                });

        } else {
            $.fn.showMessage("Resolution cannot be empty!");
        }
    } else {
        $.fn.showMessage("Project name cannot be empty!")
    }
}
