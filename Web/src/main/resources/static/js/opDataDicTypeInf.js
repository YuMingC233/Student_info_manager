/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-11 15:21:26
 * @version: 1.0
 */
$(function () {
    $("#ddtAddForm").submit(function (e) {
        var ddtInsInf = {
            TypeID: GetQueryString("TypeID") == null ? null : GetQueryString("TypeID"),
            TypeName: $("input[name=ddtName]").val(),
            TypeDesc: $("textarea[name=ddtDetails]").val(),
        };

        if (Mode === "Add") {
            $.ajax({
                type: "post",
                url: "addDicTypeInfo",
                data: JSON.stringify(ddtInsInf),
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
                            location.href = "DicTypePage"
                        })
                    }
                }
            });
        } else {
            $.ajax({
                type: "post",
                url: "modDicTypeInfo",
                data: JSON.stringify(ddtInsInf),
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
                            location.href = "DicTypePage"
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