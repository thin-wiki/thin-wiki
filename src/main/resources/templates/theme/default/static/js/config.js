const alert = layer;

$(document).ready(function () {
    $("#updateConfig").click(function () {

        const reqData = {};
        reqData.webSiteName = $("#webSiteName").val();
        reqData.webSiteKeywords = $("#webSiteKeywords").val();
        reqData.webSiteDescription = $("#webSiteDescription").val();
        reqData.resourceBaseUrlType = $("#resourceBaseUrlType").val();

        const index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });

        $.ajax({
            type: "PUT",
            url: "/api/config/system",
            contentType: "application/json",
            data: JSON.stringify(reqData),
            dataType: "json",
            success: function (data) {
                alert.close(index)
                if (data.code !== 0) {
                    alert.msg(data.msg)
                    return
                }
                alert.msg("更新成功");
                window.location.reload();
            },
            error: function (message) {
                alert.close(index)
                alert.msg("更新成功" + JSON.stringify(message));
            }
        });
    });
});
