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
    <title>${options.optionSiteTitle} 登录</title>
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
        <form name="loginForm" id="loginForm" method="post">
            <p>
                <label>
                    用户名或电子邮件地址<br/>
                    <input type="text" name="username" id="username" class="input" value="<%=username%>" size="20" required/>
                </label>
            </p>
            <p>
                <label>
                    密码<br/>
                    <input type="password" name="password" id="password" class="input" value="<%=password%>" size="20" required/>
                </label>
            </p>
            <p class="remember">
                <label>
                    <input type="checkbox" name="rememberme" id="rememberme" value="1" checked />
                    记住密码
                </label>
            </p>
            <p class="submit">
                <input type="button" class="submit-btn" value="登录" />
            </p>
        </form>
        <p id="backto-blog">
            <a href="<%=basePath%>">
                返回到${options.optionSiteTitle}
            </a>
            <a href="register">
                注册
            </a>
        </p>
    </div>
    <div class="clear"></div>

<script src="js/jquery.min.js"></script>
<script src="js/login.js"></script>

</body>
</html>