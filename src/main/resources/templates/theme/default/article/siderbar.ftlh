<script src="https://cdn.jsdelivr.net/npm/@ztree/ztree_v3@3.5.46/js/jquery.ztree.core.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@ztree/ztree_v3@3.5.46/js/jquery.ztree.exedit.min.js"></script>
<script src="${resVar.resourceBashPath}static/layer/layer.js"></script>
<script type="text/javascript">
    const alert = layer;
    const setting = {
        view: {
            showLine: false,
            showIcon: false,
            selectedMulti: false,
            dblClickExpand: false,
            addDiyDom: addDiyDom
        },
        data: {
            simpleData: {
                enable: true,
                pIdKey: "pid",
            }
        },
        edit: {
            enable: true,
            showRemoveBtn: false,
            showRenameBtn: false,
            drag: {
                isCopy: false,
                isMove: true,
                inner: true
            }
        },
        callback: {
            onClick: function (event, treeId, treeNode) {
                if (${(articleId!0)?c} == treeNode.id) {
                    return;
                }
                window.location.href = '/${columnPath}/' + treeNode.id;
            },
            onDrop: function (event, treeId, treeNodes, targetNode, moveType, isCopy) {
                if (!moveType || isCopy == null) {
                    return;
                }
                const id = treeNodes[0].id;
                var pid = treeNodes[0].pid;
                if (!pid) {
                    pid = 0;
                }
                $.ajax({
                    type: "PUT",
                    url: "/api/article/" + id + "/pid?parentId=" + pid,
                    dataType: "json",
                    success: function (data) {
                        alert.msg("修改成功");
                    },
                    error: function (message) {
                        alert("提交失败" + JSON.stringify(message));
                    }
                });
            },
        }
    };

    const zNodes = ${navVar.getMenus(columnPath)?no_esc};

    function addDiyDom(treeId, treeNode) {
        const spaceWidth = 5;
        const switchObj = $("#" + treeNode.tId + "_switch");
        const icoObj = $("#" + treeNode.tId + "_ico");
        switchObj.remove();
        icoObj.before(switchObj);

        if (treeNode.level > 1) {
            const spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
            switchObj.before(spaceStr);
        }
    }

    $(document).ready(function () {
        const treeObj = $("#treeMenu");
        $.fn.zTree.init(treeObj, setting, zNodes);

        // treeObj.hover(function () {
        //     if (!treeObj.hasClass("showIcon")) {
        //         treeObj.addClass("showIcon");
        //     }
        // }, function () {
        //     treeObj.removeClass("showIcon");
        // });

        const zTree_Menu = $.fn.zTree.getZTreeObj("treeMenu");
        const node = zTree_Menu.getNodeByParam('id', ${(articleId!0)?c});
        zTree_Menu.selectNode(node);
    });
</script>

<#assign columnTitleOptional = columnVar.columnTitle(columnPath)>
<#if columnTitleOptional.present>
    <a class="nav-column text-body" href="/${columnPath}/">${columnTitleOptional.get()}</a>
</#if>
<ul id="treeMenu" class="ztree showIcon"></ul>