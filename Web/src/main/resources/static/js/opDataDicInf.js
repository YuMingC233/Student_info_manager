/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-11 13:02:11
 * @version: 1.0
 */
$(function () {
    $("#dataDicAddForm").submit(function (e) {

        var ddTypeID = $("select[name=ddTypeID]").val()

        if (ddTypeID === 'null') {
            $('#inpNullErr').modal("show")
            return false;
        }

        var ddInsInf = {
            ID: GetQueryString("ID") == null ? null : GetQueryString("ID"),
            Value: $("input[name=ddValue]").val(),
            Desc: $("textarea[name=ddDetails]").val(),
            typeInf:  {
                TypeID: ddTypeID,
                TypeName: null,
                TypeDesc: null
            }
        };

        if (Mode === "Add") {
            $.ajax({
                type: "post",
                url: "addDataDicInfo",
                data: JSON.stringify(ddInsInf),
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
                            location.href = "dataDicPage"
                        })
                    }
                }
            });
        } else {
            $.ajax({
                type: "post",
                url: "ModifyDataDicInfo",
                data: JSON.stringify(ddInsInf),
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
                            location.href = "dataDicPage"
                        })
                    }
                }
            });
        }
    });

    $(".backBtn").click(function (e) {
        location.href = "dataDicPage";
    });
});

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}