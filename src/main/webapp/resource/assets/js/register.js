$(".submit-btn").click(function() {
    var user = $("#username").val();
    var email = $("#email").val();
    var nickname = $("#nickname").val();
    var password = $("#password").val();
    var confirmPass = $("#confirmPassword").val();
    if (user == "" || nickname == "" || email == "" || password == "" || confirmPass == "") {
        alert("请输入完整信息！")
    } else if (password != confirmPass) {
        alert("两次密码不一致!");
    } else {
        $.ajax({
            async: false,//同步 待请求完毕后再执行后面的代码
            type: "POST",
            url: "/registerSubmit",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: $("#registerForm").serialize(),
            dataType: "json",
            success: function(data) {
                if (data.code == 0) {
                    alert("注册成功");
                    window.location.href = "/login";
                } else {
                    alert(data.msg);
                }
            },
            error: function() {
                alert("数据获取失败");
            }
        })
    }
})