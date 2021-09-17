<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>


<rapid:override name="content">

    <blockquote class="layui-elem-quote">
         <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/user">用户列表</a>
              <a><cite>个人信息</cite></a>
        </span>
    </blockquote>
    <br><br>
    <form class="layui-form" action="/admin/profile/save" id="userForm"
          method="post">
        <div class="layui-form-item">
            <a class="layui-btn layui-btn-primary"  href="/admin/profile/edit">编辑</a>
            <label class="layui-form-label">头像</label>
            <div class="layui-input-inline">
                <img class="layui-upload-img" src="${user.userAvatar}" width="100"
                             height="100">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名 </label>
            <div class="layui-input-inline">
                <input type="text" value="${user.userName}"  id="userName" required
                       autocomplete="off" class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码 </label>
            <div class="layui-input-inline">
                <input type="password" value="${user.userPass}" id="userPass" required
                       autocomplete="off" class="layui-input" min="3" max="20" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">昵称 </label>
            <div class="layui-input-inline">
                <input type="text"  value="${user.userNickname}" required
                       placeholder="" autocomplete="off" min="2" max="10"
                       class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">Email </label>
            <div class="layui-input-inline">
                <input type="email"  value="${user.userEmail}" id="userEmail" required
                       class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">URL </label>
            <div class="layui-input-inline">
                <input type="url"  value="${user.userUrl}" placeholder="" autocomplete="off"
                       class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">注册时间 </label>
            <div class="layui-input-inline">
                <input type="text"  value="<fmt:formatDate value="${user.userRegisterTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
" placeholder="" autocomplete="off"
                       class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">最后登录时间 </label>
            <div class="layui-input-inline">
                <input type="text"  value='<fmt:formatDate value="${user.userLastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
' placeholder="" autocomplete="off"
                       class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">最后登录IP </label>
            <div class="layui-input-inline">
                <input type="text"  value="${user.userLastLoginIp}" placeholder="" autocomplete="off"
                       class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态 </label>
            <div class="layui-input-inline">
                <input type="text"  value='<c:choose>
                    <c:when test="${user.userStatus==0}">禁用
                    </c:when>
                    <c:otherwise>正常
                    </c:otherwise>
                </c:choose>' placeholder="" autocomplete="off"
                       class="layui-input" disabled>
            </div>
        </div>
    </form>

</rapid:override>


<%@ include file="../Public/framework.jsp" %>
