
 	/*
    var jqformScript = "http://cdn.jiajia1.com/Scripts/Base/jquery.form.js?v=V1.2.9";
    var unityregisterScript = "http://cdn.jiajia1.com/Scripts/Register/unityregister.js?v=V1.2.9";
    var unityloginScript = "http://cdn.jiajia1.com/Scripts/Register/unitylogin.js?v=V1.2.9";
    var uploadifyScript ="http://cdn.jiajia1.com/Scripts/jquery.uploadify/jquery.uploadify.min.js?v=V1.2.9";
    var unityScript ="http://cdn.jiajia1.com/Scripts/Unity/unity.js?v=V1.2.9";
    var jiathisScript = "http://v2.jiathis.com/code/jia.js";
    window.onload = function() {
        loadScript(jqformScript, function () {
            loadScript(unityregisterScript, function () {
                loadScript(unityloginScript, function () {
                    loadScript(uploadifyScript, function () {
                        loadScript(unityScript, function () {
                            loadScript(jiathisScript, function () { });
                        });
                    });
                });
            });
        });
    };
   	*/
    //jbox 提醒配置
    jBoxConfig.tipDefaults = {
        timeout: 1000 /* 提示显示多少毫秒后自动关闭,必须是大于0的整数 */
    };
    $.jBox.setDefaults(jBoxConfig);
    if ("-1" != "-1") {
        CallCricleProcessBar();
    }
    //CallCricleProcessBar();
    //检测浏览器
    // var jqcbrowser = jQuery.checkBrowserPlugin;
    // console.log(navigator.userAgent.toLowerCase());


    $(function () {
        var $recLi = $('.space_img');
        $recLi.hover(function () {
            if (!$(this).children(".mask").is(":animated")) {
                $(this).children(".mask").slideDown();
            }
        }, function () {
            $(this).children(".mask").slideUp();
        });
        var deConcept = $('.concept');
        var proList = $('.list');
        deConcept.click(function () {
            $(".pro_list").hide();
            $(".design").show();
            $(this).removeClass('con_sel').siblings(".list").removeClass('list_sel');
        });
        proList.click(function () {
            $(".pro_list").show();
            $(".design").hide();
            $(this).addClass('list_sel').siblings(".concept").addClass('con_sel');
        });
        /********右侧浮层*********/
        $(".right_item ul li").hover(function () {
            $(this).children("span").show();
            if (!($(this).children("span").is(":animated"))) {
                $(this).children("span").animate({ "marginTop": "-3px" }, 200).animate({ "marginTop": "3px" }, 200);
            }
            if ($(this).index() == "0" || $(this).index() == "1") {
                $(this).css("background", "#F4E069");
                if ($(this).index() == "0") {
                    $(this).children(".history_icon").addClass("history_icon_cur").text("浏览历史");
                }

            } else {
                $(this).css("background", "#B8C5D3");
            }

        }, function () {
            $(this).css("background", "none");
            $(this).children("span").hide();

            if ($(this).index() == "0") {
                $(this).children(".history_icon").removeClass("history_icon_cur").text("");
            }
        });
        $(".history_icon").click(function () {
            $("div.browse_history").slideToggle(500);
            if ($("div.loading-box:visible")) {
                $("div.loading-box").hide();
            }
        });
        $(".loading_icon").click(function () {
            $("div.loading-box").slideToggle(500);

            if ($("div.browse_history:visible")) {
                $("div.browse_history").hide();
            }
        });
        $("div.browse_history").mouseleave(function () {
            $(this).fadeOut(100);
        });
        if ($("div.unity_suspension").length >= 1) {
            //异步获取方案浏览数据
            GetSchemeHistory();
        }
        //unity设计方案推荐效果
        var recUl = $(".recommend_list");
        var recLi = recUl.children("li");
        var arrow = $(".recommend_arrow");
        var recIndex = 0;
        var recDocPar = $(".recommend_round");
        var appLi = "";
        var thisLiLength = Math.ceil(recDocPar.siblings(".recommend_box").find("li").length / 4);

        recDocPar.children().remove();//每次插入可点击圆点前清空desDoc里面的子节点，以防叠加

        //动态插入可点击的小圆点（个数根据图片的个数而定）
        recDocPar.append("<ul></ul>");
        for (var i = 0; i < thisLiLength; i++) {
            appLi += "<li>" + "i" + "</li>";
        }
        recDocPar.children().append(appLi);

        var recDoc = $(".recommend_round ul li").text("");
        recDoc.first().addClass("hover");


        //点击圆点
        recDoc.click(function () {
            recIndex = $(this).index();

            var leftWidth = recLi.outerWidth(true) * 4 * recIndex;
            if (!recUl.is(":animated")) {
                $(this).addClass("hover").siblings().removeClass("hover");
                recUl.animate({ left: -leftWidth });
            }
        });

        //点击左右箭头
        arrow.children("a").click(function () {
            if ($(this).hasClass("arr_left")) {
                recIndex--;
                if (recIndex < 1) {
                    recIndex = 0;
                }

            } else if ($(this).hasClass("arr_right")) {
                recIndex++;
                if (recIndex == recDoc.length) {
                    recIndex = recDoc.length - 1;
                }
            }

            recDoc.eq(recIndex).addClass("hover").siblings().removeClass("hover");

            var leftWidth = recLi.outerWidth(true) * 4 * recIndex;
            if (!recUl.is(":animated")) {
                $(this).addClass("hover").siblings().removeClass("hover");
                recUl.animate({ left: -leftWidth });
            }
        });

    });

  

    $(function () {
        //unity设计理念 鼠标移动进去 箭头的样式变化
        var conBoxSA = $(".con_box .scroll").children("a");

        conBoxSA.each(function () {
            var thisClassEnd = $(this).attr("class");
            $(this).hover(function () {

                if (!$(this).hasClass("scroll_left_cur") && !$(this).hasClass("scroll_right_cur")) {
                    $(this).attr("class", thisClassEnd + " " + thisClassEnd + "_cur");
                }

            }, function () {
                $(this).attr("class", thisClassEnd);
            });
        });


        //新增设计理念其中一个形式·鼠标移动过去出现描述
        var textLength = $(".text-img").children(".txt-01").text().length;
        if (textLength > 2) {//大于一个文字的长度才会触发这个事件
            $(".text-img").hover(function () {
                $(this).children(".txt-01").show();
            }, function () {
                $(this).children(".txt-01").hide();
            });
        }

    });

        //如果是谷歌内核并且版本是42，则提醒
        if (/chrome\/4([2-5])./.test(navigator.userAgent.toLowerCase())) {
            if (jQuery("#unityPlayer").find("embed").length==0) {
                showConflictTips();
            }
        }
        var elem = document.body; // Make the body go full screen.
        requestFullScreen(elem);


        //分享设计
        function ShareDesign(backgroundImageData, imageData, designId) {
            hideUnity();
            $("#share").show(50);
            changeBackground(backgroundImageData);
            handle.fn.uploadSharePic("shareImgUrl", imageData, "331", "220");
        }
        //分享抓图
        function SharePicture(backgroundImageData, imageData, designId, transform) {
            hideUnity();
            $("#print_share").show(50);
            changeBackground(backgroundImageData);
            handle.fn.uploadSharePic("print_shareImgUrl", imageData, "590", "328");
        }
       
        //弹出商品详情页
        function OpenURLInColorBox(url) {
            handle.fn.openURLInColorBox(url);
        }
        //分享自己的方案
        function ShareMyDesign(backgroundImageData, imageData, designId) {
        }
        //申请渲染
        function ApplyLightmapRender(key, sceneId, requestId) {
            handle.fn.checkLightMapState(key, sceneId, requestId);
        }
        //记录unity下载失败日志
        //function ShowSceneLoadingError(sceneId, errorMsg) {
        //  handle.fn.recordUnityFailLog(sceneId, errorMsg);
        //}

        //申请置入
        function Apply3DExperience(key, houseId, sceneId, backHistory) {
            handle.fn.apply3DExperience(key, houseId, sceneId, backHistory);
        }
        // 弹出登陆层
        function Login() {
            showLoginBox();
        }
        //上传平面图
        function UploadFloorPlan(key, designId) {
        }
        //我的方案收藏
        function AddDesignToFavourite(backgroundImageData, key, designId) {
            handle.fn.addDesignToFavourite(backgroundImageData, key, designId);
        }
        //falsh通讯
        function HandleFlashToHtml(progress) {
            // showProgressBar($loadProgress,progress);
        }
        //unity下载完处理方法
        function UnityDownLoadComplete(sceneId, lossDataMsg) {
            handle.fn.unityDownLoadComplete(sceneId, lossDataMsg);
        }
        // 功能描述：排家私进度显示
        function ShowArrangeProgress() {
            SuspendProcessBar();
            CallCricleProcessBar();
        }
        //unity跳转页面
        function UnityRedirect(type) {
            handle.fn.unityRedirect(type);
        }

        //谷歌分析工具
        function GoogleAnalytics(keyWord) {
            //var _gaq = _gaq || [];
            //_gaq.push(['_setAccount', 'UA-232915-7']);
            //_gaq.push(['_trackPageview']);
        }
        //反馈户型纠错信息
        function CorrectApartmentInfo(key, sceneId, apartmentId, space) {
            handle.fn.showUnityWrongBox(key, sceneId, apartmentId, space);
        }
        //debug
        function ShowProductDebugInfo(namestr) {
            handle.fn.showProductDebugInfo(namestr);
        }
        //上传unity日志
        function UploadUnityLog(log) {
            handle.fn.uploadUnityLog(log);
        }
        /*
        UpdateProductList
      AddFurnitureToProductList
         DeleteFurnitureToProductList
        */
        function UpdateProductList(datas) {
            handle.fn.updateProductList(datas);
        }
        function AddFurnitureToProductList(oldData, newData) {
            console.log(oldData + ";" + newData);
        }
        function DeleteFurnitureToProductList(data) {
            //console.log(data);
        }
        //隐藏div
        function HideThisDiv(obj) {
        	
            $("#shareImgUrl,#print_shareImgUrl").empty();
            $("#jiathis_weixin_share").remove();
            removeBackgroud();
            $(obj).hide();
            if (obj == "#new_apply" || obj == "#loginAndRegister") {
                SendWebPlayerMessage(false, "");
            }
            showUnity();
        }
        //重试
        function retryApply(obj) {
            var mdId = obj.id;
            if (!IsNullOrEmpty(mdId)) {
                $.getJSON("/unity/unity/retryApply", { mdId: mdId }, function (result) {
                    if (result.Message == "ok") {
                        CallCricleProcessBar();
                    }
                });
            }
        };

        function generateTexturePriceReport_customerJS(arg1) {
            $("#j-textureInfo").val(arg1);
            $("#getEexcelForm").attr("action", "generateTexturePriceReport_customer").submit();
            //window.open('generateTexturePriceReport_customer?TextureInfo=' + arg1);
        };

        function generateTexturePriceReport_budgetJS(arg1) {
            $("#j-textureInfo").val(arg1);
            $("#getEexcelForm").attr("action", "generateTexturePriceReport_budget").submit();
            //window.open('generateTexturePriceReport_budget?TextureInfo=' + arg1);
        };

        function loadComplete(obj) {

            var href = obj.attributes["link"].value;
            if (IsNullOrEmpty(href)) {
                return;
            } else {
                window.location.href = href;
            }

        };
    
        function setShare(pic, title, summary) {
            jiathis_config.pic = pic,
            jiathis_config.title = title;
            jiathis_config.summary = summary;
        }
        var jiathis_config = {
            pic: "",
            title: "",
            summary: ""
        };