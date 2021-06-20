<#include "layout/_layout.ftlh"/>

<@css>
    <style>
        .login-box {
            max-width: 420px;
            margin: 50px auto 0 auto;
        }

        .login-btn {
            width: 100%;
        }
    </style>
</@css>

<@javascript>
    <script src="/static/layer/layer.js"></script>
    <script type="text/javascript">
        $("#login").click(function () {
            const data = {
                'password':$("#password").val()
            };
            const index = layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            $.ajax({
                type: 'POST',
                url: '/api/pub/login',
                contentType: "application/json",
                data: JSON.stringify(data),
                dataType: "json",
                success: function (data) {
                    layer.close(index)
                    if(data.code !== 0){
                        layer.msg(data.msg);
                        return;
                    }
                    layer.msg("登录成功");
                    var redirectUrl = '/';
                    if (document.referrer){
                        redirectUrl = document.referrer;
                    }
                    window.location.href = redirectUrl;
                },
                error: function (message) {
                    console.log(message);
                    layer.close(index)
                    if (message.responseJSON && message.responseJSON.msg){
                        layer.msg("登录失败:" + message.responseJSON.msg);
                    }else{
                        layer.msg("登录失败" + JSON.stringify(message));
                    }


                }
            });
        });
    </script>
</@javascript>

<@layout title="首页">
    <div class="login-box bd-content">
        <form method="post" action="return;">
            <div class="mb-3">
                <input type="text" class="form-control" name="account" disabled value="${account!}">
            </div>
            <div class="mb-3">
                <input type="password" class="form-control" name="password" autofocus="autofocus" id="password" placeholder="密码">
            </div>
            <a type="submit" class="login-btn btn btn-primary" id="login">登录</a>
        </form>
    </div>
</@layout>