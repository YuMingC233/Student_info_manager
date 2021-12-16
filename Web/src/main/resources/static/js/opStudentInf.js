/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-09 14:40:42
 * @version: 1.0
 */
$(function () {
    $("#stuAddForm").submit(function (e) {

        var uSex = $("select[name=uSex]").val()
        var uPA = $("select[name=uPA]").val()
        var uNation = $("select[name=uNation]").val()
        var uClass = $("select[name=uClass]").val()

        if (uSex === 'null' || uPA === 'null' ||
            uNation === 'null' || uClass === 'null') {
            $('#inpNullErr').modal("show")
            return false;
        }

        var file = $("input[name=uPic]").val();
        var fileName = getFileName(file);

        function getFileName(o){
            var pos=o.lastIndexOf("\\");
            return o.substring(pos+1);
        }

        var stuInsInf = {
            NO: $("input[name=uNo]").val() ,
            Name: $("input[name=uName]").val() ,
            Sex:  uSex,
            Birthday: $("input[name=uBirDay]").val(),
            Birthday_Begin: null,
            Birthday_Over: null,
            Admission_Time: $("input[name=uInDate]").val(),
            Admission_Time_Begin: null,
            Admission_Time_Over: null,
            Political_Appearance: uPA,
            Nation: uNation,
            Desc: $("textarea[name=uDetails]").val(),
            Pic: fileName === "" ? null : fileName,
            classInf: {
                ClassID: uClass,
                Name: null,
                gradeInf: null,
                ClassDesc: null
            },
            gradeInf: {
                GradeID: null,
                Name: null,
                Desc: null
            }
        };

        if (Mode === "Add") {
            $.ajax({
                type: "post",
                url: "addStudentInfo",
                data: JSON.stringify(stuInsInf),
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
                            location.href = "studentPage"
                        })
                    }
                }
            });
        } else {
            $.ajax({
                type: "post",
                url: "ModifyStudentInfo",
                data: JSON.stringify(stuInsInf),
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
                            location.href = "studentPage"
                        })
                    }
                }
            });
        }
    });

    $(".backBtn").click(function (e) {
        location.href = "studentPage";
    });
});