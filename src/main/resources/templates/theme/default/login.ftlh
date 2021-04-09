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
     <div class="login-box bd-content">
         <form method="post" action="/login">
             <div class="mb-3">
                 <input type="text" class="form-control" name="account" placeholder="账号" value="${account!}">
             </div>
             <div class="mb-3">
                 <input type="password" class="form-control" name="password" placeholder="密码">
             </div>
             <#if errorMsg??>
                  <small class="form-text text-muted">${errorMsg}</small>
             </#if>
             <button type="submit" class="login-btn btn btn-primary" id="login">登录</button>
         </form>
     </div>
</@layout>