<#include "../layout/_layout.ftlh"/>

<@javascript>
    <script src="${resVar.resourceBashPath}static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${resVar.resourceBashPath}${theme.themePath}static/js/storage.js"></script>
</@javascript>
<@layout title="存储管理">
    <input type="hidden" value="" id="currentTreeId">
    <div class="d-flex container">
        <div class="w-25">
            <#include "siderbar.ftlh"/>
        </div>
        <div class="px-3 w-100">
            <p class="h1">存储管理</p>
            <button type="button" data-bs-target="#editModal" data-type="new" data-bs-toggle="modal" class="btn btn-outline-success btn-sm">新增存储</button>
            <ul class="list-group mt-2">
                <#list storageVOS as storageVO>
                    <li class="list-group-item py-3">
                        <span>${storageVO.name}</span>
                        <div class="btn-group float-end" role="group">
                            <button type="button" data-bs-target="#linkModal" data-bs-toggle="modal" data-id="${storageVO.id?c}"
                                    data-name="${storageVO.name}"
                                    data-ref-storage-id="${(storageVO.refStorageId?c)!}" data-ref-storage-type="${storageVO.refStorageType!}"
                                    class="btn btn-outline-success btn-sm">关联存储</button>
                            <button type="button" data-bs-toggle="modal" data-bs-target="#editModal" data-type="edit"
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
        </div>
    </div>

    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title h5" id="exampleModalLabel">新增存储</div>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <input type="hidden" id="storage-id">
                        <input type="hidden" id="action-type">
                        <div class="mb-3">
                            <label for="storage-name" class="form-label">名称</label>
                            <input type="text" class="form-control required" id="storage-name">
                        </div>
                        <div class="mb-3">
                            <label for="storage-work-type" class="col-sm-2 col-form-label">工作模式:</label>
                            <div class="col-sm-12">
                                <select class="form-select" id="storage-work-type" >
                                    <option value="MAIN">主存储</option>
                                    <option value="BACKUP">备存储</option>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3 main-storage-box">
                            <label for="main-storage" class="col-sm-2 col-form-label">主存储:</label>
                            <div class="col-sm-12">
                                <select class="form-select" id="main-storage" >
                                    <#list mainStorageVO as storageVO>
                                        <option value="${storageVO.id?c}">${storageVO.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="storage-writable">
                            <label class="form-check-label" for="storage-writable">是否可写</label>
                        </div>
                        <div class="mb-3 row">
                            <label for="message-text" class="col-form-label">描述:</label>
                            <textarea class="form-control" id="storage-description"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="saveStorage">保存</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="linkModal" tabindex="-1" role="dialog" aria-labelledby="linkModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="h5 modal-title" id="exampleModalLabel">关联存储</div>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <input type="hidden" id="selected-storage-id">
                        <div class="mb-3">
                            <label for="storage-name" class="form-label">本地存储:</label>
                            <#list localStorages as localStorage>
                                <div class="form-check form-check-inline">
                                    <input type="radio" value="LOCAL-${localStorage.id?c}" id="local-${localStorage.id?c}" name="storage" class="form-check-input">
                                    <label class="form-check-label" for="local-${localStorage.id?c}">${localStorage.name}</label>
                                </div>
                            </#list>
                        </div>
                        <div class="mb-3">
                            <label for="storage-name" class="form-label">Github存储:</label>
                            <#list githubStorages as githubStorage>
                                <div class="form-check form-check-inline">
                                    <input type="radio" value="GITHUB-${githubStorage.id?c}" id="github-${githubStorage.id?c}" name="storage" class="form-check-input">
                                    <label class="form-check-label" for="github-${githubStorage.id?c}">${githubStorage.name}</label>
                                </div>
                            </#list>
                        </div>
                        <div class="mb-3">
                            <label for="storage-name" class="form-label">Gitee存储:</label>
                            <#list giteeStorages as giteeStorage>
                                <div class="form-check form-check-inline">
                                    <input type="radio" value="GITEE-${giteeStorage.id?c}" id="gitee-${giteeStorage.id?c}" name="storage" class="form-check-input">
                                    <label class="form-check-label" for="gitee-${giteeStorage.id?c}">${giteeStorage.name}</label>
                                </div>
                            </#list>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="saveLinkStorage">保存</button>
                </div>
            </div>
        </div>
    </div>

</@layout>