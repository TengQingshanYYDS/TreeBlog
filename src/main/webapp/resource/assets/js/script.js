//文章浏览器量+1
function increaseViewCount(articleId){
    if($.cookie("viewId") != articleId || $.cookie("viewId") == null){
        $.ajax({
            async: false,
            type: "POST",
            url: "article/view/" + articleId,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                console.log(data);
                $(".articleViewCount").html(data);
                $.cookie(
                    "viewId",
                    articleId,
                    {
                        "path": "/",
                    }
                );
            },
            error: function () {
                alert("获取数据出错！");
            },
        });
    }
}

// 文字滚动
(function ($) {
    $.fn.textSlider = function (settings) {
        settings = jQuery.extend({
                speed: "normal",
                line: 2,
                timer: 1000
            },
            settings);
        return this.each(function () {
            $.fn.textSlider.scllor($(this), settings)
        })
    };
    $.fn.textSlider.scllor = function ($this, settings) {
        var ul = $("ul:eq(0)", $this);
        var timerID;
        var li = ul.children();
        var _btnUp = $(".up:eq(0)", $this);
        var _btnDown = $(".down:eq(0)", $this);
        var liHight = $(li[0]).height();
        var upHeight = 0 - settings.line * liHight;
        var scrollUp = function () {
            _btnUp.unbind("click", scrollUp);
            ul.animate({
                    marginTop: upHeight
                },
                settings.speed,
                function () {
                    for (i = 0; i < settings.line; i++) {
                        ul.find("li:first").appendTo(ul)
                    }
                    ul.css({
                        marginTop: 0
                    });
                    _btnUp.bind("click", scrollUp)
                })
        };
        var scrollDown = function () {
            _btnDown.unbind("click", scrollDown);
            ul.css({
                marginTop: upHeight
            });
            for (i = 0; i < settings.line; i++) {
                ul.find("li:last").prependTo(ul)
            }
            ul.animate({
                    marginTop: 0
                },
                settings.speed,
                function () {
                    _btnDown.bind("click", scrollDown)
                })
        };
        var autoPlay = function () {
            timerID = window.setInterval(scrollUp, settings.timer)
        };
        var autoStop = function () {
            window.clearInterval(timerID)
        };
        ul.hover(autoStop, autoPlay).mouseout();
        _btnUp.css("cursor", "pointer").click(scrollUp);
        _btnUp.hover(autoStop, autoPlay);
        _btnDown.css("cursor", "pointer").click(scrollDown);
        _btnDown.hover(autoStop, autoPlay)
    }
})(jQuery);

$("#scrolldiv").textSlider({line: 1, speed: 300, timer: 6000});