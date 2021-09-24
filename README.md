## TreeBlog

来源于[ForestBlog](https://github.com/saysky/ForestBlog)，是一个SSM博客。

本项目(TreeBlog)对其进行了部分修改，删除了大量冗余代码，并且优化了逻辑。

## 学习ForestBlog源码的记录

# pom.xml

commons-logging

Jakarta Commons-logging（JCL）是apache最早提供的日志的门面接口。提供简单的日志实现以及日志解耦功能。

https://www.liaoxuefeng.com/wiki/1252599548343744/1264738932870688

虽然引用了，但实际没有用到。

# 首页：

src/main/webapp/WEB-INF/view/Home/index.jsp

涉及知识点：

**el表示式**

---

**jstl**

```
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
```

---

**rapid-framework**

```
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
```

学习： https://tengqingshan.blog.csdn.net/article/details/114519570

---

# framework.jsp

src/main/webapp/WEB-INF/view/Home/Public/framework.jsp

涉及知识点：



js : jquery layui.all.js



superfish导航菜单插件  手机端页面有用到。

# header.jsp

src/main/webapp/WEB-INF/view/Home/Public/part/header.jsp

---



---

# paging.jsp

src/main/webapp/WEB-INF/view/Home/Public/part/paging.jsp

分页

# sidebar-2.jsp

首页侧边栏

# 配置文件

## web.xml

RequestContextListener，https://blog.csdn.net/yanweju/article/details/70622313

---

使用Rest风格的URI，将页面普通的post请求转为指定的delete或者put请求

https://www.cnblogs.com/dream-saddle/p/9349224.html

https://blog.csdn.net/qq_33404395/article/details/80096357

---

DruidWebStatFilter，对站点的URL进行统计，https://blog.csdn.net/u011831754/article/details/71631622

---



## spring-mvc.xml

是spring-mvc容器配置文件

---

annotation-driven

<mvc:annotation-driven /> 会自动注册DefaultAnnotationHandlerMapping与AnnotationMethodHandlerAdapter 两个bean,是spring MVC为@Controllers分发请求所必须的，即解决了@Controller注解使用的前提配置。

---

文件上传

---

静态资源映射

---

拦截器 mvc:interceptor exclude

---

## spring-mybatis.xml

是spirng容器配置文件

## myBatis-config.xml

useGeneratedKeys  允许JDBC支持自动生成主键

在settings元素中设置的全局useGeneratedKeys参数对于xml映射器无效。如果希望在xml映射器中执行添加记录之后返回主键ID，则必须在xml映射器中明确设置useGeneratedKeys参数值为true。

在这里设置没有必要，后面会在mapper文件中设置

---

mapUnderscoreToCamelCase  开启自动驼峰命名规则

即从经典数据库列名 A_COLUMN 到 经典Java 属性名 aColumn 的类似映射

---

useGeneratedKeys

---

使用Rest风格的URI





# ArticleService

PageHelper PageInfo

---



# ArticleMapper.java

 @Mapper作用  https://tengqingshan.blog.csdn.net/article/details/114582315

# ArticleMapper.xml

因为在mybatis主配置文件中配置了`mapUnderscoreToCamelCase`，所以不需要resultMap

---

mybatis模糊查询  

```
 LIKE concat('%',#{keywords},'%')
```

---

MySQL 子查询in改为join，优化效率

```
select article.* from article WHERE article.article_id IN (
    SELECT article_category_ref.article_id FROM article_category_ref
    WHERE article_category_ref.category_id = 10
)

select article.* from article join(
    SELECT * FROM article_category_ref
)t1 on article.article_id = t1.article_id and t1.category_id=10
```

---

使用`<where>`标签就可以不用再写`where 1=1`了

---

# enums/ArticleStatus.java

lombok enum

---

rollbackFor = Exception.class

@RestController

jsp include和import区别

rapid-framework

# enums/Category.java

lombok 多个构造器  https://tengqingshan.blog.csdn.net/article/details/114597280

---

java default方法

---

# mapper/ArticleCategoryRefMapper.java

FROM多个表和join

俩的查询耗时都一样，官方推荐 第二种写法(阅读好，本地化好)

---

# mapper/ArticleMapper.xml

`<where>` 

AND放在 `<if>` 内容的前面

# Index.jsp

HTML中的role属性 https://blog.csdn.net/annip/article/details/53455226

---

fmt:formatNumber pattern="#" 转成整数

---

a rel="bookmark"

---

jsp <%= 和${}

---

font-size: 62.5%;  原理

---

# framework.jsp

用sticky.js实现头部导航栏固定 - 罗毅豪 - 博客园  https://www.cnblogs.com/luoyihao/p/11732645.html

---

/plugin/font-awesome/css/font-awesome.min.css  图标字体库



# MenuMapper.xml

```
<insert id="insert" parameterType="com.liuyanzhao.ssm.blog.entity.Menu" useGeneratedKeys="true" keyProperty="menuId">
```

useGeneratedKeys

设置useGeneratedKeys参数值为true，在执行添加记录之后可以获取到数据库自动生成的主键ID。

useGeneratedKeys这个只在insert语句中有效，正常情况下useGeneratedKeys默认为false。当useGeneratedKeys为true时，如果插入的表以自增列为主键时，将会把该自增id返回。数据插入之后可以直接通过对象获取自增的id

---

mybatis 动态sql set

*set* 元素可以用于动态包含需要更新的列，忽略其它不更新的列。

例如：

```
<update id="updateAuthorIfNecessary">
  update Author
    <set>
      <if test="username != null">username=#{username},</if>
      <if test="password != null">password=#{password},</if>
      <if test="email != null">email=#{email},</if>
      <if test="bio != null">bio=#{bio}</if>
    </set>
  where id=#{id}
</update>
```

*set* 元素会动态地在行首插入 SET 关键字，并会删掉额外的逗号（这些逗号是在使用条件语句给列赋值时引入的）

---

# HomeResourceInterceptor.java

在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中



包括菜单、目录、网站logo 等等...

# OptionsServiceImpl.java

spring cache 学习 —— @Cacheable 使用详解 - 水煮鱼它不香吗 - 博客园 https://www.cnblogs.com/coding-one/p/12401630.html

spring cache 学习——@CacheEvict 使用详解 - 水煮鱼它不香吗 - 博客园 https://www.cnblogs.com/coding-one/p/12408631.html

spring cache 学习——@CachePut 使用详解 - 水煮鱼它不香吗 - 博客园 https://www.cnblogs.com/coding-one/p/12403801.html

---

# CategoryServiceImpl.java

`@Transactional(rollbackFor = Exception.class)`

如果类加了这个注解,那么这个类里面的方法抛出异常,就会回滚,数据库里面的数据也会回滚

---

在aop配置事务控制或注解式控制事务中，`try...catch...`会使事务失效，可在catch中抛出运行时异常`throw new RuntimeException(e)`

或者手动回滚`TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();`使得事务生效，异常回滚。

---

所以这个文件中`@Transactional(rollbackFor = Exception.class)`和`setRollbackOnly()`不必同时使用。

---

# NoticeMapper.xml

关于mybatis中基本类型条件判断问题_CCLOVETT的专栏-CSDN博客 https://blog.csdn.net/cclovett/article/details/12855505

# Script.js

文字滚动  textSlider.js

# sidebar-2.jsp

ul overflow:hidden 父元素高度不随子元素变化 https://tengqingshan.blog.csdn.net/article/details/114747760

---

`<div class="clear"></div>`

＜div class=“clear“＞＜/div＞_滕青山博客-CSDN博客
https://tengqingshan.blog.csdn.net/article/details/114648945

---

设置了50% 不能并排显示_滕青山博客-CSDN博客 https://tengqingshan.blog.csdn.net/article/details/114751116

---

固定侧边栏 sticky.js

---

# articleDetail.jsp

```
word-wrap: break-word;

word-break: break-all;
```

word-wrap 是用来决定允不允许单词内断句的，如果不允许的话长单词就会溢出。最重要的一点是它还是会首先尝试挪到下一行，看看下一行的宽度够不够，不够的话就进行单词内的断句。

而word-break:break-all则更变态，因为它断句的方式非常粗暴，它不会尝试把长单词挪到下一行，而是直接进行单词内的断句。这也可以解释为什么说它的作用是决定用什么方式来断句的，那就是——用了word-break:break-all，就等于使用粗暴方式来断句了。总之一句话，如果您想更节省空间，那就用word-break:break-all就对了！

word-wrap:break-word与word-break:break-all共同点是都能把长单词强行断句，不同点是word-wrap:break-word会首先起一个新行来放置长单词，新的行还是放不下这个长单词则会对长单词进行强制断句；而word-break:break-all则不会把长单词放在一个新行里，当这一行放不下的时候就直接强制断句了。

---

头像avatar上下抖动：

@keyframes 

```
@keyframes avatar {
    16.65% {
        -webkit-transform: translateY(8px);
        transform: translateY(8px);
    }

    33.3% {
        -webkit-transform: translateY(-6px);
        transform: translateY(-6px);
    }

    49.95% {
        -webkit-transform: translateY(4px);
        transform: translateY(4px);
    }

    66.6% {
        -webkit-transform: translateY(-2px);
        transform: translateY(-2px);
    }

    83.25% {
        -webkit-transform: translateY(1px);
        transform: translateY(1px);
    }

    100% {
        -webkit-transform: translateY(0);
        transform: translateY(0);
    }
}
```

animation-duration

```
.comment-body:hover .avatar {
    -webkit-animation-name: avatar;
    animation-name: avatar;
    -webkit-animation-duration: 1s;
    animation-duration: 1s;
    -webkit-animation-timing-function: ease-in-out;
    animation-timing-function: ease-in-out;
    -webkit-animation-iteration-count: 1;
    animation-iteration-count: 1;
}
```

---



layui代码样式

```
<script type="text/javascript">
    layui.code({
        elem: 'pre',
        about: false
    });
</script>
```

---

# ------后台 分割线------

# login.jsp

```label for```

在点击label时,会自动将焦点移动到绑定的元素

---

# AdminController

当方法上加`@ResponseBody`注解的时候，返回的数据的`context-type'`一定是`application/json;`类型的，即使指定了`@requestMapping`中的`produces`属性的值也无效。

返回中文乱码解决

```java
@RequestMapping(value = "testPersonalValidtor.do",produces = "application/json;charset=utf-8")
```

---

# Admin/Article/index.jsp

```
<a><cite>文章列表</cite></a>
```

让a标签不是蓝色的方法： 加上cite标签

# Admin/Article/insert.jsp

`layui-hide` 隐藏元素

---

# Admin/User/edit.jsp

```
<div class="layui-form-mid layui-word-aux">辅助文字</div>
```

---

# Admin/User/editProfile.jsp

```
<button id="sub">提交</button>
<script>
    layui.use(['jquery'], function(){
      var $ = jQuery = layui.$;
      // 你可以在下面的 js 代码中使用你熟悉的 $, jQuery
        $('#sub').click(function() {
            // todo
        });
    });
</script>
```

在 layui 使用 jquery 选择器

---

# Admin/UploadFileController.java

```
file.getOriginalFilename()
```

getOriginalFilename()获取的是真正的文件名，而getName()方法获取的并不是真正的文件名，没有后缀

---

创建年月日文件夹

```
Calendar now = Calendar.getInstance();
System.out.println("年: " + now.get(Calendar.YEAR));
System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
System.out.println("分: " + now.get(Calendar.MINUTE));
System.out.println("秒: " + now.get(Calendar.SECOND));
System.out.println("当前时间毫秒数：" + now.getTimeInMillis());
System.out.println(now.getTime());
```

---

`File.separator`

在 windows 中 文件文件分隔符 用 ' \ ' 或者 ' / ' 都可以

但是在 Linux 中，是不识别 ' \ '  的，而 File.separator 是系统默认的文件分隔符号，在 UNIX 系统上，此字段的值为 ' / '

所以用 File.separator 保证了在任何系统下不会出错。

---

mkdirs和mkdir

java.io.File.mkdir()：只能创建一级目录，且父目录必须存在，否则无法成功创建一个目录。

java.io.File.mkdirs()：可以创建多级目录，父目录不一定存在。

---

