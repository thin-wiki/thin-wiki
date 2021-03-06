const alert = layer;
const columnId = $("#columnId").val();
tinymceEditor(function () {
    const modifyType = $("#modifyType").val();
    const reqData = {};
    const path = $("#path").val()
    reqData.title = $("#title").val();
    reqData.path = path;
    reqData.content = tinymce.activeEditor.getContent();

    let reqType;
    let reqUrl;
    if (modifyType === 'new') {
        reqType = "POST";
        reqUrl = "/api/article/column";
    } else {
        reqType = "PUT";
        reqUrl = "/api/article/column/" + columnId;
    }
    const index = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    $.ajax({
        type: reqType,
        url: reqUrl,
        contentType: "application/json",
        data: JSON.stringify(reqData),
        dataType: "json",
        success: function (data) {
            alert.close(index)
            if (data.code !== 0) {
                alert.msg(data.msg)
                return
            }
            alert.msg("提交成功");
            window.location.href = "/" + path + "/";
        },
        error: function (message) {
            alert.msg("提交失败" + JSON.stringify(message));
        }
    });
}, columnId, "COLUMN");