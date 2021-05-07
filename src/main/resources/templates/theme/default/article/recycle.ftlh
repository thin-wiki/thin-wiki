<#include "../layout/_layout.ftlh"/>

<@javascript>
    <script src="${resVar.resourceBashPath}static/layer/layer.js" referrerpolicy="origin"></script>
    <script>
        const alert = layer;
        $(document).ready(function(){
            $(".clean-recycle").click(function (){
                layer.confirm('您确定要清空回收站么么？', {
                    btn: ['确实','取消']
                }, function(){
                    $.ajax({
                        type: "DELETE",
                        url: "/api/article/recycle/clean",
                        contentType: "application/json",
                        dataType: "json",
                        success: function (data) {
                            if (data.code !== 0){
                                alert.msg(data.msg);
                                return;
                            }
                            alert.msg("清空成功");
                            window.location.reload();
                        },
                        error: function (message) {

                        }
                    });

                }, function(){

                });
            });

            $(".delete").click(function (){
                const columnId = $(this).attr('columnId');
                const title = $(this).attr('title');
                layer.confirm('您确定要删除['+title+']文章么？', {
                    btn: ['确实','取消']
                }, function(){
                    $.ajax({
                        type: "DELETE",
                        url: "/api/article/recycle/"+columnId,
                        contentType: "application/json",
                        dataType: "json",
                        success: function (data) {
                            if (data.code !== 0){
                                alert.msg(data.msg);
                                return;
                            }
                            alert.msg("删除成功");
                            window.location.reload();
                        },
                        error: function (message) {

                        }
                    });

                }, function(){

                });
            });

            $(".restore").click(function (){
                const columnId = $(this).attr('columnId');
                const title = $(this).attr('title');
                layer.confirm('您确定要还原['+title+']文章么？', {
                    btn: ['确实','取消']
                }, function(){
                    $.ajax({
                        type: "PUT",
                        url: "/api/article/recycle/"+columnId+"/restore",
                        contentType: "application/json",
                        dataType: "json",
                        success: function (data) {
                            if (data.code !== 0){
                                alert.msg(data.msg);
                                return;
                            }
                            alert.msg("还原成功");
                            window.location.reload();
                        },
                        error: function (message) {

                        }
                    });

                }, function(){

                });
            });
        });
    </script>
</@javascript>

<@layout title="回收站">
    <div class="container px-5">
        <p class="h1">回收站</p>
        <a type="button" href="#" class="clean-recycle btn btn-outline-danger btn-sm mb-3">清空回收站</a>
        <ul class="list-group">
            <#list articleVar.recycleArticles() as article>
                <li class="d-flex justify-content-between align-items-center list-group-item">
                    <a class="link-dark text-decoration-none" target="_blank" href="/recycle/${article.id?c}">${article.title}</a>
                    <div class="btn-group" role="group">
                        <a type="button" href="#" columnId="${article.id?c}" title="${article.title}" class="restore btn btn-outline-success btn-sm">还原</a>
                        <a type="button" href="#" columnId="${article.id?c}" title="${article.title}" class="delete btn btn-outline-danger btn-sm">删除</a>
                    </div>
                </li>
            </#list>
        </ul>
    </div>
</@layout>