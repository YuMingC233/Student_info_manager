/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-11 13:02:14
 * @version: 1.0
 */
$(function () {
    $("#ddSearchForm").submit(function (e) {
        aboutForm.searchDataDicInf(1, aboutForm.getFormData());
    });

    $("#toAdd").click(function (e) {
        location.href = "addDataDic";
    });
});

if (SearchMode) {
    $("#FirstPage").click(function (e) {
        aboutForm.searchDataDicInf(1, aboutForm.getFormData())
    });

    $("#PrevPage").click(function (e) {
        var NowPage = parseInt($(".page-item.active > a").text());
        aboutForm.searchDataDicInf(NowPage - 1, aboutForm.getFormData())
    });

    $("#NextPage").click(function (e) {
        var NowPage = parseInt($(".page-item.active > a").text());
        aboutForm.searchDataDicInf(NowPage + 1, aboutForm.getFormData())
    });

    $("#EndPage").click(function (e) {
        aboutForm.searchDataDicInf(TotalPage, aboutForm.getFormData())
    });

    $(".page-item:not([id])").click(function (e) {
        var needSearchPage = parseInt($($(this).find("a")).text());
        aboutForm.searchDataDicInf(needSearchPage, aboutForm.getFormData())
    });
}

function toModify(ddID) {
    location.href = "ModifyDataDic?ID=" + ddID;
}

function toDelete(ddID) {
    $("#delDataDicID").val(ddID);
}

$(".delDataDicInfBtn").click(function (e) {
    $.ajax({
        type: "post",
        url: "DeleteDataDic",
        data: {
            "ID": $("#delDataDicID").val()
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
        var ddSearchInf = {
            ID: null,
            Value: null,
            Desc: null,
            typeInf:  {
                TypeID: null,
                TypeName: $("input[name=ddTypeName]").val() === "" ? null : $("input[name=ddTypeName]").val(),
                TypeDesc: null
            }
        };
        return ddSearchInf;
    }

    static searchDataDicInf(ToPageNum, ddSearchInf) {
        $.ajax({
            type: "post",
            url: "dataDicPage?offset=" + ToPageNum,
            data: JSON.stringify(ddSearchInf),
            contentType: "application/json",
            success: function (resp) {
                $("#data_refresh").html(resp)
            }
        });
    }
}
