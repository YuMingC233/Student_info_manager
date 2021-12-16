/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-10 17:02:43
 * @version: 1.0
 */
function toModify(classID) {
    location.href = "ModifyClass?classID=" + classID;
}

function toDelete(classID) {
    $("#delClassNo").val(classID);
}

$(".delClassInfBtn").click(function (e) {
    $.ajax({
        type: "post",
        url: "DeleteClass",
        data: {
            "ClassID": $("#delClassNo").val()
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
    location.href = "addClass";
});