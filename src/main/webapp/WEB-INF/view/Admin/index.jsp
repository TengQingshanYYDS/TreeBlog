<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="content">
    <div class="layui-container">
        <div class="layui-row">
            <div class="layui-col-md6">
                <div id="dashboard-activity" class="postbox">
                    <div class="inside">
                        <div id="activity-widget">
                            <div id="published-posts" class="activity-block">
                                <h3>最近发布</h3>
                                <br>
                                <ul>
                                    <c:forEach items="${articleList}" var="a">
                                        <li>
                                            <span>
                                                <fmt:formatDate value="${a.articleCreateTime}"
                                                    pattern="HH:mm MM月dd日" />
                                            </span>
                                            <a href="article/${a.articleId}"
                                               target="_blank">
                                                ${a.articleTitle}
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <br>
                            <div id="latest-comments" class="activity-block">
                                <h3>近期评论</h3>
                                <ul>
                                    <c:forEach items="${commentList}" var="c">
                                        <li>
                                            <img src="${c.commentAuthorAvatar}"/>
                                            <div class="dashboard-comment-wrap">
                                                <p class="comment-meta">
                                                    由
                                                    <cite class="comment-author">
                                                        <a target="_blank" href="${c.commentAuthorUrl}"
                                                           rel="external nofollow">
                                                            ${c.commentAuthorName}
                                                        </a>
                                                    </cite>
                                                    发表在
                                                    《<a href="article/${c.commentArticleId}">
                                                    ${c.article.articleTitle}
                                                </a>》
                                                </p>

                                                <blockquote>
                                                    <p>
                                                        ${c.commentContent}
                                                    </p>
                                                </blockquote>

                                                <p class="row-actions">
                                                    <span>
                                                        |
                                                        <a href="admin/comment/reply/${c.commentId}">
                                                            回复
                                                        </a>
                                                    </span>
                                                    <span>
                                                        |
                                                        <a href="admin/comment/edit/${c.commentId}">
                                                            编辑
                                                        </a>
                                                    </span>
                                                    <span>
                                                        |
                                                        <a href="javascript:void(0)"
                                                           onclick="deleteComment(${c.commentId})">
                                                            删除
                                                        </a>
                                                    </span>
                                                </p>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md6">
                <div id="dashboard-quick-press" class="postbox">
                    <div class="inside">
                        <form name="post" method="post" id="insertDraftForm"
                              class="draft-form" action="admin/article/insertDraftForm">
                            <div class="layui-form-item">
                                    <input type="text" name="articleTitle" id="articleTitle" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-item layui-form-text">
                                    <textarea name="articleContent" placeholder="请输入内容" id="articleContent" class="layui-textarea" required></textarea>
                            </div>
                            <input type="hidden" name="articleStatus" value="0">
                            <div class="layui-form-item">
                                <button class="layui-btn layui-btn-small" lay-submit onclick="insertDraft()">保存草稿</button>
                                <button type="reset" class="layui-btn layui-btn-small layui-btn-primary">重置</button>
                            </div>
                        </form>

                        <div class="drafts">
                            <h2>草稿</h2>
                            <p>
                                <a href="admin/article">查看所有</a>
                            </p>
                            <ul>
                                <c:forEach items="${articleList}" var="a">
                                    <c:if test="${a.articleStatus == 0}">
                                        <li>
                                            <div class="draft-title">
                                                <a href="admin/article/edit/${a.articleId}">
                                                    ${a.articleTitle}
                                                </a>
                                                <time>
                                                    <fmt:formatDate value="${a.articleCreateTime}" pattern="yyyy年MM月dd日" />
                                                </time>
                                            </div>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</rapid:override>

<%@ include file="Public/framework.jsp" %>