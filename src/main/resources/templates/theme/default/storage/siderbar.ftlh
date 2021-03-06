<script src="https://cdn.jsdelivr.net/npm/@ztree/ztree_v3@3.5.46/js/jquery.ztree.core.min.js"></script>

<script type="text/javascript">

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
                enable: true
            }
        },
        callback: {
            onClick: function (event, treeId, treeNode) {
                window.location.href = '/storage/' + treeNode.id;
            }
        }
    };

    const zNodes = [
        {"id":"","name":"存储管理","pId":0},
        {"id":"local","name":"本地存储","pId":0},
        {"id":"github","name":"Github存储","pId":0},
        {"id":"gitee","name":"Gitee存储","pId":0},
        ];

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

        treeObj.hover(function () {
            if (!treeObj.hasClass("showIcon")) {
                treeObj.addClass("showIcon");
            }
        }, function () {
            treeObj.removeClass("showIcon");
        });

        const zTree_Menu = $.fn.zTree.getZTreeObj("treeMenu");
        const node = zTree_Menu.getNodeByParam('id', $("#currentTreeId").val());
        zTree_Menu.selectNode(node)
    });
    //-->
</script>

<ul id="treeMenu" class="ztree"></ul>