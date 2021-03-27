<nav class="navbar navbar-expand-lg navbar-light bg-light bd-navbar">
    <a class="navbar-brand" href="/">${sysVar.systemConfig().webSiteName} <small class="text-muted">${version!}</small></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">首页 <span class="sr-only">(current)</span></a>
            </li>
            <#if secVar.isLogin() && columnPath??>
                <li class="nav-item">
                    <#if articleId??>
                        <a class="nav-link btn-light btn btn-sm" href="/${columnPath}/${articleId?c}/new">新 增</a>
                    <#else >
                        <a class="nav-link btn-light btn btn-sm" href="/${columnPath}/new">新 增</a>
                    </#if>
                </li>
            </#if>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="get" action="/search">
            <input class="form-control mr-sm-2" name="keyword" value="${keyword!}" type="search" placeholder="搜索"
                   aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
        </form>
        <ul class="navbar-nav" style="margin: 0 10px">
            <#if !secVar.isLogin()>
                <a class="btn btn-success" href="/login" style="margin: 0 20px;">登录</a>
            <#else >
            <#--            <a class="btn btn-success" href="/logout" style="margin: 0 20px;">登出</a>-->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        我的
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/column">类目管理</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/storage">存储管理</a>
                        <a class="dropdown-item" href="/config">系统配置</a>
                        <a class="dropdown-item" href="/upgrade">升级管理</a>
                        <a class="dropdown-item" href="/backup">数据备份</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" target="_blank" href="/admin/performance">监控</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/logout">登出</a>
                    </div>
                </li>
            </#if>
        </ul>
    </div>
</nav>