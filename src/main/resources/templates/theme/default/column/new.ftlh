<#include "../layout/_layout.ftlh"/>
<@css>
    <link rel="stylesheet" href="${resVar.resourceBashPath}/static/prism/prism.css">
    <style>
        body{
            overflow-y: inherit !important;
        }
        .edit-content,.bd-content{
            overflow-y: initial !important;
        }
        .tox-editor-container{
            z-index: 1100;
        }
    </style>
</@css>

<@javascript>
    <script src="${resVar.resourceBashPath}static/tinymce/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="${resVar.resourceBashPath}static/prism/prism.js" referrerpolicy="origin"></script>
    <script src="${resVar.resourceBashPath}${theme.themePath}static/js/tinymce-editor.js"></script>
    <script src="${resVar.resourceBashPath}static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${resVar.resourceBashPath}${theme.themePath}static/js/column.js"></script>
</@javascript>

<@layout>
    <div class="container pt-3">
        <input type="hidden" id="modifyType" value="new">
        <div class="input-group mb-3">
            <input type="text" class="form-control" id="title" placeholder="标题">
            <button class="btn btn-outline-secondary save" type="button">保存</button>
        </div>
        <div class="mb-3">
            <input type="text" class="form-control" id="path" placeholder="路径,如：wiki">
        </div>
        <div class="mb-3">
            <textarea class="form-control" id="edit-content" rows="3"></textarea>
        </div>
    </div>
</@layout>