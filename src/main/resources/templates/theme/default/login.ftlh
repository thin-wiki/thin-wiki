<#include "layout/_layout.ftlh"/>

<@css>
    <style>
        .login-box{
            max-width: 420px;
            margin: 50px auto 0 auto;
        }
        .login-btn{
            width: 100%;
        }
    </style>
</@css>


<@layout title="首页">
     <main class="login-box bd-content" role="main">
         <form method="post" action="/login">
             <div class="form-group">
                 <input type="text" class="form-control" name="account" placeholder="账号" value="${account!}">
<#--                 <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>-->
             </div>
             <div class="form-group">
                 <input type="password" class="form-control" name="password" placeholder="密码">
             </div>
             <#if errorMsg??>
                  <small class="form-text text-muted">${errorMsg}</small>
             </#if>
             <button type="submit" class="login-btn btn btn-primary" id="login">登录</button>
         </form>
     </main>
</@layout>