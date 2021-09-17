<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<%
    String basePathCopy = request.getScheme()+"://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>

<rapid:override name="description">
    <meta name="description" content="${tag.tagDescription}"/>
</rapid:override>
<rapid:override name="keywords">
    <meta name="keywords" content="${tag.tagName}"/>
</rapid:override>
<rapid:override name="title">
    <title>${tag.tagName}</title>
</rapid:override>


<%--面包屑导航 start--%>
<rapid:override name="breadcrumb">
    <nav class="breadcrumb">
        <a class="crumbs" href="<%=basePathCopy%>">
            <i class="fa fa-home"></i>首页
        </a>
        <i class="fa fa-angle-right"></i>
        <c:choose>
            <c:when test="${tag != null}">
                <a href="tag/${tag.tagId}">${tag.tagName}</a>
                <i class="fa fa-angle-right"></i> 文章
            </c:when>
            <c:otherwise>
                该标签不存在
            </c:otherwise>
        </c:choose>
    </nav>
</rapid:override>
<%--面包屑导航 end--%>


<%--左侧正文 start--%>
<rapid:override name="left">
    <main id="main">
        <c:choose>
            <c:when test="${pageInfo != null}">
                <%--文章列表 start--%>
                <c:choose>
                    <c:when test="${pageInfo.list.size() != 0}">
                        <c:forEach items="${pageInfo.list}" var="a">
                            <article class="type-post">
                                <figure class="thumbnail">
                                    <a href="article/${a.articleId}">
                                        <img width="280" height="210"
                                             src="img/thumbnail/random/img_${a.articleId%15}.jpg"
                                             class="post-image" alt="${a.articleTitle}">
                                    </a>
                                    <span class="cat">
                                <a href="category/${a.categoryList[a.categoryList.size()-1].categoryId}">
                                        ${a.categoryList[a.categoryList.size()-1].categoryName}
                                </a>
                            </span>
                                </figure>

                                <header class="entry-header">
                                    <h2 class="entry-title">
                                        <a href="article/${a.articleId}">
                                                ${a.articleTitle}
                                        </a>
                                    </h2>
                                </header>

                                <div class="entry-content">
                                    <div class="archive-content">
                                            ${a.articleSummary}...
                                    </div>
                                    <span class="title-l"></span>
                                    <span class="new-icon">
                                <jsp:useBean id="nowDate" class="java.util.Date"/>
                                <c:set var="interval"
                                       value="${nowDate.time - a.articleCreateTime.time}"/>
                                <fmt:formatNumber value="${interval/1000/60/60/24}" pattern="#"
                                                  var="days"/>
                                <c:if test="${days < 7}">New</c:if>
                            </span>
                                    <span class="entry-meta">
                                <span class="date">
                                    <fmt:formatDate value="${a.articleCreateTime}" pattern="yyyy年MM月dd日"/>
                                </span>
                                <span class="views">
                                    <i class="fa fa-eye"></i>
                                        ${a.articleViewCount} views
                                </span>
                                <span class="comment">&nbsp;&nbsp;
                                    <a href="article/${a.articleId}#comments">
                                        <i class="fa fa-comment-o"></i>
                                        <c:choose>
                                            <c:when test="${a.articleCommentCount == 0}">
                                                发表评论
                                            </c:when>
                                            <c:otherwise>
                                                ${a.articleCommentCount}
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </span>
                            </span>
                                    <div class="clear"></div>
                                </div>

                                <span class="entry-more">
                            <a href="article/${a.articleId}" rel="bookmark">
                                阅读全文
                            </a>
                        </span>
                            </article>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <section class="no-results not-found">
                            <div class="post">
                                <p>该分类目前还没有文章！</p>
                                <br/><br/><br/><br/><br/><br/><br/><br/><br/>
                            </div>
                        </section>
                    </c:otherwise>
                </c:choose>
                <%--文章列表 end--%>
            </c:when>
            <c:otherwise>
                <section class="no-results not-found">
                    <div class="post">
                        <p>该标签不存在文章！</p>
                        <br/><br/><br/><br/><br/><br/><br/><br/><br/>
                    </div>
                </section>
            </c:otherwise>
        </c:choose>
        <%@ include file="../Public/part/paging.jsp" %>
    </main>
</rapid:override>
<%--左侧正文 end--%>

<%@ include file="../Public/framework.jsp"%>
