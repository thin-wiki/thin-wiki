<#include "layout/_layout.ftlh"/>

    <@css>
        <style>
            ul.timeline {
                list-style-type: none;
                position: relative;
            }
            ul.timeline:before {
                content: ' ';
                background: #d4d9df;
                display: inline-block;
                position: absolute;
                left: 29px;
                width: 2px;
                height: 100%;
                z-index: 400;
            }
            ul.timeline > li {
                margin: 20px 0;
                padding-left: 20px;
            }
            ul.timeline > li:before {
                content: ' ';
                background: white;
                display: inline-block;
                position: absolute;
                border-radius: 50%;
                border: 3px solid #22c0e8;
                left: 20px;
                width: 20px;
                height: 20px;
                z-index: 400;
            }
        </style>
    </@css>

<@layout title="首页">
    <#assign asticles = articleVar.lastModified()/>
    <div class="container mt-5 mb-5">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="h3">最近更新</div>
                <ul class="timeline">
                    <#list asticles as a>
                        <li>
                            <a target="_blank" class="text-decoration-none" href="/${columnVar.columnPath(a.columnId)}/${(a.id)?c}">${a.title}</a>
                            <span class="float-end">${a.lastModifiedDate?string("yyyy-MM-dd HH:mm")}</span>
                            <#--                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque scelerisque diam non nisi semper, et elementum lorem ornare. Maecenas placerat facilisis mollis. Duis sagittis ligula in sodales vehicula....</p>-->
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</@layout>