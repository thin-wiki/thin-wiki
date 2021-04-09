<#include "layout/_layout.ftlh"/>

<@layout title="首页">
    <div class="container">
        <div class="p-5 bg-body rounded shadow-sm">
            <div class="border-bottom pb-2 mb-0 fs-5">类目</div>
            <#list articleColumnVar.allList as column>
            <a class="d-flex align-items-center pt-3 pb-3 border-bottom text-decoration-none" href="/${column.path}">
                <svg class="me-2 rounded flex-shrink-0" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img"
                     aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <rect width="100%" height="100%" fill="#${utilVar.randColorCode()}" />
                </svg>
                <div class="link-dark">
                    ${column.title}
                </div>
            </a>
            </#list>
        </div>
    </div>
</@layout>