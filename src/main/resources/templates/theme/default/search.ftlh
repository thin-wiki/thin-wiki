<#include "layout/_layout.ftlh"/>
<@javascript>
    <script>
        function highlight(loadedDivId) {
            var txt = "${segmentKeyword!}";
            var div = document.getElementById(loadedDivId);
            var ts = div.getElementsByClassName("search_item");

            var kws = txt.split(/[ 　]/);
            for (var i = 0; i < kws.length; i++) {
                var kw = String(kws[i]).trim();
                if (kw.length < 1) {
                    continue;
                }
                var fontText = "<font class='rt'>";
                if (fontText.indexOf(kw.toLowerCase()) > -1) {
                    continue;
                }
                if ("</font>".indexOf(kw.toLowerCase()) > -1) {
                    continue;
                }
                for (var j = 0; j < ts.length; j++) {
                    var html = ts[j].innerHTML;
                    ts[j].innerHTML =
                        html.replace(new RegExp(kw, 'gi'),
                            fontText + kw + "</font>");
                }
            }
        }

        setTimeout(function () {
            highlight("search_result");
        }, 100);
    </script>
</@javascript>

<@css>
    <style>
        .rt {
            color: red;
        }
    </style>
</@css>

<@layout title="首页">
    <main class="index-main-box" role="main">
        <p class="h1">Search ${keyword}</p>
        <div class="list-group" id="search_result">
            <#list page.content as article>
                <a href="/${columnVar.columnPath(article.columnId)}/${(article.id)?c}" target="_blank"
                   class="list-group-item list-group-item-action search_item">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">${article.title?no_esc}</h5>
                        <small>${article.lastModifiedDate?string("yyyy-MM-dd HH:mm:ss")}</small>
                    </div>
                    <p class="mb-1">${article.content?no_esc}.</p>
                </a>
            </#list>
        </div>
    </main>
</@layout>