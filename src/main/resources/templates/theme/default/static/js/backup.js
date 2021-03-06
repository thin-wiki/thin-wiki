const alert = layer;

$(document).ready(function () {
    $("#newBackup").click(function () {

        const index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });

        $.ajax({
            type: "POST",
            url: "/api/backup",
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                alert.close(index)
                if (data.code !== 0) {
                    alert.msg(data.msg)
                    return
                }
                alert.msg("备份成功");
                window.location.reload();
            },
            error: function (message) {
                alert.close(index)
                alert.msg("备份失败" + JSON.stringify(message));
            }
        });
    });
    $(".deleteBackup").click(function () {


        const filename = $(this).attr('filename');
        layer.confirm('您确定要删除[' + filename + ']备份么？', {
            btn: ['确实', '取消'] //按钮
        }, function () {
            const index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                type: "DELETE",
                url: "/api/backup/" + filename,
                contentType: "application/json",
                success: function (data) {
                alert.close(index)
                    if (data.code !== 0) {
                        alert.msg(data.msg);
                        return
                    }
                    alert.msg("删除成功");
                    window.location.reload();
                },
                error: function (message) {

                }
            });

        }, function () {
        });
    });
});
