/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-10 20:47:47
 * @version: 1.0
 */
$(function () {
    $("#clAddForm").submit(function (e) {
        var cGrade = $("select[name=cGrade]").val()

        if (cGrade === 'null') {
            $('#inpNullErr').modal("show")
            return false;
        }

        var clInsInf = {
            ClassID: GetQueryString("classID") == null ? null : GetQueryString("classID"),
            Name: $("input[name=cName]").val(),
            ClassDesc: $("textarea[name=cDetails]").val(),
            gradeInf: {
                GradeID: cGrade,
                Name: null,
                Desc: null
            }
        };

        if (Mode === "Add") {
            $.ajax({
                type: "post",
                url: "addClassInfo",
                data: JSON.stringify(clInsInf),
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
                            location.href = "classPage"
                        })
                    }
                }
            });
        } else {
            $.ajax({
                type: "post",
                url: "modClassInfo",
                data: JSON.stringify(clInsInf),
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
                            location.href = "classPage"
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