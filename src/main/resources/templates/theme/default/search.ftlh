<#include "layout/_layout.ftlh"/>

<@layout title="首页">
    <main class="index-main-box" role="main">
        <div class="h1">Search ${keyword}</div>
        <div class="list-group" id="search_result">
            <#list page.content as article>
                <a href="/${columnVar.columnPath(article.columnId)}/${(article.id)?c}" target="_blank"
                   class="list-group-item list-group-item-action search_item">
                    <div class="d-flex w-100 justify-content-between pb-2">
                        <div class="h5 mb-1">${article.title?no_esc}</div>
                        <small>${article.lastModifiedDate?string("yyyy-MM-dd HH:mm:ss")}</small>
                    </div>
                    <small class="mb-1 text-secondary">${article.content?no_esc}.</small>
                </a>
            </#list>
        </div>
    </main>
</@layout>