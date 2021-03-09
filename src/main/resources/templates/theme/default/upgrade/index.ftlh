<#include "../layout/_layout.ftlh"/>
<@javascript>
    <script src="/static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${theme.themePath}static/js/upgrade.js"></script>
</@javascript>
<@layout title="升级管理">
    <div class="row flex-xl-nowrap storages">
        <main class="bd-content" role="main" style="width: 100%">
            <p class="h1">升级管理</p>
            <ul class="list-group">
                <li class="list-group-item">本地版本: ${sysVar.version}</li>
                <li class="list-group-item">最新版本: ${sysVar.newestVersion}</li>
            </ul>
            <#if sysVar.hasNewVersion()>
                <#if sysVar.isInstall()>
                    <button type="submit" class="btn btn-primary" id="restart" style="margin-top: 20px">重启应用更新</button>
                <#else >
                    <button type="submit" class="btn btn-primary" id="upgrade" style="margin-top: 20px">下载并安装更新</button>
                </#if>
            </#if>
        </main>
    </div>
</@layout>