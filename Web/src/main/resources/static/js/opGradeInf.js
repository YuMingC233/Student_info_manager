/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-11 11:02:15
 * @version: 1.0
 */
$(function () {
    $("#grAddForm").submit(function (e) {
        var grInsInf = {
            GradeID: GetQueryString("GradeID") == null ? null : GetQueryString("GradeID"),
            Name: $("input[name=gName]").val(),
            Desc: $("textarea[name=gDetails]").val(),
        };

        if (Mode === "Add") {
            $.ajax({
                type: "post",
                url: "addGradeInfo",
                data: JSON.stringify(grInsInf),
                contentType: "application/json",
                dataType: "json",
                success: function (resp) {
                    if (resp.code === 504)
                        $('#DBErr').modal("show")
                    else if (resp.code === 405)
                        $('#inpNullErr').modal("show")
                    else {
                        $('#sucModal').modal("show")
                        $('#sucModal').on('hidden.bs.modal', function () {
                            location.href = "gradePage"
                        })
                    }
                }
            });
        } else {
            $.ajax({
                type: "post",
                url: "modGradeInfo",
                data: JSON.stringify(grInsInf),
                contentType: "application/json",
                dataType: "json",
                success: function (resp) {
                    if (resp.code === 504)
                        $('#DBErr').modal("show")
                    else if (resp.code === 405)
                        $('#inpNullErr').modal("show")
                    else {
                        $('#sucModal').modal("show")
                        $('#sucModal').on('hidden.bs.modal', function () {
                            location.href = "gradePage"
                        })
                    }
                }
            });
        }
    });
});


function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}