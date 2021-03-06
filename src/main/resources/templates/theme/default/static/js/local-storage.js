const alert = layer;

$(document).ready(function(){
    $("#saveLocalStorage").click(function(){
        const actionType = $("#action-type").val();
        const id = $("#storage-id").val();
        const name = $("#storage-name").val();
        const path = $("#storage-path").val();
        const description = $("#storage-description").val();

        const reqData = {};
        reqData.name = name
        reqData.basePath = path;
        reqData.description = description;

        let reqType;
        let reqUrl;
        if (actionType === 'new') {
            reqType = "POST";
            reqUrl = "/api/storage/local";
        } else {
            reqType = "PUT";
            reqUrl = "/api/storage/local/" + id;
        }

        $.ajax({
            type: reqType,
            url: reqUrl,
            contentType: "application/json",
            data: JSON.stringify(reqData),
            dataType: "json",
            success: function (data) {
                if (data.code !== 0){
                    alert.msg(data.msg)
                    return
                }
                alert.msg("提交成功");
                window.location.reload();
            },
            error: function (message) {
                alert.msg("提交失败" + JSON.stringify(message));
            }
        });

        $('#editModal').modal('hide');
    });

    $('.deleteStorage').click(function(){
        const storageId = $(this).attr('storageId');
        const title = $(this).attr('title');
        layer.confirm('您确定要删除['+title+']存储么？', {
            btn: ['确实','取消'] //按钮
        }, function(){
            $.ajax({
                type: "DELETE",
                url: "/api/storage/local/"+storageId,
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.code !== 0){
                        alert.msg(data.msg);
                        return
                    }
                    alert.msg("删除成功");
                    window.location.reload();
                },
                error: function (message) {

                }
            });

        }, function(){

        });
    })
});

$('#editModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget);
    const modal = $(this)
    const actionType = button.data('type');
    modal.find('.modal-body #action-type').val(actionType);

    if (actionType === 'edit'){
        const id = button.data('id');
        const name = button.data('name');
        const path = button.data('path');
        const description = button.data('description');
        modal.find('.modal-title').text('编辑[' + name + ']');
        modal.find('.modal-body #storage-id').val(id);
        modal.find('.modal-body #storage-name').val(name);
        modal.find('.modal-body #storage-path').val(path);
        modal.find('.modal-body #storage-description').val(description);
    }else{
        modal.find('.modal-title').text('新增本地存储')
        modal.find('.modal-body #storage-id').val('');
        modal.find('.modal-body #storage-name').val('');
        modal.find('.modal-body #storage-path').val('');
        modal.find('.modal-body #storage-description').val('');
    }
})
