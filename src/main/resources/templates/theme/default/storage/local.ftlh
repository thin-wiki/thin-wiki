<#include "../layout/_layout.ftlh"/>

<@javascript>
    <script src="${resVar.resourceBashPath}static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${resVar.resourceBashPath}${theme.themePath}static/js/local-storage.js"></script>
</@javascript>
<@layout title="本地存储管理">
    <input type="hidden" value="local" id="currentTreeId">
    <div class="d-flex container">
        <div class="w-25">
            <#include "siderbar.ftlh"/>
        </div>
        <div class="px-3 w-100">
            <p class="h1">本地存储</p>
            <button type="button" data-bs-target="#editModal" data-type="new" data-bs-toggle="modal" class="btn btn-outline-success btn-sm">新增存储</button>
            <ul class="list-group mt-2">
                <#list localStorages as localStorage>
                    <li class="list-group-item py-3">
                        <span>${localStorage.name}</span>
                        <div class="btn-group float-end" role="group">
                            <button type="button" data-bs-toggle="modal" data-bs-target="#editModal" data-type="edit"
                                    data-id="${localStorage.id?c}" data-name="${localStorage.name}" data-path="${localStorage.basePath}"
                                    data-description="${localStorage.description!}" class="btn btn-outline-success btn-sm">编辑</button>
                            <button type="button" title="${localStorage.name}" storageId="${localStorage.id?c}" class="deleteStorage btn btn-outline-danger btn-sm">删除</button>
                        </div>
                        <span class="badge badge-primary badge-pill">0</span>
                        <div>
                            <small>路径：${localStorage.basePath}</small>
                        </div>
                    </li>
                </#list>
            </ul>
        </div>
    </div>

    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="h5 modal-title" id="exampleModalLabel">新增本地存储</div>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <input type="hidden" id="storage-id">
                        <input type="hidden" id="action-type">
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">名称:</label>
                            <input type="text" class="form-control" id="storage-name">
                        </div>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">路径:</label>
                            <input type="text" class="form-control" id="storage-path">
                        </div>
                        <div class="form-group">
                            <label for="message-text" class="col-form-label">描述:</label>
                            <textarea class="form-control" id="storage-description"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="saveLocalStorage">保存</button>
                </div>
            </div>
        </div>
    </div>

</@layout>