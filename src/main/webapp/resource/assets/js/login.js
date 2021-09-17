$(".login-button").click(function () {
    var user = $("#username").val();
    var password = $("#password").val();
    if (user == "") {
       alert("用户名不可为空");
    } else if (password == null) {
        alert("密码不可为空");
    } else {
        $.ajax({
            async: false, //同步 待请求完毕后再执行后面的代码
            type: "POST",
            url: 'loginVerify',
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            data: $("#loginForm").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    alert(data.msg);
                } else {
                    window.location.href="/admin";
                }
            },
            error: function() {
                alert("数据获取失败");
            }
        })
    }
})

function wp_attempt_focus(){
    setTimeout( function(){ try{
        d = document.getElementById('username');
        d.focus();
        d.select();
    } catch(e){}
    }, 200);
}
wp_attempt_focus();