/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-11 18:06:28
 * @version: 1.0
 */
$(function () {
    $("#modifyPwdForm").submit(function (e) {
        var oldPwd = $("#inpOldPwd").val();
        var NewPwd = $("#inpNewPwd").val();
        var NewPwdRepeat = $("#inpNewPwdRepeat").val();

        if (NewPwd !== NewPwdRepeat) {
            $("#inpErr").modal("show")
            return false;
        }

        $.ajax({
            type: "post",
            url: "ModifyPassword",
            data: {
                "UserName": uName,
                "oldPwd": oldPwd,
                "NewPwd": NewPwd
            },
            dataType: "json",
            success: function (resp) {
                if (resp.code === 404)
                    $("#nullUserErr").modal("show")
                else if (resp.code === 405)
                    $("#pwdNotEqualErr").modal("show")
                else if (resp.code === 406)
                    $("#DBErr").modal("show")
                else
                    $("#sucModal").modal("show")

                $('#sucModal').on('hidden.bs.modal', function () {
                    location.reload()
                })
            }
        });
    });
});