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


<@layout title="首页">
    <main class="login-box bd-content" role="main">
        <form method="post" action="/auth_login">
            <div class="form-group">
                <input type="text" class="form-control" name="account" disabled value="${account!}">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" autofocus="autofocus" placeholder="密码">
            </div>
            <#if errorMsg??>
                <small class="form-text text-muted">${errorMsg}</small>
            </#if>
            <button type="submit" class="login-btn btn btn-primary" id="login">登录</button>
        </form>
    </main>
</@layout>