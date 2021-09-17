<%--
    一般用于正文侧边栏：
    包括 搜索，热评文章，所有标签，随机文章 等小工具
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div id="sidebar" class="widget-area all-sidebar">
    <%--搜索框 start--%>
    <aside class="widget widget-search">
        <div class="searchbar">
            <form method="get" id="searchform1" action="search">
                <span>
                    <input type="text" name="keywords" id="s1" placeholder="输入搜索内容" />
                    <button type="submit" id="searchsubmit1">搜索</button>
                </span>
            </form>
        </div>
        <div class="clear"></div>
    </aside>
    <%--搜索框 end--%>

    <%--热评文章 start--%>
    <aside class="widget hot-comment">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>热评文章
        </h3>
        <div id="hot-comment-widget">
            <ul>
                <c:forEach items="${mostCommentArticleList}" var="m">
                    <li>
                        <a href="article/${m.articleId}" rel="bookmark"
                           title="(${m.articleCommentCount}条评论)">
                                ${m.articleTitle}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="clear"></div>
    </aside>
    <%--热评文章 end--%>

    <%--所有标签 start--%>
    <aside class="widget">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>所有标签
        </h3>
        <div class="tagcloud">
            <c:forEach items="${allTagList}" var="tag">
                <a href="tag/${tag.tagId}">
                        ${tag.tagName}
                </a>
            </c:forEach>
            <div class="clear"></div>
        </div>
    </aside>
    <%--所有标签 end--%>

    <%--随机文章 start--%>
        <aside id="random-post" class="widget random-post">
            <h3 class="widget-title">
                <i class="fa fa-bars"></i>随机文章
            </h3>
            <div id="random-post-widget">
                <ul>
                    <c:forEach items="${randomArticleList}" var="r">
                        <li>
                            <a href="article/${r.articleId}" rel="bookmark">
                                ${r.articleTitle}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </aside>
    <%--随机文章 end--%>
</div>