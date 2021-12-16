/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-11 15:20:58
 * @version: 1.0
 */
function toModify(TypeID) {
    location.href = "ModifyDicType?TypeID=" + TypeID;
}

function toDelete(TypeID) {
    $("#delDicTypeID").val(TypeID);
}

$(".delDicTypeInfBtn").click(function (e) {
    $.ajax({
        type: "post",
        url: "DeleteDicType",
        data: {
            "TypeID": $("#delDicTypeID").val()
        },
        dataType: "json",
        success: function (resp) {
            if (resp.code === 504)
                $('#DBErr').modal("show")
            else if (resp.code === 405)
                $('#inpNullErr').modal("show")
            else {
                $('#sucModal').modal("show");
                $('#sucModal').on('hidden.bs.modal', function () {
                    location.reload();
                })
            }
        }
    });
});

$("#toAdd").click(function (e) {
    location.href = "addDicType";
});