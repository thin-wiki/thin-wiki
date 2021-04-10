const alert = layer;

$(document).ready(function () {
    $("#saveStorage").click(function () {
        const actionType = $("#action-type").val();
        const id = $("#storage-id").val();
        const name = $("#storage-name").val();
        const workType = $("#storage-work-type").val();
        const mainStorage = $("#main-storage").val();
        const writable = $("#storage-writable").is(":checked");
        const description = $("#storage-description").val();


        const reqData = {};
        reqData.name = name
        reqData.workType = workType;
        reqData.writable = writable;

        reqData.description = description;
        if (workType === 'BACKUP') {
            reqData.mainStorageId = mainStorage;
        }
        // if (writable === 'on') {
        //     reqData.writable = true;
        // } else {
        //     reqData.writable = false;
        // }

        console.log(reqData);

        let reqType;
        let reqUrl;
        if (actionType === 'new') {
            reqType = "POST";
            reqUrl = "/api/storage";
        } else {
            reqType = "PUT";
            reqUrl = "/api/storage/" + id;
        }

        $.ajax({
            type: reqType,
            url: reqUrl,
            contentType: "application/json",
            data: JSON.stringify(reqData),
            dataType: "json",
            success: function (data) {
                if (data.code !== 0) {
                    alert.msg(data.msg)
                    return
                }
                $('#editModal').modal('hide');
                alert.msg("提交成功");
                window.location.reload();
            },
            error: function (message) {
                alert.msg("提交失败" + JSON.stringify(message));
            }
        });


    });

    $('.deleteStorage').click(function () {
        const storageId = $(this).attr('storageId');
        const title = $(this).attr('title');
        layer.confirm('您确定要删除[' + title + ']存储么？', {
            btn: ['确实', '取消'] //按钮
        }, function () {
            $.ajax({
                type: "DELETE",
                url: "/api/storage/" + storageId,
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.code !== 0) {
                        alert.msg(data.msg);
                        return
                    }
                    alert.msg("删除成功");
                    window.location.reload();
                },
                error: function (message) {

                }
            });

        }, function () {

        });
    })

    $("#storage-work-type").change(function () {
        checkWorkType();
    });

    $("#saveLinkStorage").click(function () {
        const id = $("#selected-storage-id").val();
        const checkedStorage = $("input:radio[name=storage]:checked").val();
        if (!checkedStorage) {
            alert.msg("未选择存储");
            return
        }
        const arr = checkedStorage.split("-");

        const reqData = {};
        reqData.refStorageType = arr[0];
        reqData.refStorageId = arr[1];

        console.log(reqData);

        let reqUrl = '/api/storage/' + id + '/bind';

        $.ajax({
            type: 'PUT',
            url: reqUrl,
            contentType: "application/json",
            data: JSON.stringify(reqData),
            dataType: "json",
            success: function (data) {
                if (data.code !== 0) {
                    alert.msg(data.msg)
                    return
                }
                $('#linkModal').modal('hide');
                alert.msg("关联成功");
                window.location.reload();
            },
            error: function (message) {
                alert.msg("提交失败" + JSON.stringify(message));
            }
        });


    });

    $("#saveCopyStorage").click(function () {
        const id = $("#selected-copy-storage-id").val();
        const checkedStorage = $("input:radio[name=copy-storage]:checked").val();
        if (!checkedStorage) {
            alert.msg("未选择存储");
            return
        }
        const arr = checkedStorage.split("-");

        const reqData = {};
        reqData.refStorageType = arr[0];
        reqData.refStorageId = arr[1];

        $.ajax({
            type: 'PUT',
            url: '/api/storage/' + id + '/copy',
            contentType: "application/json",
            data: JSON.stringify(reqData),
            dataType: "json",
            success: function (data) {
                if (data.code !== 0) {
                    alert.msg(data.msg)
                    return
                }
                $('#linkModal').modal('hide');
                alert.msg("复制中");
                var myModalEl = document.getElementById('copyModal');
                var modal = bootstrap.Modal.getInstance(myModalEl);
                modal.hide();
            },
            error: function (message) {
                alert.msg("提交失败" + JSON.stringify(message));
            }
        });


    });
});

const checkWorkType = function () {
    const workType = $("#storage-work-type").val();
    if (workType === 'MAIN') {
        $('.main-storage-box').hide();
    } else {
        $('.main-storage-box').show();
    }
};

$('#editModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget);
    const modal = $(this)
    const actionType = button.data('type');
    modal.find('.modal-body #action-type').val(actionType);

    if (actionType === 'edit') {
        const id = button.data('id');
        const name = button.data('name');
        const workType = button.data('work-type');
        const writable = button.data('writable');
        const description = button.data('description');
        modal.find('.modal-title').text('编辑[' + name + ']');
        modal.find('.modal-body #storage-id').val(id);
        modal.find('.modal-body #storage-name').val(name);
        modal.find('.modal-body #storage-work-type').val(workType);
        modal.find('.modal-body #storage-writable').prop('checked', writable === 'on');
        modal.find('.modal-body #storage-description').val(description);
    } else {
        modal.find('.modal-title').text('新增存储')
        modal.find('.modal-body #storage-id').val('');
        modal.find('.modal-body #storage-name').val('');
        modal.find('.modal-body #storage-work-type').val('');
        modal.find('.modal-body #storage-writable').val('');
        modal.find('.modal-body #storage-description').val('');
    }
    checkWorkType();
})

$('#linkModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget);
    const modal = $(this)

    const id = button.data('id');
    const name = button.data('name');
    const refStorageId = button.data('ref-storage-id');
    const refStorageType = button.data('ref-storage-type');
    modal.find('.modal-title').text('关联[' + name + ']存储');
    modal.find('.modal-body #selected-storage-id').val(id);

    const value = refStorageType + '-' + refStorageId;

    $("input:radio[name=storage][value=" + value + "]").attr("checked", true);
})

$('#copyModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget);
    const modal = $(this)

    const id = button.data('id');
    const name = button.data('name');
    modal.find('.modal-title').text('复制[' + name + ']存储');
    modal.find('.modal-body #selected-copy-storage-id').val(id);
})
