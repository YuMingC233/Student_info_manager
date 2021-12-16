/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-05 17:19:05
 * @version: 1.0
 */
$(function () {
    $("#stuSearchForm").submit(function (e) {
        aboutForm.searchStuInf(1, aboutForm.getFormData());
    });

    $("#toAdd").click(function (e) {
        location.href = "addStu";
    });

    if (SearchMode) {
        $("#FirstPage").click(function (e) {
            aboutForm.searchStuInf(1, aboutForm.getFormData())
        });

        $("#PrevPage").click(function (e) {
            var NowPage = parseInt($(".page-item.active > a").text());
            aboutForm.searchStuInf(NowPage - 1, aboutForm.getFormData())
        });

        $("#NextPage").click(function (e) {
            var NowPage = parseInt($(".page-item.active > a").text());
            aboutForm.searchStuInf(NowPage + 1, aboutForm.getFormData())
        });

        $("#EndPage").click(function (e) {
            aboutForm.searchStuInf(TotalPage, aboutForm.getFormData())
        });

        $(".page-item:not([id])").click(function (e) {
            var needSearchPage = parseInt($($(this).find("a")).text());
            aboutForm.searchStuInf(needSearchPage, aboutForm.getFormData())
        });
    }
});

function toDetails(stuNo) {
    location.href = "DetailStu?StuNo=" + stuNo;
}

function toModify(stuNo) {
    location.href = "ModifyStu?StuNo=" + stuNo;
}

function toDelete(stuNo) {
    $("#delStuNo").val(stuNo);
}

$(".delStuInfBtn").click(function (e) {
    $.ajax({
        type: "post",
        url: "DeleteStu",
        data: {
            "No": $("#delStuNo").val()
        },
        dataType: "json",
        success: function (resp) {
            if (resp.code === 504)
                $('#DBErr').modal("show")
            else if (resp.code === 405)
                $('#inpNullErr').modal("show")
            else {
                $('#sucModal').modal("show")
                $('#sucModal').on('hidden.bs.modal', function () {
                    location.reload();
                })
            }
        }
    });
});



class aboutForm {
    static getFormData() {
        var stuSearchInf = {
            NO: $("input[name=uNo]").val() === "" ? null : $("input[name=uNo]").val(),
            Name: $("input[name=uName]").val() === "" ? null : $("input[name=uName]").val(),
            Sex: $("select[name=uSex]").val() === "null" ? null : $("select[name=uSex]").val(),
            Birthday: null,
            Birthday_Begin: $("input[name=uBirDayBegin]").val(),
            Birthday_Over: $("input[name=uBirDayOver]").val(),
            Admission_Time: null,
            Admission_Time_Begin: $("input[name=uInDateBegin]").val(),
            Admission_Time_Over: $("input[name=uInDateOver]").val(),
            Political_Appearance: null,
            Nation: $("select[name=uNation]").val() === "null" ? null : $("select[name=uNation]").val(),
            Desc: null,
            Pic: null,
            classInf: {
                ClassID: $("select[name=uClass]").val() === "null" ? null : $("select[name=uClass]").val(),
                Name: null,
                gradeInf: null,
                ClassDesc: null
            },
            gradeInf: {
                GradeID: $("select[name=uGrade]").val() === "null" ? null : $("select[name=uGrade]").val(),
                Name: null,
                Desc: null
            }
        };
        return stuSearchInf;
    }

    static searchStuInf(ToPageNum, stuSearchInf) {
        $.ajax({
            type: "post",
            url: "studentPage?offset=" + ToPageNum,
            data: JSON.stringify(stuSearchInf),
            contentType: "application/json",
            success: function (resp) {
                $("#data_refresh").html(resp)
            }
        });
    }
}

