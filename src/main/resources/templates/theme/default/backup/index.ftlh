<#include "../layout/_layout.ftlh"/>
<@javascript>
    <script src="${resVar.resourceBashPath}static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${resVar.resourceBashPath}${theme.themePath}static/js/backup.js"></script>
</@javascript>
<@layout title="数据备份">
    <div class="container px-5">
        <p class="h1">数据备份</p>
        <a type="button" href="#" class="btn btn-outline-success btn-sm" id="newBackup">新增备份</a>
        <div class="list-group overflow-auto mt-3">
             <#list backupFiles as backupFile>
                 <div class="list-group-item list-group-item-action" aria-current="true">
                     <div class="d-flex w-100 justify-content-between pb-2">
                         <div class="mb-1">${backupFile.fileName}</div>
<#--                         <small>${backupFile.lastModified?string("yyyy-MM-dd HH:mm:ss")}</small>-->
                         <small>${utilVar.fromToday(backupFile.lastModified)}</small>
                     </div>
<#--                     <p class="mb-1">Some placeholder content in a paragraph.</p>-->
                     <small>大小：${utilVar.getPrintSize(backupFile.length)}</small>
                     <div class="btn-group float-end" role="group">
                         <a type="button" href="/backup/${backupFile.fileName}" class="btn btn-outline-success btn-sm" target="_bank">下载</a>
                         <button type="button" filename="${backupFile.fileName}"
                                 class="btn btn-outline-success btn-sm">还原</button>
                         <button type="button" filename="${backupFile.fileName}"
                                 class="btn btn-outline-danger btn-sm deleteBackup">删除</button>
                     </div>
                 </div>
             </#list>
        </div>
    </div>
</@layout>