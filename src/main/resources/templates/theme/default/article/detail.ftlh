<#include "../layout/_layout.ftlh"/>
<#assign articleOptional=articleVar.article(articleId) />

<#if articleOptional.present>
    <#assign article=articleOptional.get() />
    <@css>
        <link rel="stylesheet" href="${resVar.resourceBashPath}static/prism/prism.css">
        <link href="https://cdn.bootcdn.net/ajax/libs/viewerjs/1.9.0/viewer.min.css" rel="stylesheet">
    </@css>

    <@javascript>
        <!-- <script src="https://cdn.jsdelivr.net/npm/@firstandthird/toc@2.1.0/dist/toc.bundle.js"></script> -->
        <script src="${resVar.resourceBashPath}static/prism/prism.js" referrerpolicy="origin"></script>
        <script src="https://cdn.bootcdn.net/ajax/libs/viewerjs/1.9.0/viewer.min.js"></script>

        <#if secVar.isLogin()>
            <script src="${resVar.resourceBashPath}static/layer/layer.js" referrerpolicy="origin"></script>
            <script>
                $(function () {
                    $(document).keydown(function (e) {
                        if (69 === e.keyCode) {
                            window.location.href = '/${columnPath}/${articleId?c}/edit';
                        }
                    });
                    $('input[type=radio][name=sharable]').change(function () {
                        console.log(this.value);
                        const that = this;
                        const index = layer.load(1, {
                            shade: [0.1, '#fff']
                        });
                        $.ajax({
                            type: "PUT",
                            url: "/api/article/${(article.id)?c}/share?shareable=" + that.value,
                            contentType: "application/json",
                            dataType: "json",
                            success: function (data) {
                                layer.close(index);
                                layer.msg("修改成功");
                            },
                            error: function (message) {
                                layer.close(index);
                                layer("提交失败" + JSON.stringify(message));
                            }
                        });
                    });

                });
                const gallery = new Viewer(document.getElementById('detail-body'));
            </script>
        </#if>
    </@javascript>
    <@layout title=article.title>
        <div class="d-flex px-4 h-100">
            <div class="flex-shrink-1 col-4 col-md-3 col-sm-4 col-xl-2 bd-sidebar h-100">
                <#include "siderbar.ftlh"/>
            </div>
            <main class="col-8 col-md-9 col-sm-8 col-xl-10 line-numbers flex-fill px-5 py-3 overflow-auto h-100" role="main" id="article-content">
                <div class="detail-title">
                    <p class="h1" style="display:inline-block">${article.title}</p>
                    <#if secVar.isLogin()>
                        <div class="share2" style="float:right">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="sharable" id="sharable_share"
                                       value="SHAREABLE" <#if article.sharable.code==0>checked</#if>>
                                <label class="form-check-label" for="sharable_share">公开</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="sharable" id="sharable_private"
                                       value="PRIVATE" <#if article.sharable.code==1>checked</#if>>
                                <label class="form-check-label" for="sharable_private">私有</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="sharable" id="sharable_inherited"
                                       value="INHERITED" <#if article.sharable.code==2>checked</#if>>
                                <label class="form-check-label" for="sharable_inherited">继承</label>
                            </div>
                        </div>
                    </#if>
                </div>
                <div class="detail-body" id="detail-body">
                    ${article.content!?no_esc}
                </div>
            </main>
        </div>
    </@layout>
<#else >
    <#include "../notFound.ftlh" />
</#if>