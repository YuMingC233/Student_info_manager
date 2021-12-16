/**
 * @author: Aiden Connor (YuMingC233@gmail.com)
 * @date: 2021-02-01 15:34:15
 * @version: 1.0
 */
$(function () {
    $("#randImage").click(function (e) {
        $.get("/getCPT")
        $(this).attr("src", "/getCPT");
    });
});