<#include "_static.ftlh"/>
<#macro layout title="">
    <#assign systemConfig=sysVar.systemConfig()>
<#--<#compress>-->
    <!doctype html>
    <html lang="en" class="h-100">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <title>${title}-${systemConfig.webSiteName}</title>
        <meta name=”Keywords” Content="${systemConfig.webSiteKeywords}">
        <meta name=”Description” Content="${systemConfig.webSiteDescription}">
        <link rel="shortcut icon" href="${resVar.resourceBashPath}static/img/favicon.ico" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@ztree/ztree_v3@3.5.46/css/zTreeStyle/zTreeStyle.css">
        <link href="${resVar.resourceBashPath}${theme.themePath}static/css/style.css" rel="stylesheet">

        ${cssContent!}

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

    </head>
    <body class="d-flex flex-column h-100">

    <#include "_header.ftlh"/>

<#--    <div class="main-container">-->
<#--        <#nested>-->
<#--    </div>-->
    <main class="flex-shrink-1 h-100">
        <#nested>
    </main>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
<#--    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>-->
    <#--    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js" crossorigin="anonymous"></script>-->
    <#--    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>-->
    <#--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>-->
    <#--<#macro javascript>-->
    <#--    <#nested>-->
    <#--</#macro>-->

    <footer class="footer mt-auto py-2 bg-light fixed-bottom">
        <div class="text-center">
            <span class="text-muted">Copyright © 2021-2021 Thin Wiki. All rights reserved. Version: ${version!}</span>
        </div>
    </footer>

    ${javascriptContent}
    </body>
    </html>
<#--</#compress>-->
</#macro>