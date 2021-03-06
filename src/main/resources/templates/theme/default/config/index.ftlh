<#include "../layout/_layout.ftlh"/>

<@javascript>
    <script src="/static/layer/layer.js" referrerpolicy="origin"></script>
    <script src="${theme.themePath}static/js/config.js"></script>
</@javascript>
<@layout title="系统配置">
    <#assign config=sysVar.systemConfig()>
    <div class="row flex-xl-nowrap storages">
        <main class="bd-content" role="main" style="width: 100%">
            <p class="h1">系统配置</p>
            <form>
                <div class="form-group">
                    <label for="webSiteName">网站名称</label>
                    <input type="email" class="form-control" id="webSiteName" value="${config.webSiteName}">
                </div>
                <div class="form-group">
                    <label for="webSiteKeywords">网站关键字</label>
                    <input type="email" class="form-control" id="webSiteKeywords" value="${config.webSiteKeywords}">
                </div>
                <div class="form-group">
                    <label for="webSiteDescription">网站描述</label>
                    <textarea class="form-control" id="webSiteDescription"
                              rows="3">${config.webSiteDescription}</textarea>
                </div>

                <a href="#" type="submit" id="updateConfig" class="btn btn-primary">更新配置</a>
            </form>
        </main>
    </div>
</@layout>