<%--
    一般用于首页侧边栏：
    包括 关于本站，网站概况，热评文章，所有标签，随机文章 等小工具

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div id="sidebar" class="widget-area all-sidebar">
    <%--关于本站start--%>
    <aside class="widget about">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>关于本站
        </h3>
        <div id="feed_widget">
            <div class="feed_about">
                <div class="about-main">
                    <div class="about-img">
                        <img src="${options.optionAboutsiteAvatar}" alt="QR code" />
                    </div>
                    <div class="about-name">
                        ${options.optionAboutsiteTitle}
                    </div>
                    <div class="about-the">
                        ${options.optionAboutsiteContent}
                    </div>
                </div>
                <div class="clear"></div>
                <ul>
                    <li class="weixin">
                        <a title="微信" id="weixin_btn" rel="external nofollow">
                            <i class="fa fa-weixin"></i>
                            <div id="weixin_code" class="hide">
                                <img src="${options.optionAboutsiteWechat}" />
                            </div>
                        </a>
                    </li>
                    <li class="tqq">
                        <a target="_blank" rel="external nofollow"
                           href="http://wpa.qq.com/msgrd?V=3&amp;uin=${options.optionAboutsiteQq}&amp;Site=QQ&amp;Menu=yes"
                           title="QQ在线">
                            <i class="fa fa-qq"></i>
                        </a>
                    </li>
                    <li class="tsina">
                        <a title=""
                           href="http://weibo.com/${options.optionAboutsiteWeibo}"
                           target="_blank" rel="external nofollow">
                            <i class="fa fa-weibo"></i>
                        </a>
                    </li>
                    <li class="feed">
                        <a title="" href="https://github.com/${options.optionAboutsiteGithub}" target="_blank"
                           rel="external nofollow">
                            <i class="fa fa-github"></i>
                        </a>
                    </li>
                </ul>
                <div class="about-inf">
                    <span class="about-pn">文章 ${siteBasicStatistics[0]}</span>
                    <span class="about-cn">留言 ${siteBasicStatistics[1]}</span>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </aside>
    <%--关于本站end--%>

    <%--网站概况 start--%>
    <aside class="widget">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>网站概况
        </h3>
        <div class="widget-text">
            <ul class="site-profile">
                <li><i class="fa fa-file-o"></i> 文章总数：${siteBasicStatistics[0]} 篇</li>
                <li><i class="fa fa-commenting-o"></i> 留言数量：${siteBasicStatistics[1]} 条</li>
                <li><i class="fa fa-folder-o"></i> 分类数量：${siteBasicStatistics[2]} 个</li>
                <li><i class="fa fa-tags"></i> 标签总数：${siteBasicStatistics[3]} 个</li>
                <li><i class="fa fa-link"></i> 链接数量：${siteBasicStatistics[4]} 个</li>
                <li><i class="fa fa-eye"></i> 浏览总量：${siteBasicStatistics[5]} 次</li>
                <li><i class="fa fa-pencil-square-o"></i> 最后更新：
                    <fmt:formatDate value="${lastUpdateArticle.articleUpdateTime}" pattern="yyyy年MM月dd日"/>
                </li>
            </ul>
        </div>
    </aside>
    <%--网站概况 end--%>

    <%--所有标签 start--%>
    <aside class="widget">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>所有标签
        </h3>
        <div class="tagcloud">
            <c:forEach items="${allTagList}" var="tag">
                <a href="/${tag.tagId}">
                    ${tag.tagName}
                </a>
            </c:forEach>
            <div class="clear"></div>
        </div>
    </aside>
    <%--所有标签 end--%>

    <%--近期评论 start--%>
    <aside class="widget">
        <h3 class="widget-title">
            <i class="fa fa-bars"></i>近期评论
        </h3>
        <div class="message-widget">
            <ul>
                <c:forEach items="${recentCommentList}" var="r">
                    <li>
                        <a href="${r.commentArticleId}/#anchor-comment-${r.commentId}" rel="external nofollow">
                            <span class="comment-author">
                                ${r.commentAuthorName}
                            </span>
                            ${r.commentContent}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </aside>
    <%--近期评论 end--%>


</div>