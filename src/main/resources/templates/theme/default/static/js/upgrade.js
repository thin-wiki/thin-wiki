const alert = layer;

$(document).ready(function () {
    $("#upgrade").click(function () {
        layer.confirm('您确定要更新系统么？', {
            btn: ['确实', '取消'] //按钮
        }, function () {
            const index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                type: "PUT",
                url: "/api/upgrade",
                contentType: "application/json",
                success: function (data) {
                    alert.close(index)
                    if (data.code !== 0) {
                        alert.msg(data.msg);
                        return
                    }
                    alert.msg("系统安装成功，正在等待更新");
                    window.location.reload();
                },
                error: function (message) {

                }
            });

        }, function () {
        });
    });
    $("#restart").click(function () {
        layer.confirm('您确定要重启系统么？', {
            btn: ['确实', '取消'] //按钮
        }, function () {
            const index = layer.load(1, {
                shade: [0.1, '#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                type: "PUT",
                url: "/api/upgrade/restart",
                contentType: "application/json",
                success: function (data) {
                    alert.close(index)
                    if (data.code !== 0) {
                        alert.msg(data.msg);
                        return
                    }
                    alert.msg("系统重启成功，请稍后刷新页面");
                    window.location.reload();
                },
                error: function (message) {
                    alert.close(index)
                    alert.msg("系统重启成功，请稍后刷新页面");
                    // window.location.reload();
                    // alert.msg("失败" + message);
                }
            });

        }, function () {
        });
    });
});
