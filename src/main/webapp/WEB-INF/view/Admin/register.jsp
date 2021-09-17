<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()+"://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>" />
    <meta name='robots' content='noindex,follow' />
    <meta name="viewport" content="width=device-width" />
    <title>${options.optionSiteTitle} 注册</title>
    <link rel="shortcut icon" href="img/logo.png">
    <link rel="stylesheet" href="css/loginRegister.css">
    <link rel="stylesheet" href="plugin/font-awesome/css/font-awesome.min.css">

</head>
<body>
<div id="login">
    <%
        String username = "";
        String password = "";
        //获得当前站点所有cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                username = cookie.getValue();
            } else if ("password".equals(cookie.getName())) {
                password = cookie.getValue();
            }
        }
    %>
    <form name="registerForm" id="registerForm" method="post">
        <p>
            <label for="username">用户名<br/>
                <input type="text" name="username" id="username" class="input"
                       size="20" required autocomplete="off" /></label>
        </p>
        <p>
            <label for="nickname">昵称<br/>
                <input type="text" name="nickname" id="nickname" class="input"
                       size="20" required autocomplete="off"/></label>
        </p>
        <p>
            <label for="email">电子邮箱<br/>
                <input type="text" name="email" class="input" id="email"
                       size="20" required autocomplete="off"/></label>
        </p>
        <p>
            <label for="password">登录密码<br/>
                <input type="password" name="password" id="password" class="input"
                       size="20" required autocomplete="off"/>
            </label>
        </p>
        <p>
            <label for="confirmPassword">确认密码<br/>
                <input type="password" name="confirmPassword" id="confirmPassword" class="input"
                       size="20" required autocomplete="off"/>
            </label>
        </p>
        <p class="submit">
            <input type="button" class="submit-btn" class="button button-primary button-large"
                   value="注册"/>
        </p>
    </form>
    <p id="backto-blog">
        <a href="<%=basePath%>">
            返回到${options.optionSiteTitle}
        </a>
        <a href="/login">
            登录
        </a>
    </p>
</div>
<div class="clear"></div>

<script src="js/jquery.min.js"></script>
<script src="js/register.js"></script>


</body>
</html>