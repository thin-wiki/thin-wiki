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
    <input type="hidden" id="modifyType" value="new">
    <div class="flex-xl-nowrap">
        <main class="bd-content edit-content line-numbers" role="main">
            <form>
                <div class="form-group">
                    <label for="title" class="form-label">标题</label>
                    <input type="text" class="form-control" id="title" placeholder="标题">
                </div>
                <div class="form-group">
                    <label for="path" class="form-label">路径</label>
                    <input type="text" class="form-control" id="path" placeholder="路径">
                </div>
                <div class="form-group">
                    <label for="edit-content" class="form-label">内容</label>
                    <textarea id="edit-content"></textarea>
                </div>
            </form>
        </main>
    </div>
</@layout>