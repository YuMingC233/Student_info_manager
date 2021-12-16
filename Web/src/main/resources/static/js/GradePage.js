/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-11 11:01:53
 * @version: 1.0
 */
function toModify(GradeID) {
    location.href = "ModifyGrade?GradeID=" + GradeID;
}

function toDelete(GradeID) {
    $("#delGradeNo").val(GradeID);
}

$(".delGradeInfBtn").click(function (e) {
    $.ajax({
        type: "post",
        url: "DeleteGrade",
        data: {
            "GradeID": $("#delGradeNo").val()
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
    location.href = "addGrade";
});