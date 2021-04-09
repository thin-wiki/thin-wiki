<#include "../layout/_layout.ftlh"/>
<#assign article=articleVar.article(articleId).get() />

<@css>
<#--    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.4.1/build/styles/default.min.css">-->
    <link rel="stylesheet" href="${resVar.resourceBashPath}static/prism/prism.css">

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
<#--    <script src="/static/prism/prism.js" referrerpolicy="origin"></script>-->
    <script src="${resVar.resourceBashPath}static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${resVar.resourceBashPath}${theme.themePath}static/js/tinymce-editor.js"></script>
    <script src="${resVar.resourceBashPath}${theme.themePath}static/js/article.js"></script>
</@javascript>

<@layout title="编辑">
    <div class="container pt-3">
        <input type="hidden" id="modifyType" value="edit">
        <input type="hidden" id="columnPath" value="${columnPath}">
        <input type="hidden" id="articleId" value="${(articleId!0)?c}">
        <div class="input-group mb-3">
            <input type="text" class="form-control" id="title" placeholder="标题" value="${article.title}">
            <button class="btn btn-outline-secondary save" type="button">保存</button>
        </div>
        <div class="mb-3">
            <textarea class="form-control" id="edit-content" rows="3">${article.content?no_esc}</textarea>
        </div>
    </div>
</@layout>