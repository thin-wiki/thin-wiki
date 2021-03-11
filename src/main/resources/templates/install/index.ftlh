<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

    <title>Thin Wiki 安装向导</title>
</head>
<body>
<div class="container">
    <input type="hidden" id="passCheckToken" value="${passCheckToken?string!}">
    <div class="d-flex align-items-start" style="margin-top: 50px;">
        <div class="nav flex-column nav-pills me-3" id="v-install-tab" role="tablist" aria-orientation="vertical" style="width: 130px;">
            <button class="nav-link active" id="guide-tab" data-bs-toggle="pill" data-bs-target="#guide"
                    type="button" role="tab" aria-controls="v-pills-home" aria-selected="true">安装向导
            </button>
            <button class="nav-link" id="database-tab" data-bs-toggle="pill" disabled
                    data-bs-target="#database"
                    type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false">数据库设置
            </button>
            <button class="nav-link" id="website-setting-tab" data-bs-toggle="pill" disabled
                    data-bs-target="#website-setting" type="button" role="tab" aria-controls="v-pills-messages"
                    aria-selected="false">网站设置
            </button>
            <button class="nav-link" id="account-setting-tab" data-bs-toggle="pill" disabled
                    data-bs-target="#account-setting" type="button" role="tab" aria-controls="v-pills-settings"
                    aria-selected="false">账户设置
            </button>
            <button class="nav-link" id="finish-setting-tab" data-bs-toggle="pill" disabled
                    data-bs-target="#finish-setting" type="button" role="tab" aria-controls="v-pills-settings"
                    aria-selected="false">配置完成
            </button>
        </div>
        <div class="tab-content" id="v-pills-tabContent" style="width: 100%;">
            <div class="tab-pane fade show active" id="guide" role="tabpanel" aria-labelledby="v-pills-home-tab">
                <h1 class="display-4">Thin Wiki 安装向导</h1>
                <hr class="my-4">
                <form>
                    <div class="mb-3">
                        <label for="token" class="form-label">Token </label>
                        <input type="text" class="form-control" id="token">
                        <div class="form-text">请查看[ <b>${authFilePath!}</b> ]文件查看安装token</div>
                    </div>
                    <a type="submit" class="btn btn-primary" id="checkTokenAndNext">继续</a>
                </form>
            </div>
            <div class="tab-pane fade" id="database" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                <h1 class="display-4">数据库配置</h1>
                <hr class="my-4">
                <form>
                    <div class="mb-3">
                        <label for="dbhost" class="form-label">数据库IP </label>
                        <input type="text" class="form-control" id="dbhost" value="127.0.0.1">
                    </div>
                    <div class="mb-3">
                        <label for="dbname" class="form-label">数据库端口 </label>
                        <input type="text" class="form-control" id="dbport" value="3306">
                    </div>
                    <div class="mb-3">
                        <label for="dbname" class="form-label">数据库名称 </label>
                        <input type="text" class="form-control" id="dbname" value="">
                    </div>
                    <div class="mb-3">
                        <label for="dbuser" class="form-label">数据库账户 </label>
                        <input type="text" class="form-control" id="dbuser" value="">
                    </div>
                    <div class="mb-3">
                        <label for="dbpass" class="form-label">数据库密码 </label>
                        <input type="password" class="form-control" id="dbpass">
                    </div>
                    <a type="submit" class="btn btn-primary" id="dbSetting">继续</a>
                </form>
            </div>
            <div class="tab-pane fade" id="website-setting" role="tabpanel" aria-labelledby="v-pills-messages-tab">
                <h1 class="display-4">网站配置</h1>
                <hr class="my-4">
                <form>
                    <div class="mb-3">
                        <label for="websiteName" class="form-label">网站名称 </label>
                        <input type="text" class="form-control" id="websiteName" value="Thin Wiki">
                    </div>
                    <div class="mb-3">
                        <label for="websiteKeyword" class="form-label">网站关键字 </label>
                        <input type="text" class="form-control" id="websiteKeyword" value="Thin Wiki">
                    </div>
                    <div class="mb-3">
                        <label for="websiteDescription" class="form-label">网站描述 </label>
                        <input type="text" class="form-control" id="websiteDescription" value="Thin Wiki">
                    </div>
                    <a type="submit" class="btn btn-primary" id="websiteSetting">继续</a>
                </form>
            </div>
            <div class="tab-pane fade" id="account-setting" role="tabpanel" aria-labelledby="v-pills-settings-tab">
                <h1 class="display-4">账户设置</h1>
                <hr class="my-4">
                <form>
                    <div class="mb-3">
                        <label for="account" class="form-label">用户 </label>
                        <input type="text" class="form-control" id="account" value="">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码 </label>
                        <input type="text" class="form-control" id="password" value="">
                    </div>
                    <a type="submit" class="btn btn-primary" id="accountSetting">继续</a>
                </form>
            </div>

            <div class="tab-pane fade" id="finish-setting" role="tabpanel" aria-labelledby="v-pills-settings-tab">
                <h1 class="display-4">配置完成</h1>
                <hr class="my-4">
                <form>
                    <a type="submit" class="btn btn-primary" id="restart">重启进入系统</a>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js" crossorigin="anonymous"></script>
<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>

<script src="/static/layer/layer.js" referrerpolicy="origin"></script>
<script src="/install/install.js"></script>
</body>
</html>