<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="breadcrumb">
    <%--面包屑 start--%>
    <nav class="breadcrumb">
        <a class="crumbs" href="/">
            <i class="fa fa-home"></i>首页
        </a>
        <i class="fa fa-angle-right"></i>
        留言板
        <i class="fa fa-angle-right"></i>
        正文
    </nav>
    <%--面包屑 end--%>
</rapid:override>


<rapid:override name="left">
    <main id="main" class="site-main">
        <article class="post">
            <header class="entry-header">
                <h1 class="entry-title">
                    留言板
                </h1>
            </header>
            <div class="entry-content">
                <div class="single-content">
                    <div class="single-content">
                        <!--PC版-->
                        <div id="SOHUCS" sid="message"></div>
                        <script charset="utf-8" type="text/javascript" src="https://changyan.sohu.com/upload/changyan.js" ></script>
                        <script type="text/javascript">
                            window.changyan.api.config({
                                appid: 'cytcdBHan',
                                conf: 'prod_acc9eafcae7c468c116f87dfb853e677'
                            });
                        </script>
                    </div>
                </div>
                <br/><br/>
                <footer class="single-footer">
                    <ul class="single-meta">
                        <li class="r-hide">
                            <a href="javascript:pr()" title="侧边栏">
                                <i class="fa fa-caret-left"></i>
                                <i class="fa fa-caret-right"></i>
                            </a>
                        </li>
                    </ul>
                    <ul id="fontsize">
                        <li>A+</li>
                    </ul>
                </footer>
                <div class="clear"></div>
            </div>
        </article>
    </main>
</rapid:override>


<%--侧边栏 start--%>
<rapid:override name="right">
    <%@include file="../Public/part/sidebar-3.jsp" %>
</rapid:override>
<%--侧边栏 end--%>

<%@ include file="../Public/framework.jsp" %>