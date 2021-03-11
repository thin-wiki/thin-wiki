const alert = layer;
$(document).ready(function () {
    $("#checkTokenAndNext").click(function () {
        var token = $("#token").val();
        if (!token) {
            alert.msg("请输入token");
            return;
        }
        $.ajax({
            type: "PUT",
            url: "/api/token?token=" + token,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                if (data.code !== 0) {
                    alert.msg(data.msg)
                    return;
                }
                if (!data.data) {
                    alert.msg("token错误，请重新输入");
                    return;
                }

                $("#guide-tab").attr("disabled", true);
                $("#database-tab").attr("disabled", false);

                new bootstrap.Tab(document.querySelector('#database-tab')).show()
            },
            error: function (message) {
                alert.close(index)
                alert.msg("token校验失败" + JSON.stringify(message));
            }
        });
    });

    $("#dbSetting").click(function () {
        var dbhost = $("#dbhost").val();
        var dbport = $("#dbport").val();
        var dbname = $("#dbname").val();
        var dbuser = $("#dbuser").val();
        var dbpass = $("#dbpass").val();
        if (!dbhost) {
            alert.msg("请输入数据库IP");
            return;
        }
        if (!dbport) {
            alert.msg("请输入数据库端口");
            return;
        }
        if (!dbname) {
            alert.msg("请输入数据库名称");
            return;
        }
        if (!dbuser) {
            alert.msg("请输入数据库账号");
            return;
        }

        const reqData = {};
        reqData.dbHost = dbhost;
        reqData.dbPort = dbport;
        reqData.dbName = dbname;
        reqData.dbUser = dbuser
        reqData.dbPass = dbpass;

        const index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });

        $.ajax({
            type: "PUT",
            url: "/api/database",
            contentType: "application/json",
            data: JSON.stringify(reqData),
            dataType: "json",
            success: function (data) {
                alert.close(index)
                if (data.code !== 0) {
                    alert.msg(data.msg)
                    return;
                }
                if (!data.data) {
                    alert.msg("数据库连接失败，请检查数据库信息");
                    return;
                }

                alert.msg("数据库配置成功");

                $("#database-tab").attr("disabled", true);
                $("#website-setting-tab").attr("disabled", false);

                new bootstrap.Tab(document.querySelector('#website-setting-tab')).show()
            },
            error: function (message) {
                alert.close(index)
                alert.msg("数据库设置失败" + JSON.stringify(message));
            }
        });
    });

    $("#websiteSetting").click(function () {
        var websiteName = $("#websiteName").val();
        var websiteKeyword = $("#websiteKeyword").val();
        var websiteDescription = $("#websiteDescription").val();

        const reqData = {};
        reqData.name = websiteName;
        reqData.keyword = websiteKeyword;
        reqData.description = websiteDescription;

        const index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });

        $.ajax({
            type: "PUT",
            url: "/api/website",
            contentType: "application/json",
            data: JSON.stringify(reqData),
            dataType: "json",
            success: function (data) {
                alert.close(index)
                if (data.code !== 0) {
                    alert.msg(data.msg)
                    return;
                }
                alert.msg("网站信息设置成功");

                $("#website-setting-tab").attr("disabled", true);
                $("#account-setting-tab").attr("disabled", false);

                new bootstrap.Tab(document.querySelector('#account-setting-tab')).show()
            },
            error: function (message) {
                alert.close(index)
                alert.msg("网站信息设置失败" + JSON.stringify(message));
            }
        });
    });

    $("#accountSetting").click(function () {
        var account = $("#account").val();
        var password = $("#password").val();

        if (!account) {
            alert.msg("账户不能为空");
        }
        if (!password) {
            alert.msg("密码不能为空");
        }

        const reqData = {};
        reqData.account = account;
        reqData.password = password;

        const index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });

        $.ajax({
            type: "PUT",
            url: "/api/account",
            contentType: "application/json",
            data: JSON.stringify(reqData),
            dataType: "json",
            success: function (data) {
                alert.close(index)
                if (data.code !== 0) {
                    alert.msg(data.msg)
                    return;
                }
                alert.msg("账户设置成功");

                $("#account-setting-tab").attr("disabled", true);
                $("#finish-setting-tab").attr("disabled", true);

                new bootstrap.Tab(document.querySelector('#finish-setting-tab')).show()
            },
            error: function (message) {
                alert.close(index)
                alert.msg("账户设置设置失败" + JSON.stringify(message));
            }
        });
    });

    $("#restart").click(function () {

        const index = layer.load(1, {
            shade: [0.1, '#fff'] //0.1透明度的白色背景
        });

        $.ajax({
            type: "PUT",
            url: "/api/restart",
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                alert.close(index)
                if (data.code !== 0) {
                    alert.msg(data.msg)
                    return;
                }
                alert.msg("重启成功中，请稍后");
                setTimeout("location.reload()",7000)
            },
            error: function (message) {
                alert.close(index)
                alert.msg("重启失败" + JSON.stringify(message));
            }
        });
    });
});

function checkToken() {
    var passCheckToken = document.getElementById("passCheckToken").value;
    if (passCheckToken == "true") {
        $("#guide-tab").attr("disabled", true);
        $("#database-tab").attr("disabled", false);
        new bootstrap.Tab(document.querySelector('#database-tab')).show()
    }
}

checkToken();
