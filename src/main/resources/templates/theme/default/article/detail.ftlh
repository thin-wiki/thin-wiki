<#include "../layout/_layout.ftlh"/>
<#assign articleOptional=articleVar.post(articleId) />

<#if articleOptional.present>
    <#assign post=articleOptional.get() />
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
                        if (e.target.localName!=='input' && 69 === e.keyCode) {
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
                            url: "/api/post/${(post.id)?c}/share?shareable=" + that.value,
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
                    $(".delete").click(function (){
                        layer.confirm('您确定要删除该文章么？', {
                            btn: ['确实','取消']
                        }, function(){
                            $.ajax({
                                type: "DELETE",
                                url: "/api/post/${(post.id)?c}",
                                contentType: "application/json",
                                dataType: "json",
                                success: function (data) {
                                    if (data.code !== 0){
                                        alert.msg(data.msg);
                                        return;
                                    }
                                    alert.msg("删除成功");
                                    window.location.href="/${columnPath}";
                                },
                                error: function (message) {

                                }
                            });

                        }, function(){

                        });
                    });
                });
                const gallery = new Viewer(document.getElementById('detail-body'));
            </script>
        </#if>
    </@javascript>
    <@layout title=post.title>
        <div class="d-flex px-4 h-100" id="main-content">
            <div class="flex-shrink-1 col-4 col-md-3 col-sm-4 col-xl-2 bd-sidebar h-100 border-end">
                <#include "siderbar.ftlh"/>
            </div>
            <main class="container col-8 col-md-9 col-sm-8 col-xl-10 line-numbers flex-fill px-5 overflow-auto h-100" role="main" id="post-content">
                <div class="detail-title pt-3">
                    <p class="h1" style="display:inline-block">${post.title}</p>
                    <#if secVar.isLogin()>
                        <div class="share2" style="float:right">
                            <button type="button" class="btn-sm btn-outline-danger delete"><i class="bi bi-trash"></i></button>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="sharable" id="sharable_share"
                                       value="SHAREABLE" <#if post.sharable.code==0>checked</#if>>
                                <label class="form-check-label" for="sharable_share">公开</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="sharable" id="sharable_private"
                                       value="PRIVATE" <#if post.sharable.code==1>checked</#if>>
                                <label class="form-check-label" for="sharable_private">私有</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="sharable" id="sharable_inherited"
                                       value="INHERITED" <#if post.sharable.code==2>checked</#if>>
                                <label class="form-check-label" for="sharable_inherited">继承</label>
                            </div>
                        </div>
                    </#if>
                </div>
                <div class="detail-body pb-5 mb-5" id="detail-body">
                    ${post.content!?no_esc}
                </div>
            </main>
        </div>
    </@layout>
<#else >
    <#include "../notFound.ftlh" />
</#if>