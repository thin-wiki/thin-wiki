<#include "../layout/_layout.ftlh"/>

<@javascript>
    <script src="${resVar.resourceBashPath}static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${resVar.resourceBashPath}${theme.themePath}static/js/gitee-storage.js"></script>
</@javascript>
<@layout title="存储管理">
    <input type="hidden" value="gitee" id="currentTreeId">
    <div class="row flex-xl-nowrap storages">
        <div class=" bd-sidebar">
            <#include "siderbar.ftlh"/>
        </div>
        <main class="bd-content line-numbers" role="main">
            <p class="h1">Gitee存储</p>
            <button type="button" data-target="#editModal" data-type="new" data-toggle="modal" class="btn btn-outline-success btn-sm storage-new">新增存储</button>
            <ul class="list-group">
                <#list giteeStorages as giteeStorage>
                    <li class="list-group-item"><a class="storage">${giteeStorage.name}</a>
                        <div class="btn-group float-right" role="group">
                            <button type="button" data-toggle="modal" data-target="#editModal" data-type="edit"
                                    data-id="${giteeStorage.id?c}" data-name="${giteeStorage.name}" data-path="${giteeStorage.basePath}"
                                    data-description="${giteeStorage.description!}" data-token="${giteeStorage.token}"
                                    data-owner="${giteeStorage.owner}" data-repo="${giteeStorage.repo}"
                                    data-branch="${giteeStorage.branch}"
                                    class="btn btn-outline-success btn-sm">编辑</button>
                            <button type="button" title="${giteeStorage.name}" storageId="${giteeStorage.id?c}" class="deleteStorage btn btn-outline-danger btn-sm">删除</button>
                        </div>
                        <span class="badge badge-primary badge-pill">0</span>
                        <div>
                            <small>路径：${giteeStorage.basePath}</small>
                        </div>
                    </li>
                </#list>
            </ul>
        </main>
    </div>

    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">新增本地存储</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <input type="hidden" id="storage-id">
                        <input type="hidden" id="action-type">
                        <div class="form-group">
                            <label for="storage-name" class="col-form-label">名称:</label>
                            <input type="text" class="form-control" id="storage-name">
                        </div>
                        <div class="form-group">
                            <label for="storage-owner" class="col-form-label">owner:</label>
                            <input type="text" class="form-control" id="storage-owner">
                        </div>
                        <div class="form-group">
                            <label for="storage-owner" class="col-form-label">token:</label>
                            <input type="text" class="form-control" id="storage-token">
                        </div>
                        <div class="form-group">
                            <label for="storage-repo" class="col-form-label">repo:</label>
                            <input type="text" class="form-control" id="storage-repo">
                        </div>
                        <div class="form-group">
                            <label for="storage-branch" class="col-form-label">branch:</label>
                            <input type="text" class="form-control" id="storage-branch">
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
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="saveGiteeStorage">保存</button>
                </div>
            </div>
        </div>
    </div>

</@layout>