<#include "../layout/_layout.ftlh"/>
<@css>
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
<#--    <script src="/static/prism/prism.js" referrerpolicy="origin"></script>-->
    <script src="/static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${theme.themePath}static/js/tinymce-editor.js"></script>
    <script src="${theme.themePath}static/js/column.js"></script>
</@javascript>

<#assign column=articleColumnVar.getByPath("${columnPath}").get()/>

<@layout>
    <input type="hidden" id="modifyType" value="edit">
    <input type="hidden" id="columnId" value="${column.id?c}">
    <div class="flex-xl-nowrap">
        <main class="bd-content edit-content line-numbers" role="main">
            <form>
                <div class="form-group">
                    <label for="title" class="form-label">标题</label>
                    <input type="text" class="form-control" value="${column.title}" id="title" placeholder="标题">
                </div>
                <div class="form-group">
                    <label for="path" class="form-label">路径</label>
                    <input type="text" class="form-control" value="${column.path}" id="path" placeholder="路径">
                </div>
                <div class="form-group">
                    <label for="edit-content" class="form-label">内容</label>
                    <textarea id="edit-content">${column.content!?no_esc}</textarea>
                </div>
            </form>
        </main>
    </div>
</@layout>