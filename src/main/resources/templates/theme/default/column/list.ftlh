<#include "../layout/_layout.ftlh"/>

<@css>
    <style>
        .columns a.column-new{
            margin: 10px 0;
        }
        .columns a.column{
            height: 31px;
            line-height: 31px;
        }
    </style>
</@css>

<@javascript>
    <script src="/static/layer/layer.js" referrerpolicy="origin"></script>
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
                        url: "/api/article/column/"+columnId,
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
    <main class="index-main-box columns" role="main">
        <p class="h1">类目列表</p>
        <a type="button" href="/column/new" class="btn btn-outline-success btn-sm column-new">新增类目</a>
        <ul class="list-group">
            <#list articleColumnVar.allList as column>
                <li class="list-group-item"><a class="column" href="/${column.path}/">${column.title}</a>
                    <div class="btn-group float-right" role="group">
                        <a type="button" href="/${column.path}/edit" class="btn btn-outline-success btn-sm">编辑</a>
                        <#if !columnVar.existArticle(column.id)>
                            <a type="button" href="#" columnId="${column.id?c}" title="${column.title}" class="delete btn btn-outline-danger btn-sm">删除</a>
                        </#if>
                    </div>
                </li>
            </#list>
        </ul>
    </main>
</@layout>