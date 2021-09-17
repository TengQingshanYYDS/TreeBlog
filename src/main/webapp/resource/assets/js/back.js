// 删除评论
function deleteComment(id) {
    if (confirmDelete()) {
        $.ajax({
            async: false,
            type: "POST",
            url: "/admin/comment/delete/" + id,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "text",
            complete: function() {
                window.location.reload();
            }
        })
    }
}

function confirmDelete() {
    var msg = "您确定要删除吗？";
    return confirm(msg);
}

//添加用户检查用户名是否存在
function checkUserName() {
    var result;
    $.ajax({
        async: false,//同步，待请求完毕后再执行后面的代码
        type: "POST",
        url: '/admin/user/checkUserName',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {"username": $("#userName").val(), "id": $("#userId").val()},
        dataType: "json",
        success: function (data) {
            //用户名存在
            if(data.code==1) {
                $("#userNameTips").html(data.msg);
                result=1;
            }
            //用户名不存在
            if(data.code==0) {
                $("#userNameTips").html(data.msg);
                result=0;
            }
        },
        error: function () {
            // alert("数据获取失败")
        }
    })
    return result;
}

//添加用户检查电子邮箱是否存在
function checkUserEmail() {
    var result;
    $.ajax({
        async: false,//同步，待请求完毕后再执行后面的代码
        type: "POST",
        url: '/admin/user/checkUserEmail',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {"email": $("#userEmail").val(), "id": $("#userId").val()},
        dataType: "json",
        success: function (data) {
            //用户名存在
            if(data.code==1) {
                $("#userEmailTips").html(data.msg);
                result=1;
            }
            //用户名不存在
            if(data.code==0) {
                $("#userEmailTips").html(data.msg);
                result=0;
            }
        },
        error: function () {
            //alert("数据获取失败")
        }
    })
    return result;
}
