<#include "layout/_layout.ftlh"/>

<@layout title="首页">
    <main class="index-main-box" role="main">
        <p class="h1">${sysVar.systemConfig().webSiteName}</p>
        <ul class="list-group">
            <#list articleColumnVar.allList as column>
                <li class="list-group-item"><a href="/${column.path}">${column.title}</a></li>
            </#list>
        </ul>
    </main>
</@layout>