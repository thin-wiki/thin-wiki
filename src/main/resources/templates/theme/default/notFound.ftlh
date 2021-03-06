<#include "layout/_layout.ftlh"/>

    <@css>
        <style>
            .notFound{
                max-width: 800px;
                margin: 0 auto;
            }
        </style>
    </@css>

<@layout title="not found">
    <div class="jumbotron notFound">
        <h1 class="display-4">走丢了吧</h1>
<#--        <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>-->
        <hr class="my-4">
        <a class="btn btn-primary btn-lg" href="/" role="button">点我回到首页？</a>
    </div>
</@layout>