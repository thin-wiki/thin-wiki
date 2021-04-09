<header class="py-2 px-3 mb-2 border-bottom sticky-top bg-white">
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="navbar-brand">${sysVar.systemConfig().webSiteName}</a>
        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/" class="nav-link px-2 active link-dark">首页</a></li>
            <#if secVar.isLogin() && columnPath??>
                <li class="nav-item">
                    <#if articleId??>
                        <a class="nav-link  link-dark btn-light btn btn-sm" href="/${columnPath}/${articleId?c}/new">新 增</a>
                    <#else >
                        <a class="nav-link  link-dark btn-light btn btn-sm" href="/${columnPath}/new">新 增</a>
                    </#if>
                </li>
            </#if>
        </ul>

        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" method="get" action="/search">
            <input type="search" class="form-control" name="keyword" placeholder="Search..." />
        </form>
        <div class="dropdown text-end">
            <#if !secVar.isLogin()>
<#--                <a class="btn btn-success" href="/login" style="margin: 0 20px;">登录</a>-->
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                      <li><a href="/login" class="nav-link px-2 link-dark">登录</a></li>
                </ul>
            <#else >
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown"
                   aria-expanded="false">
                    <!-- <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle"> -->
                    我的
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                    <li><a class="dropdown-item" href="/column">类目管理</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="/storage">存储管理</a></li>
                    <li><a class="dropdown-item" href="/config">系统配置</a></li>
<#--                    <li><a class="dropdown-item" href="/upgrade">升级管理</a></li>-->
                    <li><a class="dropdown-item" href="/backup">数据备份</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" target="_blank" href="/admin/performance">监控</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="/logout">退出</a></li>
                </ul>
            </#if>
        </div>
    </div>
</header>