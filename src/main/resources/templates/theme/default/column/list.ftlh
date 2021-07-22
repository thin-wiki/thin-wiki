<#include "../layout/_layout.ftlh"/>

<@javascript>
    <script src="${resVar.resourceBashPath}static/layer/layer.js" referrerpolicy="origin"></script>
    <script>
        const alert = layer;
        $(document).ready(function(){
            $(".delete").click(function (){

                const columnId = $(this).attr('columnId');
                const title = $(this).attr('title');
                layer.confirm('您确定要删除['+title+']栏目么？', {
                    btn: ['确实','取消']
                }, function(){
                    $.ajax({
                        type: "DELETE",
                        url: "/api/post/column/"+columnId,
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
        });
    </script>
</@javascript>

<@layout title="栏目列表">
    <div class="container px-5">
        <p class="h1">类目列表</p>
        <a type="button" href="/column/new" class="btn btn-outline-success btn-sm mb-3">新增类目</a>
        <ul class="list-group">
            <#list articleColumnVar.allList as column>
                <li class="d-flex justify-content-between align-items-center list-group-item">
                    <a class="link-dark text-decoration-none" href="/${column.path}/">${column.title}</a>
                    <div class="btn-group" role="group">
                        <a type="button" href="/${column.path}/edit" class="btn btn-outline-success btn-sm">编辑</a>
                        <#if !columnVar.existArticle(column.id)>
                            <a type="button" href="#" columnId="${column.id?c}" title="${column.title}" class="delete btn btn-outline-danger btn-sm">删除</a>
                        </#if>
                    </div>

                </li>
            </#list>
        </ul>
    </div>
</@layout>