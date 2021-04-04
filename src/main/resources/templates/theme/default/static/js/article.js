const alert = layer;
const articleId = $("#articleId").val();
tinymceEditor(function () {
    const columnPath = $("#columnPath").val();
    const modifyType = $("#modifyType").val();
    const reqData = {};
    reqData.title = $("#title").val();
    reqData.content = tinymce.activeEditor.getContent();
    reqData.columnPath = columnPath;

    let reqType;
    let reqUrl;
    if (modifyType === 'new') {
        reqType = "POST";
        reqUrl = "/api/article";
        reqData.parentId = articleId;
    } else {
        reqType = "PUT";
        reqUrl = "/api/article/" + articleId;
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
            if(data.code !== 0){
                alert.msg(data.msg);
                return;
            }
            if (modifyType === 'new') {
                alert.msg("添加成功");
                window.location.href = "/" + columnPath + "/" + data.data;
            } else {
                alert.msg("更新成功");
                window.location.href = "/" + columnPath + "/" + articleId;
            }
        },
        error: function (message) {
            alert("提交失败" + JSON.stringify(message));
        }
    });
}, articleId);