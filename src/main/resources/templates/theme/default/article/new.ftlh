<#include "../layout/_layout.ftlh"/>
<@css>
<#--    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.4.1/build/styles/default.min.css">-->
    <link rel="stylesheet" href="/static/prism/prism.css">

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
    <script src="/static/tinymce/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="/static/prism/prism.js" referrerpolicy="origin"></script>
    <script src="/static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${theme.themePath}static/js/tinymce-editor.js"></script>
    <script src="${theme.themePath}static/js/article.js"></script>
</@javascript>

<@layout>
    <input type="hidden" id="modifyType" value="new">
    <input type="hidden" id="columnPath" value="${columnPath}">
    <input type="hidden" id="articleId" value="${(articleId!0)?c}">
    <div class="flex-xl-nowrap">
        <main class="bd-content edit-content line-numbers" role="main">
            <form>
                <div class="form-group">
                    <input type="text" class="form-control" id="title" placeholder="">
                </div>
                <div class="form-group">
                    <textarea id="edit-content"></textarea>
                </div>
            </form>
        </main>
    </div>
</@layout>