<#include "../layout/_layout.ftlh"/>

<@javascript>
    <script src="/static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${theme.themePath}static/js/storage.js"></script>
</@javascript>
<@layout title="存储管理">
    <input type="hidden" value="" id="currentTreeId">
    <div class="row flex-xl-nowrap storages">
        <div class=" bd-sidebar">
            <#include "siderbar.ftlh"/>
        </div>
        <main class="bd-content line-numbers" role="main">
            <p class="h1">存储管理</p>
            <button type="button" data-target="#editModal" data-type="new" data-toggle="modal" class="btn btn-outline-success btn-sm storage-new">新增存储</button>
            <ul class="list-group">
                <#list storageVOS as storageVO>
                    <li class="list-group-item"><a class="storage">${storageVO.name}</a>
                        <div class="btn-group float-right" role="group">
                            <button type="button" data-target="#linkModal" data-toggle="modal" data-id="${storageVO.id?c}"
                                    data-name="${storageVO.name}"
                                    data-ref-storage-id="${(storageVO.refStorageId?c)!}" data-ref-storage-type="${storageVO.refStorageType!}"
                                    class="btn btn-outline-success btn-sm">关联存储</button>
                            <button type="button" data-toggle="modal" data-target="#editModal" data-type="edit"
                                    data-id="${storageVO.id?c}"
                                    data-name="${storageVO.name}" data-work-type="${storageVO.workType}"
                                    data-writable="${storageVO.writable?string("on","off")}" data-description="${storageVO.description!}"
                            class="btn btn-outline-success btn-sm">编辑</button>
                            <button type="button" title="${storageVO.name}" storageId="${storageVO.id?c}" class="deleteStorage btn btn-outline-danger btn-sm">删除</button>
                        </div>
                        <span class="badge badge-primary badge-pill">0</span>
                        <div>
                            <small>可写：${storageVO.writable!?string}，工作模式：${storageVO.workType}，<#if storageVO.workType.toString() == "BACKUP">主节点：${storageVO.mainStorageName!}，</#if>
                            存储类型：${storageVO.refStorageType!}，存储：${storageVO.refStorageName!}</small>
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
                    <h5 class="modal-title" id="exampleModalLabel">新增存储</h5>
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
                            <input type="text" class="form-control required" id="storage-name">
                        </div>
                        <div class="form-group">
                            <label for="storage-owner" class="col-form-label">工作模式:</label>
                            <select class="custom-select" id="storage-work-type" >
                                <option value="MAIN">主存储</option>
                                <option value="BACKUP">备存储</option>
                            </select>
                        </div>
                        <div class="form-group main-storage-box">
                            <label for="storage-owner" class="col-form-label">主存储:</label>
                            <select class="custom-select" id="main-storage" >
                                <#list mainStorageVO as storageVO>
                                    <option value="${storageVO.id?c}">${storageVO.name}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="storage-writable">
                            <label class="custom-control-label" for="storage-writable">是否可写</label>
                        </div>
                        <div class="form-group">
                            <label for="message-text" class="col-form-label">描述:</label>
                            <textarea class="form-control" id="storage-description"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="saveStorage">保存</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="linkModal" tabindex="-1" role="dialog" aria-labelledby="linkModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">关联存储</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <input type="hidden" id="selected-storage-id">
                        <div class="form-group">
                            <label for="storage-name" class="col-form-label">本地存储:</label>
                            <#list localStorages as localStorage>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" value="LOCAL-${localStorage.id?c}" id="local-${localStorage.id?c}" name="storage" class="custom-control-input">
                                    <label class="custom-control-label" for="local-${localStorage.id?c}">${localStorage.name}</label>
                                </div>
                            </#list>
                        </div>
                        <div class="form-group">
                            <label for="storage-name" class="col-form-label">Github存储:</label>
                            <#list githubStorages as githubStorage>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" value="GITHUB-${githubStorage.id?c}" id="github-${githubStorage.id?c}" name="storage" class="custom-control-input">
                                    <label class="custom-control-label" for="github-${githubStorage.id?c}">${githubStorage.name}</label>
                                </div>
                            </#list>
                        </div>
                        <div class="form-group">
                            <label for="storage-name" class="col-form-label">Gitee存储:</label>
                            <#list giteeStorages as giteeStorage>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" value="GITEE-${giteeStorage.id?c}" id="gitee-${giteeStorage.id?c}" name="storage" class="custom-control-input">
                                    <label class="custom-control-label" for="gitee-${giteeStorage.id?c}">${giteeStorage.name}</label>
                                </div>
                            </#list>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="saveLinkStorage">保存</button>
                </div>
            </div>
        </div>
    </div>

</@layout>