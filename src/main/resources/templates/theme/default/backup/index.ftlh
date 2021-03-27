<#include "../layout/_layout.ftlh"/>
<@css>
    <style>
        #newBackup {
            margin: 10px 0;
        }

        .time {
            font-size: 10px;
            margin-top: 5px;
            color: #555;
            font-size: 12px
        }
    </style>
</@css>
<@javascript>
    <script src="${resVar.resourceBashPath}static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${resVar.resourceBashPath}${theme.themePath}static/js/backup.js"></script>
</@javascript>
<@layout title="数据备份">
    <div class="row flex-xl-nowrap storages">
        <main class="bd-content" role="main" style="width: 100%">
            <p class="h1">数据备份</p>
            <a type="button" href="#" class="btn btn-outline-success btn-sm" id="newBackup">新增备份</a>
            <ul class="list-group">
                <#list backupFiles as backupFile>
                    <li class="list-group-item">
                        <div style="display: inline-block">
                            <div class="column">${backupFile.fileName}</div>
                            <div class="time">备份时间：${backupFile.lastModified?string("yyyy-MM-dd HH:mm:ss")}</div>
                        </div>
                        <div class="btn-group float-right" role="group">
                            <a type="button" href="/backup/${backupFile.fileName}" class="btn btn-outline-success btn-sm" target="_bank">下载</a>
                            <button type="button" filename="${backupFile.fileName}"
                               class="btn btn-outline-success btn-sm">还原</button>
                            <button type="button" filename="${backupFile.fileName}"
                               class="btn btn-outline-danger btn-sm deleteBackup">删除</button>
                        </div>
                    </li>
                </#list>
            </ul>
        </main>
    </div>
</@layout>