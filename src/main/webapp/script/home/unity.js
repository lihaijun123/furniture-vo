/// <reference path="unity.js" />
//unity操作处理函数
/*引用 /Scripts/Base/common.js 
          /Scripts/Unity/casamia.js*/
var handle = {};
var loadedTimer;
handle.prototype = {
    $newApplyFirst: $("#new_apply div.new_apply_first"),
    $newApplySecond: $("#new_apply div.new_apply_second"),
    $divLogin: $("#loginAndRegister"),
    $application: $('#new_apply'),
    $newApplyFirstDefault: $("#new_apply div.new_apply_first_hasdefault"),
    $ulApartment: $("#new_apply div.new_apply_first ul.apartment li"),
    $bombBox: $("#bomb_box"),
    $unityWrong: $("#unity_wrong"),
    $collect: $("#collect"),
    $inception: $("#inception"),
    $expmenu: $("div.unity_box_right ul.expmenu"),
    version: GetWebVersion(""),
    solrUrl: solrUrl,
    recordTimes: 1,//用来记录一次加载失败
    isLoadedProList: false, //用来记录是否已经加载右侧的产品列表
    isLoadedProListDone:false,//用来记录是否已经加载完成右侧的产品列表
    lazyProData: {},
    unityFail: false  //用来记录unity是否加载失败
};

//点击使用默认户型
handle.prototype.$newApplyFirstDefault.find("#userDefault").click(function () {
    var apartmentId = $("input[name=defaultApartment]").val();
    var pictureId = $("input[name=defaultPicture]").val();
    if (IsNullOrEmpty(apartmentId)) {
        return;
    }
        var userDefault = $(this);
        if (userDefault.attr("opened") != "true") {
            userDefault.attr("opened", "true");
            $.getJSON("/common/common/ConfirmApartmentInfo?ApartmentId=" + apartmentId + "&PicId=" + pictureId, function (result) {
                if (result.Message == "ok") {
                    handle.prototype.$application.hide();
                    ComfirmAddress(result.Data);
                    userDefault.attr("opened", "false");
                }
                if (result.Message == "fail") {
                    handle.fn.showTipMsg("数据有误");
                    userDefault.attr("opened", "false");
                }

            });
        }
    });
//点击搜索我家户型图
handle.prototype.$newApplyFirstDefault.find("#choseAgain").unbind("click").click(function () {
    showApplySearchBox();
});


//关闭渲染提醒窗体
handle.prototype.$bombBox.find(".bomb_close").unbind("click").click(function() {
    handle.prototype.$bombBox.hide();
    SendWebPlayerMessage(false, "");
    showUnity();
});
//右侧产品清单点击事件
$("div.unity_box_right .list").eq(0).click(function () {
    if (handle.prototype.isLoadedProList) {
        handle.fn.lazyLoadProductList();
    } else {
        loadedTimer = setInterval(function () {
            if (!handle.prototype.isLoadedProListDone) {
                handle.fn.lazyLoadProductList();
            } else {
              window.clearInterval(loadedTimer);
            }
        }, 800);
      
    }
});


//点击取消申请
 $("#cancel").unbind().bind("click", function () {
     handle.prototype.$application.find(".new_apply_first").show(100).siblings().hide();
 });
//handle处理函数
handle.fn = {
    //初始化地址信息
    initAddressInfo: function (pro, city, county) {
        new PCAS("selectProvince=" + pro, "selectCity=" + city, "selectCountry=" +county);
    },
    //初始化上传插件
    initUploadify: function (id) {
        $("#" + id).uploadify({
            swf: "/Scripts/jquery.uploadify/uploadify.swf" + handle.prototype.version,
            uploader: "/Common/Common/Upload?sSavePath=sweethome/room/image/v0001&var",
            auto: true,
            buttonText: "户型图上传",
            fileTypeDesc: '图片文件',
            fileTypeExts: '*.gif; *.jpg;*.jpeg;*.png',
            removeCompleted: false,
            onUploadStart: function (file) {
                //清除队列只保留最后一个
                $("#select_upload-queue").children(":last").siblings().remove();
                $(".image_name").html("");
                $("#uploadMsg").find("img").attr("src", "/Content/Images/Unity/unity_apply/back04.png" + handle.prototype.version);
            },
            queueSizeLimit: 1,
            uploadLimit: 99,
            width: 86,
            height: 25,
            multi: false,
            removeTimeout: 1,
            onUploadSuccess: function (file, data, response) {
                eval("data=" + data);
                if (data.Success == true) {
                    var img = GetThumbnail(data.SaveName, 170, 170);
                    var bigUrl = GetImageFullUrl(data.SaveName);
                    $("#uploadMsg").find("img").attr({ "src": img, "bigUrl": bigUrl });
                }
                if (data.Success == false) {

                }
            }
        });
    },
    //搜索自动补全
    autoComplete: function (id) {
        var $mq = $("#" + id);
        var options = {
            serviceUrl: handle.prototype.solrUrl+"/suggest?json.wrf=?",
            delimiter: /(,|;)\s*/,//分隔符
            deferRequestBy: 200, //单位微秒
            params: { wt: 'json' },//参数
            noCache: false//是否启用缓存 默认是开启缓存的
        };
        $mq.autocomplete(options);
        $mq.keypress(function(e) {
            if (e.keyCode == 13) {
                handle.prototype.$newApplyFirst.find("a.select_button").click();
                if (e && e.stopPropagation && e.preventDefault) {
                    e.stopPropagation();
                    e.preventDefault();
                }
            }
        });
        //$("#" + id).autocomplete({
        //    source: function (request, response) {
        //        $.ajax({
        //            url: handle.prototype.solrUrl + "/suggest?json.wrf=?",
        //            dataType: "jsonp",
        //            data: {
        //                wt: "json",
        //                q: request.term
        //            },
        //            success: function (data) {
        //                var spellcheck = data.spellcheck;
        //                if (spellcheck.suggestions.length >= 2) {
        //                    var autocompleteData = data.spellcheck.suggestions[1].suggestion;
        //                    response(autocompleteData);
        //                } else {
        //                    response(new Array());
        //                }
        //            },
        //            error: function (data) {
        //                alert(data);
        //            }
        //        });
        //    },
        //}).keypress(function (e) {
        //    if (e.keyCode == 13) {
        //        handle.prototype.$newApplyFirst.find("a.select_button").click();
        //        if (e && e.stopPropagation && e.preventDefault) {
        //            e.stopPropagation();
        //            e.preventDefault();
        //        }
        //    }
        //});
    },
    //unity debug模式下，新建页签展示产品页面
    showProductDebugInfo: function (namestr) {
       
        // window.location.href = "/common/common/ProductDebug?nameStr=" + namestr;
        window.open("/common/common/productdebug?nameStr=" + namestr);
    },
    //unity 申请置入返回上一步
    showBackSearchBox: function() {
        handle.prototype.$application.find("div.popup_box").hide();
        handle.prototype.$newApplyFirst.show(80).siblings().hide();
    },
    //弹出登陆框
    showDivLogin: function () {
        hideUnity();
        handle.prototype.$divLogin.show(100).siblings().hide();
        handle.prototype.$divLogin.find(".login_form").show(100);
        handle.prototype.$divLogin.find(".register_form").hide();
    },
    //信息提醒
    showTipMsg: function (msg) {
        $.jBox.tip(msg);
    },
    //弹出商品详情页
    openURLInColorBox: function (url) {
        $.colorbox({
            iframe: true,
            href: url,
            onOpen: function () {
                hideUnity();
            },
            onClosed: function () {
                showUnity();
            },
            width: "96%",
            height: "96%"
        });
    },

    uploadUnityLog: function (log) {
        $.post("/common/common/uploadUnityLog", {log:log}, function(data) {
            if (data.Message == "ok") {
                console.log(data.Data);
                if (data.Data.Type == "Exception") {
                    handle.fn.recordUnityFailLog();
                  
                } 
                if (data.Data.Type == "Brower_ConsoleLog") {
                    handle.prototype.recordTimes = 1;
                    handle.prototype.unityFail = false;
                }
                
            }
        });
    },
    //记录unity下载失败日志
    recordUnityFailLog: function () {
        hideUnity();
        if (handle.prototype.recordTimes == 1) {
            var failure = "<div class=\"failure\" id=\"failure\" style=\"display: block\">" +
            "<div class=\"fa_title\"><a title=\"点击重新加载\" href=\"{0}\"></a></div>".format(window.location.href) +
            "<div style=\"clear: both\"></div>" +
            "<a class=\"fa_back\" title=\"返回上一页\" href=\"javascript:window.history.go(-1);\"></a>" +
            "<a class=\"fa_home\" title=\"返回首页\"  href=\"{0}\"></a>".format("/homepage/home/index") +
            "</div>";
            $("div.unity_box").css("height", "562px");
            $("div.bg_bg").append(failure);
        }
        handle.prototype.recordTimes++;
        handle.prototype.unityFail = true;
     
    },
    //方案收藏
    addDesignToFavourite: function (backgroundImageData, key, designId) {
        hideUnity();
        changeBackground(backgroundImageData);
        var setdata = {
            key: key,
            designId: designId
        };
        $.ajax({
            url: "/Unity/Unity/AddDesignToFavourite",
            data: setdata,
            cache: false,
            success: function (data) {
                if (data.Message == "fail") {
                    hideUnity();
                    handle.prototype.$collect.find(".title").html("收藏失败！");
                    $collect.show(50).siblings().hide();
                }
                if (data.Message == "exist") {
                    hideUnity();
                    handle.prototype.$collect.find(".title").html("已经收藏了！");
                    handle.prototype.$collect.show(50).siblings().hide();
                }
                if (data.Message == "nologin") {
                    showLoginBox();
                }
                if (data.Message == "ok") {
                    hideUnity();
                    handle.prototype.$collect.show(50).siblings().hide();
                }
            },
            error: function (data) {
            }
        });
    },
    //unity下载完成处理
    unityDownLoadComplete: function () {
        handle.prototype.recordTimes = 1;
        handle.prototype.unityFail = false;
    },

    /************************************申请置入**************************************/
    //申请置入
    apply3DExperience: function (key, houseId, sceneId, backHistory) {
        var setData = {
            key: key,
            houseId: houseId,
            schemeId: sceneId
        };
        $.ajax({
            url: "/common/checkunitystate",
            data: setData,
            cache: false,
            dataType:"json",
            success: function (data) {
                if (data.Message == "nologin") {
                    showLoginBox();
                }
                if (data.Message == "errorParas") {
                    SendWebPlayerMessage(true, "参数有误");
                }
                if (data.Message == "arranging") {
                    SendWebPlayerMessage(true, "正在排家私，在此期间\n不可申请");
                }
                if (data.Message == "unableArrange") {
                    SendWebPlayerMessage(true, "此方案不具备置入功能");
                }
                if (data.Message == "exceed") {
                
                    if (backHistory == "history") {
                        hideUnity();
                        showApplication();
                        handle.fn.showBackSearchBox();
                    }
                    else {
                        showInceptionBox();
                        handle.prototype.$inception.find(".confirm").unbind("click").click(function () {
                            //如果存在默认户型
                            if (typeof (data.Data) != "undefined") {
                                handle.prototype.$inception.hide();
                                showApplication();
                                $("input[name=defaultApartment]").val(data.Data.ApartmentId); 
                                $("input[name=defaultPicture]").val(data.Data.PicId);
                                showApartmentBox();
                            } else {
                                handle.prototype.$inception.hide();
                                showApplication();
                                showApplySearchBox();
                            }
                        });
                        handle.prototype.$inception.find(".none_confirm").unbind("click").click(function () {
                            handle.prototype.$inception.hide();
                            SendWebPlayerMessage(false, "");
                            showUnity();
                        });
                    }
               
                }
                if (data.Message == "ok") {
                    hideUnity();
                    showApplication();
                    //如果存在默认户型
                    if (typeof (data.Data) !== "undefined") {
                        $("input[name=defaultApartment]").val(data.Data.ApartmentId);
                        $("input[name=defaultPicture]").val(data.Data.PicId);
                        showApartmentBox();
                    } else {
                   
                        if (backHistory == "history") {
                            handle.fn.showBackSearchBox();
                        } else {
                            showApplySearchBox();
                        }

                    }
                }
            },
            error: function (data) {
                console.log("验证key失败");
                showUnity();
            }
        });
    },
    //隐藏申请体验框
    hideApplyFirst: function () {
        handle.prototype.$newApplyFirst.hide();
    },
    //unity页面跳转（返回其他设计，查看套入进度）
    unityRedirect: function(type) {
        if (type == "design") {
            window.location.href = "/ProductInfo/Home/DesignList";
        }
        if (type == "progress") {
            window.location.href = "/Customer/Scheme/Index";
        }
    },

    /************************************申请置入**************************************/

    /************************************上传户型图**************************************/
    //清空户型申请数据
    clearApplyMsg: function () {
        $("#txtGallery").val("");
        $("#txtFloor").val("");
        $("#txtArea").val("");
        $("select[name='selectPlansType']").val(1);
        $("select[name='selectRoomType']").val(1);
        $("input[name='txtStreet']").val("");
        $("#file_upload-queue").empty();
        handle.prototype.$newApplySecond.find(".tipinfo").empty();
    },
    //弹出上传户型图
    showApplySecond: function () {

        handle.prototype.$newApplySecond.find(".de_box").empty().append("<a><img src=\"/Content/Images/Unity/unity_apply/back04.png" + handle.prototype.version + "\"></a>");
        handle.prototype.$newApplySecond.find("#select_upload-queue").html("");
        handle.prototype.$newApplySecond.find(".image_name").html("我的平面户型图.jpg");

        handle.fn.initAddressInfo("广东省", "广州市", "天河区");
        handle.fn.hideApplyFirst();
        handle.fn.clearApplyMsg();
        handle.prototype.$newApplySecond.show();
    },
    //弹出渲染动画
    showRending: function () {
        $('#rendering').show().siblings().hide();
        //渲染4秒后隐藏
       //setTimeout('HideThisDiv("#new_apply")', 4000);
        CallCricleProcessBar();
    },
    //上传图片
    uploadSharePic: function (shareImgUrl, imagedata, width, height) {
        $("#" + shareImgUrl).html("加载中...");
        $.ajax({
            url: "/Unity/Unity/SaveCameraPicture",
            data: { imagedata: imagedata },
            type: "post",
            success: function (data) {
                var backPath = data.Data;
                var shareUrl = GetThumbnail(backPath, 800, 800);
                var imghtml = "<img style=\"width: " + width + "px;height: " + height + "px\"  src=\"" + backPath + "\" shareUrl=\"" + shareUrl + "\"/>";
                $("#" + shareImgUrl).html("").append(imghtml);
            },
            error: function (data) {
                console.log("上传错误！");
            }
        });
    },
    //保存上传户型图信息
    saveApplicationRender: function(isold) {
        var $apply = handle.prototype.$application;
        if (isold == "false") {
        } else if (isold == "true") {
            //防止重复点击
            if ($apply.find("input[name=submit]").attr("opened") == "true") {
                return;
            }
            $apply.find("input[name=submit]").attr("opened", "true");
            //获取用户填写的信息
            var selectProvince = $("select[name='selectProvince']").val();
            var selectCity = $("select[name='selectCity']").val();
            var selectCountry = $("select[name='selectCountry']").val();
            var plansType = $("select[name='selectPlansType']").val();
            //   var doorModel = $("select[name='selectDoorModel']").val();
            var doorModel =$("select[name='selectDoorModel']").find("option:selected").text();
            var txtArea = $("#txtArea").val();
            var txtStreet = $("input[name=txtStreet]").val();
            var floor = $("#txtFloor").val();
            var txtGallery = $("#txtGallery").val();
            var picUrl = $("#uploadMsg").find("img").attr("bigUrl");
            var designId = $("input[name='designId']").val();
            var designName = $("input[name='designName']").val();
            var data = {
                buildinfo: floor, //楼盘
                province: selectProvince,
                city: selectCity,
                country: selectCountry,
                street: txtStreet,
                uploadedPath: picUrl, //上传图片的路径
                designId: designId,
                designName: designName,
                gallery: txtGallery, //楼座
                plansType: plansType, //房屋类型（小区房）
                doormodel: doorModel, //几居室
                housearea: txtArea //面积
            };
            $.getJSON("/Unity/Unity/SaveApplicationRender", data, function(result) {
                $apply.find("input[name=submit]").attr("opened", "false");
                if (result.Message == "nologin") {
                    handle.fn.showDivLogin();
                }
                if (result.Message == "hasApply") {
                    handle.fn.showTipMsg("你已经有毛胚房在申请审核中...");
                }
                if (result.Message == "ok") {
                    handle.fn.showRending();
                }
                if (result.Message == "fail") {
                    handle.fn.showTipMsg("处理失败");
                }
            });
        }
    },
    //判断是否上传图片
    applicationOps: function() {
        //先判断是否上传图片
        var b_up = $("div.uploadify-queue-item").length > 0 ? true : false;
        if (!b_up) {
            $.jBox.tip("请上传户型图");
            return;
        }
        handle.fn.saveApplicationRender("true");
    },
    //验证函数
    initValidator: function () {
        $("#formApply").validate({
            onkeyup: false,
            //设置验证规则   
            rules: {
                "txtFloor": {
                    required: true,
                    stringCheck: true,
                    rangelength: [2, 50]
                },
                "txtStreet": {
                    stringCheck: true
                },
                "txtGallery": {
                    stringCheck: true
                }
            },
            //设置错误信息  
            messages: {
                "txtFloor": {
                    required: "请输入楼盘信息",
                    stringCheck: "内容格式不合法",
                    rangelength: "请输入2-50字符"
                },
                "txtStreet": {
                    stringCheck: "内容格式不合法"
                },
                "txtGallery": {
                    stringCheck: "内容格式不合法"
                }
            },
            errorElement: "font",
            errorPlacement: function (error, element) {
                error.appendTo(element.parent().find(".tipinfo"));
            },
            success: "valid"
        });
    },

    /************************************上传户型图**************************************/

    /************************************申请渲染**************************************/
    //检测渲染
    checkLightMapState: function (key, sceneId, requestId) {
        $.ajax({
            url: "/Common/CheckUnityState/LightmapRender",
            data: { key: key, requestId: requestId, sceneID: sceneId },
            cache: false,
            dataType: "json",
            success: function (data) {
                if (data.Message == "nologin") {
                    showLoginBox();
                }
                if (data.Message == "rendering") {
                    SendWebPlayerMessage(true, "你已提交渲染申请，\n在此期间不可申请");
                }
                if (data.Message == "unableRender") {
                    hideUnity();
                    handle.prototype.$bombBox.show(80).siblings().hide();
                    handle.prototype.$bombBox.find("div.bomb_04").show(80).siblings().hide();
                    handle.prototype.$bombBox.find("div.bomb_04 .yes").unbind("click").click(function () {
                        CallCricleProcessBar();
                        handle.fn.comfirmApplyRender(key, sceneId, requestId);
                        return false;
                    });
                    handle.prototype.$bombBox.find("div.bomb_04 .no").unbind("click").click(function () {
                        handle.prototype.$bombBox.hide();
                        SendWebPlayerMessage(false, "");
                        showUnity();
                        return false;
                    });
                }
                if (data.Message == "norequest") {
                    SendWebPlayerMessage(true, "norequest");
                }
                if (data.Message == "noexists") {
                    SendWebPlayerMessage(true, "没有置入的方案");
                }
                if (data.Message == "ok") {
                    hideUnity();
                    handle.prototype.$bombBox.show(80).siblings().hide();
                    handle.prototype.$bombBox.find("div.bomb_01").show(80).siblings().hide();
                    handle.prototype.$bombBox.find("div.bomb_01 .i_know").unbind("click").click(function () {
                        handle.prototype.$bombBox.find("div.bomb_02").show().siblings().hide();
                        return false;
                    });
                    handle.prototype.$bombBox.find("div.bomb_02 .yes").unbind("click").click(function () {
                        handle.fn.comfirmApplyRender(key, sceneId, requestId);
                        return false;
                    });
                    handle.prototype.$bombBox.find("div.bomb_02 .no").unbind("click").click(function () {
                        handle.prototype.$bombBox.hide();
                        SendWebPlayerMessage(false, "");
                        showUnity();
                        return false;
                    });
                }
            }
        });
    },
    //确认申请渲染
    comfirmApplyRender: function (key, sceneId, requestId) {
        $.getJSON("/Unity/Unity/ApplyLightmapRender", { key: key, requestId: requestId, sceneID: sceneId }, function (data) {
            CallCricleProcessBar();
            if (data.Message == "nologin") {
                showLoginBox();
            }
            if (data.Message == "unableRender") {
                hideUnity();
                handle.prototype.$bombBox.show().siblings().hide();
                handle.prototype.$bombBox.find("div.bomb_04").show().siblings().hide();
                handle.prototype.$bombBox.find("div.bomb_04 .yes").unbind("click").click(function () {
                    CallCricleProcessBar();
                    handle.fn.comfirmApplyRender(key, sceneId, requestId);
                    return false;
                });
                handle.prototype.$bombBox.find("div.bomb_04 .no").unbind("click").click(function () {
                    handle.prototype.$bombBox.hide();
                    showUnity();
                    return false;
                });
            }
            if (data.Message == "norequest") {
                handle.prototype.$bombBox.hide();
                SendWebPlayerMessage(true, "norequest");
                showUnity();
            }
            if (data.Message == "noexists") {
                handle.prototype.$bombBox.hide();
                SendWebPlayerMessage(true, "没有置入的方案");
                showUnity();
            }
            if (data.Message == "ok") {
                handle.prototype.$bombBox.find("div.bomb_03").show().siblings().hide();
            }
            if (data.Message == "fail") {
                handle.prototype.$bombBox.hide();
                SendWebPlayerMessage(true, "fail");
                showUnity();
            }
        });
    },
    /************************************申请渲染**************************************/

    /************************************户型纠错框**************************************/
    //弹出纠错框
    showUnityWrongBox: function (key, sceneId, apartmentId, space) {
        hideUnity();
        handle.fn.getUserPhoneEmail();
        var $wrong = $("#unity_wrong");
        //清空数据
        $wrong.find("#wrong_space").html(space);
        $wrong.find("textarea").val("");
        $wrong.find(".wrong_box").show();
        $wrong.find(".wrong_success").hide();
        $wrong.find(".send_again").empty().append("<a href=\"javascript:void(0);\">免费获取验证码</a>");
        $wrong.find(".send_success").hide().empty();
        $wrong.show(80).siblings().hide();
        //绑定反馈提交点击事件
        handle.prototype.$unityWrong.find(".wrong_btn").unbind("click").click(function () {
            handle.fn.recordApartmentFeedBack(key, sceneId, apartmentId, space);
        });
        //绑定发送验证码事件
        handle.prototype.$unityWrong.find(".send_again").unbind("click").click(function () {
            var type = $(this).attr("sType");
            if (IsNullOrEmpty(type)) {
                return;
            }
            handle.fn.sendSmsVerification(type);
        });
        //绑定反馈返回事件
        handle.prototype.$unityWrong.find(".wrong_back").unbind("click").click(function () {
            $("#unity_wrong").hide();
            showUnity();
        });
    },
    //记录用户反馈毛胚房信息
    recordApartmentFeedBack: function (key, sceneId, apartmentId, space) {
        var content = handle.prototype.$unityWrong.find("textarea").val();
        var vcode = handle.prototype.$unityWrong.find("#wrong_code").val();
        var vtype = handle.prototype.$unityWrong.find(".send_again").attr("sType");
        //为空
        if (IsNullOrEmpty(content)) {
            handle.fn.showTipMsg("内容为空");
            handle.prototype.$unityWrong.find("textarea").focus();
            return;
        }
        //过滤特殊字符
        if (!/^[^\|"'<>]*$/.test(content)) {
            handle.fn.showTipMsg("不能包含特殊字符");
            return;
        }
        if (IsNullOrEmpty(vcode)) {
            handle.fn.showTipMsg("验证码为空");
            handle.prototype.$unityWrong.find("#wrong_code").focus();
            return;
        } else {
            var setData = { designId: sceneId, apartmentId: apartmentId, roomType: space, content: content, vcode: vcode, vtype: vtype };
            $.getJSON("/Unity/Unity/RecordApartmentFeedBack", setData, function (data) {
                var $uw = handle.prototype.$unityWrong;
                if (data.Message == "nologin") {
                    $uw.find("#wrong_code").css("border", "1px solid #206BC6");
                    handle.fn.showDivLogin();
                }
                if (data.Message == "nodata") {
                    console.log("数据丢失！");
                }
                if (data.Message == "codeError") {
                    $uw.find("#wrong_code").css("border", "1px solid red").focus();
                }
                if (data.Message == "hasFeedBack") {
                    $uw.find("#wrong_code").css("border", "1px solid #206BC6");
                    handle.fn.showTipMsg("该毛胚房已经反馈，正在确认中...");
                }
                if (data.Message == "ok") {
                    $uw.find("#wrong_code").css("border", "1px solid #206BC6");
                   
                    $uw.find("textarea").val("");
                    $("#unity_wrong").hide();
                    showUnity();
                    
                }
                if (data.Message == "fail") {
                    console.log("程序出错！");
                }
            });
        }
    },
    //获取用户手机和邮箱验证方式
    getUserPhoneEmail: function () {
        $.getJSON("/common/common/GetUserPhoneEmail", function (data) {
            var childElement = handle.prototype.$unityWrong.find("#showPhoneEmailInfo").children();
            var msg = data.Message;
            if (msg == "ok") {
                var phone = data.Data.Phone;
                var email = data.Data.Email;
                phone = IsNullOrEmpty(phone) ? "" : phone;
                email = IsNullOrEmpty(email) ? "" : email;
                handle.prototype.$unityWrong.find(".wrong_select").empty();
                if (!IsNullOrEmpty(phone)) {
                    handle.prototype.$unityWrong.find(".wrong_select").append("<option value=\"phone\">手机验证</option>");
                }
                if (!IsNullOrEmpty(email)) {
                    handle.prototype.$unityWrong.find(".wrong_select").append("<option value=\"email\">邮箱验证</option>");
                }
                childElement.eq(0).find("a").html(phone);
                childElement.eq(1).find("a").html(email);
                //默认取第一个option
                var firstType = handle.prototype.$unityWrong.find(".wrong_select option").eq(0).attr("value");
                if (firstType == "phone") {
                    childElement.eq(0).show();
                }
                if(firstType=="email")
                {
                    childElement.eq(1).show();
                }
                handle.prototype.$unityWrong.find(".send_again").attr("sType", firstType);
                //绑定反馈改变验证方式事件
                handle.fn.changePhoneEmail();
            }
            if (msg == "nologin" || msg == "fail") {
                childElement.eq(0).find("a").html("");
                childElement.eq(1).find("a").html("");
            }

        });
    },
    //手机验证和邮箱验证选择事件
    changePhoneEmail: function () {
        handle.prototype.$unityWrong.find(".wrong_select").unbind("change").change(function () {
            var value = $(this).val();
            handle.prototype.$unityWrong.find(".send_again").attr("sType", value);
            var childElement = handle.prototype.$unityWrong.find("#showPhoneEmailInfo").children();
            if (value == "phone") {
                childElement.eq(0).show().siblings().hide();
            }
            if (value == "email") {
                childElement.eq(1).show().siblings().hide();
            }
            // handle.fn.showTipMsg(value);
        });
    },
    //发送手机和邮箱验证码
    sendSmsVerification: function (type) {
        var action = type == "phone" ? "GetPhoneValidCode" : "GetEmailValidCode";
        var reset = "<a href=\"javascript:void(0);\">重新获取验证码</a>";
        $.getJSON("/Common/ReceiveSmsState/" + action, function (data) {
            //未登录
            if (data.Message == "nologin") {
                handle.fn.showDivLogin();
            }
            //发送成功
            if (data.Message == "ok") {
                //倒计时函数
                var sh;
                var time = function () {
                    var $a = handle.prototype.$unityWrong.find(".send_again a");
                    var second = parseInt($a.html()) - 1;
                    if (second <= 0) {
                        handle.prototype.$unityWrong.find(".send_again").empty().show().append(reset);
                        clearInterval(sh);
                    } else {
                        $a.html(second);
                    }
                };
                var countdown = "<a>120</a>秒后重新获取";
                var s = "<div class=\"send_success\"><span></span>验证码发送成功，请查收</div>";
                handle.prototype.$unityWrong.find(".send_again").empty().show().append(countdown);
                handle.prototype.$unityWrong.find(".send_success").empty().show().append(s);
                //倒计时120秒
                sh = setInterval(time, 1000);
            }
            //发送失败
            if (data.Message == "fail") {
                var e = "<div style=\"color:red;\"><span class=\"unity_wrong_icon\" style=\"background: url('/Content/Images/Unity/unity_wrong/unity_wrong.png?v={0}') no-repeat 0 -412px;float: left;margin-top: 8px;margin-right: 5px;\"></span>验证码发送失败</div>";
                handle.prototype.$unityWrong.find(".send_success").empty().show().append(e);
            }
        });
    },
    /************************************户型纠错框**************************************/


    /************************************异步加载方案*****************************/

    //发送unity设置参数给WebPlayer
    sendUnityParamsToPlayer: function (setdata) {
        $("div.loading_loading").show();
        $("div.unity_box_right .con_box .pro_list").hide();
        $.getJSON("/unity/unity/getUnityRunTimeParam", setdata, function (result) {
            if (result.Message == "false") {
                console.log("数据错误！");
            }
            if (result.Message == "ok") {
                if (handle.prototype.unityFail) {
                    $("#unityPlayer").empty();
                    showUnity();
                    unityRunTimeParam = result.Data.UnityParams;
                    u.initPlugin(jQuery("#unityPlayer")[0], baseWebplayer);
                } else {
                    //发送unity设置参数给WebPlayer
                    refreshUnityRunTimeParam(result.Data.UnityParams);
                }
                handle.prototype.unityFail = false;
          
                //更新方案右侧的产品列表
                handle.fn.updateSchemeProducts(result.Data);
                console.log(result.Data.UnityParams);
            }
        });
    },
    //发送排家私参数给WebPlayer
    sendArrangeParamsToPlayer: function(setdata) {
        $.getJSON("/Common/Common/GetArrangeParam", setdata, function (result) {
            if (result.Message == "ok") {
                forceComfirmAddress(result.Data.ApartmentData);
                //超过6个方案
                if (result.Data.DesignCount >= 6) {
                    showInceptionBox();
                    handle.prototype.$inception.find(".confirm").unbind("click").click(function () {
                        showUnity();
                    });
                    handle.prototype.$inception.find(".none_confirm").unbind("click").click(function () {
                        window.location.href = "/productinfo/home/designlist";
                    });
                }
            }
            if (result.Message == "fail") {
                console.log("处理有误！");
            }
            if (result.Message == "nologin") {
                window.location.href = "/Base/LoginAuth/Login?returnUrl="+window.location.href;
            }
            
        });
    },
    //更新方案右侧的产品列表和空间图片
    updateSchemeProducts: function (datas) {
        var scheme = datas.SchemeInfo;
        var i = 0;
        document.title = scheme.Sname + "- 智家居";
        $("div.unity_nav").find("a").eq(2).html(scheme.Sname);
        $("input[name='designId']").val(scheme.DesignId);
        $("input[name='designName']").val(scheme.Sname);
        //分享链接修改
        $("#shareTitle").html(scheme.Sname); 
        $("#shareSummary").html(scheme.Summary);
        var myhost = window.location.host.indexOf("localhost")==-1?window.location.host:"http://"+window.location.host;
        jiathis_config.url = myhost + "/unity/unity/details?designId={0}".format(scheme.DesignId);
        //替换方案主图片
        handle.prototype.$collect.find("a.back img").attr("src", GetThumbnail(scheme.ImgUrl, 148, 148));
        //总价钱
        var hisDesigns = datas.HisDesign;
        //更新右侧历史列表数据
        if (!IsNullOrEmpty(hisDesigns)) {
            var hisLi = "";
            for (i ; i <hisDesigns.length; i++) {
                hisLi += "<li><p><a href=\"unity/unity/details?designId={0}\">{1}</a><span>{2}</span></p>".format(hisDesigns[i].DesignId,hisDesigns[i].DesignName, hisDesigns[i].AddTimeStr) +
                    "<div class=\"his_de\"><a class=\"No_txt\">{0}</a><a href=\"unity/unity/details?designId={1}\"><img style=\"width: 142px;height: 114px\" src=\"{2}\" /></a></div></li>".format(i< 10 ? "0"+ (i+1) : i+1,hisDesigns[i].DesignId,GetThumbnail(hisDesigns[i].DesignImgUrl, 142, 114));
            }
            $("div.browse_history ul.history").empty().append(hisLi);
            handle.fn.hoverHistory();
        }
        //更新设计理念
        if (!IsNullOrEmpty(scheme.SpaceImgs)) {
           var spaceImgs = eval(scheme.SpaceImgs);
            if (spaceImgs.length >= 1) {
                var spaceLi = "";
                i = 0;
                for (i; i < spaceImgs.length; i++) {
                    spaceLi += "  <li><img style=\"width: 278px; height: 474px;\" src=\"{0}\" /></li>".format(GetThumbnail(spaceImgs[i].url,278,474));
                }
                $("ul.design_list").empty().append(spaceLi);
                handle.fn.designBroadCasting();
            }
        }
    },
    //更新产品列表
    updateProductList: function(datas) {
        $.ajax({
            cache: false,
            url: "/unity/unity/updateProductList",
            data: { productdata: datas },
            type: "post",
            dataType: "json",
            success: function (data) {
                handle.prototype.lazyProData = data;
                //第二次加载的时候直接刷新。
                if (handle.prototype.isLoadedProList == true) {
                    handle.fn.lazyLoadProductList();
                } 
                    handle.prototype.isLoadedProList = true;
            }
        });
    },

    //延迟加载产品列表
    lazyLoadProductList: function () {
        var data = handle.prototype.lazyProData;
        if (typeof(data.Message) == "undefined") {
            return;
        }
        if (data.Message == "ok") {
            if (IsNullOrEmpty(data.Data)) {
                $("div.loading_loading").hide();
                $("#addCartTipMsg").html("没有数据").show();
                window.setTimeout(function () {
                    $("#addCartTipMsg").fadeOut();
                }, 1500);
                return;
            }
            var products = data.Data;
            handle.prototype.$expmenu.empty();

            var totalPrice = 0;
            $.each(products, function (name, value) {
                var len = value.length;
                var div = "<div class=\"header\">  <input type=\"checkbox\" /><label>{0}</label><a>共<span>{1}</span>件</a> <span class=\"arrow up\"></span></div>".format(name, len);
                var menu = "<ul class=\"menu\">";
                var li = "";
                for (var i = 0; i < value.length; i++) {
                    var tradename = "";
                    var price = parseFloat(value[i].NowPrice);
                    //var showNum = value[i].ShowStock == 0 ? 1 : value[i].ShowStock;
                    if (!IsNullOrEmpty(value[i].TrademarkName)) {
                        tradename = value[i].TrademarkName;
                    }
                    li += " <li><input type=\"checkbox\" /><a href=\"/productinfo/home/details?productId={0}\" target=\"_blank\"><img src=\"{1}\" /></a><a href=\"/productinfo/home/details?productId={0}\" title=\"{2}&nbsp;{3}\" target=\"_blank\" style=\"padding-left:5px;padding-top:5px;white-space: nowrap;text-overflow: ellipsis; overflow: hidden;width:105px\">{2}&nbsp;{3}</a><p>￥<span pId=\"{0}\" style=\"float: none; margin: 0px;\">{4}</span><br /></p></li>".format(value[i].ProductId, GetThumbnail(value[i].MainImgPath, 50, 50),
                        tradename, value[i].ProName, parseFloat(price).toFixed(2));
                    totalPrice = totalPrice + price;

                }
                menu = div + menu + li + "</ul>";
                handle.prototype.$expmenu.append("<li>{0}</li>".format(menu));

            });

            handle.fn.clickExpmenu();
            handle.fn.addProductToCart();
            handle.fn.countProductNumPrice();
            $("div.loading_loading").hide();
            $("div.unity_box_right .con_box .pro_list").show();
            $("div.unity_box_right .pro_head span").html("￥" + parseFloat(totalPrice).toFixed(2));
            handle.prototype.isLoadedProListDone = true;
        }
        if (data.Message == "fasle") {
            console.log("获取失败");
        }
    },

    //加入购物车
    addProductToCart: function () {
        $("#add_cart").unbind("click").click(function () {
            var ids = "";
            $("div.unity_box_right ul.menu input[type=checkbox]").each(function () {
                if ($(this).attr("checked")) {
                    var id = $(this).siblings("p").find("span").eq(0).attr("pId");
                    ids = ids + id + ";";
                }
            });
            if (IsNullOrEmpty(ids)) {
                $("#addCartTipMsg").html("请选择商品").show();
                window.setTimeout(function () {
                    $("#addCartTipMsg").fadeOut();
                }, 1000);
                return;
            }
            if (ids.length > 4) {
                $("#addCartTipMsg").html("正在加入中...").show();
                $.post("/unity/unity/AddShoppingCart", { ProductIds: ids }, function (result) {
                    if (result.Message == "ok") {
                        $("#addCartTipMsg").html("加入购物车成功").show();
                        window.setTimeout(function() {
                            $("#addCartTipMsg").fadeOut();
                        }, 1500);
                    }
                    if (result.Message == "nologin") {
                        $("#addCartTipMsg").html("请先登陆").show();
                        window.setTimeout(function () {
                            $("#addCartTipMsg").fadeOut();
                        }, 1500);
                    }
                    if (result.Message == "fail") {
                        $("#addCartTipMsg").html("加入购物车失败").show();
                        window.setTimeout(function () {
                            $("#addCartTipMsg").fadeOut();
                        }, 1500);
                    }
                });
                //console.log(ids);
            }
        });
    },

    //统计商品的勾选个数和总价格
    countProductNumPrice: function () {
        $("div.unity_box_right input[type=checkbox]").unbind("click").bind("click", function () {
            var len = 0;
            //如果是全选,则全部勾选
            if ($(this)[0].id == "selectAll") {
                if ($(this).attr("checked")) {
                    $("div.unity_box_right ul.expmenu input[type=checkbox]").attr("checked", true);
                } else {
                    $("div.unity_box_right ul.expmenu input[type=checkbox]").attr("checked", false);
                }
            }
            //如果是header,menu菜单的checkbox要全部勾选
            if ($(this).parent(".header")) {
                //设置该分类下的产品为选中状态
                if ($(this).attr("checked")) {
                    $(this).parent(".header").next(".menu").find("input[type=checkbox]").attr("checked", true);

                } else {
                    $(this).parent(".header").next(".menu").find("input[type=checkbox]").attr("checked", false);
                }
            }

            //统计勾选的个数
            var price = 0;
            var ids = ""; 
            $("div.unity_box_right ul.menu input[type=checkbox]").each(function () {

                if ($(this).attr("checked")) {
                    len++;
                    var pri = $(this).siblings("p").find("span").eq(0).html();
                    var id = $(this).siblings("p").find("span").eq(0).attr("pId");
                    ids = ids + id + ";";
                    price = parseFloat(pri) + price;
                }
            });
            //显示勾选个数
            $("div.unity_box_right div.add_cart p span").eq(0).html(len);
            //显示总价钱
            $("div.unity_box_right div.add_cart p span").eq(1).html(price.toFixed(2));

        });
    },
    /*************************************异步加载方案*****************************/

    /**************************效果处理************************************/


    //产品清单，点击展示，点击收回功能
    clickExpmenu: function() {
        $("ul.expmenu li > div.header").unbind("click").click(function () {
            var arrow = $(this).find("span.arrow");
            if (arrow.hasClass("up")) {
                arrow.removeClass("up");
                arrow.addClass("down");
            } else if (arrow.hasClass("down")) {
                arrow.removeClass("down");
                arrow.addClass("up");
            }
            $(this).parent().find("ul.menu").slideToggle();
        });
    },
    hoverHistory: function() {
        $(".history li").hover(
            function () {
                $(this).css({ "background": "#eff3f9", "border-top": "1px dashed #8FA6B5", "border-bottom": "1px dashed #8FA6B5" });
                $(this).children(".his_de").css({ "box-shadow": "1px 1px 2px #155E60", "-webkit-box-shadow": "1px 1px 2px #155E60", "-moz-box-shadow": "1px 1px 2px #155E60", "filter": "progid:DXImageTransform.Microsoft.Shadow( color='#155E60', Direction=145, Strength=2)" });
                $(this).children(".his_de").find(".No_txt").css({ "background": "url(/Content/Images/Unity/new_unity/history_number_hover.png)" });
            },
            function() {
                $(this).css({ "background": "#DFE8F4", "border-top": "1px solid #DFE8F4", "border-bottom": "1px solid #DFE8F4" });
                $(this).children(".his_de").css({ "box-shadow": "none", "-webkit-box-shadow": "none", "-moz-box-shadow": "none", "filter": "none" });
                $(this).children(".his_de").find(".No_txt").css({ "background": "url(/Content/Images/Unity/new_unity/histroy_number.png)" });
            });
    },
    //设计理念轮播
    designBroadCasting: function () {
        var showClass = "selected";
        var desUl = $(".design_list");
        var desLi = desUl.children("li");
        var desIndex = 0;
        var desDos = $(".design_round");
        var arrow = $(".scroll");
        var appLi = "";
        var thisLiLength = desDos.parents(".scroll").siblings(".design_list").children().length;

        desLi.hide();
        desLi.first().show();

        desDos.children().remove();//每次插入可点击圆点前清空desDoc里面的子节点，以防叠加

        //动态插入可点击的小圆点（个数根据图片的个数而定）
        desDos.append("<ul></ul>");
        for (var i = 0; i < thisLiLength; i++) {
            appLi += "<li>"+"i"+"</li>";
        }
        desDos.children().append(appLi);

        var desDoc = $(".design_round ul li").text("");
        desDoc.first().addClass("selected");
        //点击圆点
        desDoc.unbind("click").click(function () {
            desIndex = $(this).index();
            desLi.eq(desIndex).fadeIn().siblings().fadeOut();
            $(this).addClass(showClass).siblings().removeClass("selected");
        });
            //点击左右箭头
        arrow.children("a").unbind("click").click(function () {
            if ($(this).hasClass("scroll_left")) {
                desIndex--;
                if (desIndex < 0) {
                    desIndex = thisLiLength-1;
                }
            } else if ($(this).hasClass("scroll_right")) {
                desIndex++;
                if (desIndex > thisLiLength-1) {
                    desIndex = 0;
                }
            }
            desDoc.eq(desIndex).addClass(showClass).siblings().removeClass(showClass);
            desLi.eq(desIndex).fadeIn().siblings().hide();
        });
    }

    /**************************效果处理*************************************/
};

//获取拼接条件
handle.fn.getJoinQuery = function () {
    var $first = handle.prototype.$newApplyFirst;
    var selectArea = $first.find("ul.option").eq(0).children(".cur"); //选择的面积
    var selectRooms = $first.find("ul.option").eq(1).children(".cur");//选择的户型
    var selectPlansType = $first.find("ul.apartment").children(".cur");//选择的类型
    var floor = $("#mq").val();
    var province = $first.find(".province").html();
    var city = $first.find(".city").html();
    var areaList = "";
    var roomsList = "";
    var plansTypeList = "";
    if (selectArea.length >= 1) {
        for (var i = 0; i < selectArea.length; i++) {
            areaList += $(selectArea[i]).attr("data-value") + ";";
        }
    }
    if (selectRooms.length >= 1) {
        for (var j = 0; j < selectRooms.length; j++) {
            roomsList += $(selectRooms[j]).attr("data-value") + ";";
        }
    }
    if (selectPlansType.length >= 1) {
        for (var z = 0; z < selectPlansType.length; z++) {
            plansTypeList += $(selectPlansType[z]).attr("data-value") + ";";
        }
    }
    var dataset = { keyword: floor, roomsList: roomsList, plansTypeList: plansTypeList, areaList: areaList, province: province, city: city, pageSize: 100, pageIndex: 1 };
    return dataset;
};

//搜索主方法
handle.fn.floorSearch = function (dataset) {
    var $first = handle.prototype.$newApplyFirst;
    $first.find("a.select_button").attr("opened", true);
    var path = "/rest/house/search";
    $.getJSON(path, dataset, function (result) {
        $first.find("a.select_button").attr("opened", false);
        $first.find("div.none_record >p").show();
        if (result.Message == "ok") {
            //存在数据
            var itemData = result.Data.Item;
            if (itemData.length >= 1) {
                var len = itemData.length;
                $first.find(".none_record").hide();
                $first.find(".apartment_record").show();
                var li = "";
                for (var i = 0; i < len; i++) {
                    //读取转换成功的图片
                    var smallImgUrl = itemData[i].picUrl;//GetThumbnail(itemData[i].ApartmentModelUrl + "floorPlan.png", 114, 90);
                    var bigImgUrl = itemData[i].picUrl//GetThumbnail(itemData[i].ApartmentModelUrl + "floorPlan.png", 400, 400);
                    li += "<li><input name=\"smallImgUrl\" value=\"{0}\" type=\"hidden\" /><a title=\"点击放大\" class=\"list_image\" bigurl=\"{6}\"><img  src=\"{0}\"></a><p>{1}{2} ㎡<br>{3}室{4}厅</p><a class=\"use_button\" opened=\"false\" picId=\"{5}\"  apId=\"{7}\" apartmentModelUrl=\"{8}\" version=\"{9}\">使用</a></li>".format(
                        smallImgUrl, itemData[i].Floor, Math.floor(parseFloat(itemData[i].Area)), itemData[i].Rooms, itemData[i].Saloons, itemData[i].PicId, bigImgUrl, itemData[i].ApartmentId, itemData[i].ApartmentModelUrl, itemData[i].version);
                }
                $first.find("ul.record_list").empty().css("margin-left", "0px").append(li);
                $("#btnRecordLeft").css("background", "url('/Content/Images/Unity/unity_apply/left.png')");
                if (len <= 4) {
                    $("#btnRecordRight").css("background", "url('/Content/Images/Unity/unity_apply/right_dark.png')");
                } else {
                    $("#btnRecordRight").css("background", "url('/Content/Images/Unity/unity_apply/right.png')");
                }
                fnchangeRecordList();
                //户型图鼠标移到效果并添加对应的事件
                $first.find("ul.record_list li").hover(
                    function () {
                        $(this).addClass("hover").siblings().removeClass("hover");
                        var bigurl = $(this).find(".list_image").attr("bigurl");
                        handle.prototype.$application.find("div.popup_box").find(">img").attr("src", bigurl);
                        handle.prototype.$application.find("div.popup_box").show();
                    },
                    function () {
                        $(this).removeClass("hover");
                    }
                );
                $first.find("ul.record_list li").find(".use_button").unbind().click(function () {
                    /*var apId = $(this).attr("apId");
                    var picId = $(this).attr("picId");
                    //处理重复提交
                    var opened = $(this).attr("opened");
                    if (opened == "false") {
                        var designId = getQueryStringByName("designId");
                        var set = { designId: designId, picId: picId, ApartmentId: apId };
                        handle.fn.applycomfirmAddress(set);
                        
                    }*/
                	//lihaijun
                	
                	$('#new_apply').hide();
                	showUnity();
                	var url = $(this).attr("apartmentModelUrl");
                	var version = $(this).attr("version");
                	var rv = '{"url":"' + url + '","version":' + version + '}';
                	
                	LoadUnit(rv);
                });
            }
            //不存在数据
            else {
                $first.find("ul.record_list").empty();
                $first.find(".none_record").show().find("p>a").html($("#mq").val());
                $first.find(".apartment_record").hide();

            }
        }
        if (result.Message == "false") {
            $.jBox.tip("搜索处理出错或超时");
        }
        if (result.Message == "nodata") {
            $first.find("ul.record_list").empty();
            $first.find(".none_record").show().find("p>a").html($("#mq").val());
            $first.find(".apartment_record").hide();
        }
    });
};

//获取保利项目户型数据
handle.fn.getPolyFloor = function (dataset) {

};

//地区联动处理
handle.prototype.$newApplyFirst.find(".province").click(function () {
    if ($(".province_box").is(":visible")) {
        $(".province_box").hide();
    } else {
        $(".province_box").show();
    }
});

$(".province_box,.city_box").mouseleave(function () {
    var box = $(this)[0];
    if (box.className == "province_box") {
        $(".province_box").hide();
    } else {
        $(".city_box").hide();
    }
});
handle.prototype.$newApplyFirst.find(".city").click(function () {
    if ($(".city_box").is(":visible")) {
        $(".city_box").hide();
    } else {
        $(".city_box").show();
    }
});

/***房屋效果处理 开始***/
var ulApartmentli = { one: handle.prototype.$ulApartment.eq(0), two: handle.prototype.$ulApartment.eq(1), three: handle.prototype.$ulApartment.eq(2), four: handle.prototype.$ulApartment.eq(3), five: handle.prototype.$ulApartment.eq(4) };
$(ulApartmentli.one).click(function () {
    if (!$(this).hasClass("cur")) {
        $(this).css("background", "url('/Content/Images/Unity/unity_apply/building-10-1.png')").addClass("cur");
    } else {
        $(this).css("background", "url('/Content/Images/Unity/unity_apply/building-10.png')").removeClass("cur");
    }
    var dataset = handle.fn.getJoinQuery();
    handle.fn.floorSearch(dataset);
});
$(ulApartmentli.two).click(function () {
    if (!$(this).hasClass("cur")) {
        $(this).css("background", "url('/Content/Images/Unity/unity_apply/building-11-1.png')").addClass("cur");
    } else {
        $(this).css("background", "url('/Content/Images/Unity/unity_apply/building-11.png')").removeClass("cur");
    }
    var dataset = handle.fn.getJoinQuery();
    handle.fn.floorSearch(dataset);
});
$(ulApartmentli.three).click(function () {
    if (!$(this).hasClass("cur")) {
        $(this).css("background", "url('/Content/Images/Unity/unity_apply/building-12-1.png')").addClass("cur");
    } else {
        $(this).css("background", "url('/Content/Images/Unity/unity_apply/building-12.png')").removeClass("cur");
    }
    var dataset = handle.fn.getJoinQuery();
    handle.fn.floorSearch(dataset);
});
$(ulApartmentli.four).click(function () {
    if (!$(this).hasClass("cur")) {
        $(this).css("background", "url('/Content/Images/Unity/unity_apply/building-13-1.png')").addClass("cur");
    } else {
        $(this).css("background", "url('/Content/Images/Unity/unity_apply/building-13.png')").removeClass("cur");
    }

    var dataset = handle.fn.getJoinQuery();
    handle.fn.floorSearch(dataset);
});
$(ulApartmentli.five).click(function () {
    if (!$(this).hasClass("cur")) {
        $(this).css("background", "url('/Content/Images/Unity/unity_apply/building-14-1.png')").addClass("cur");
    } else {
        $(this).css("background", "url('/Content/Images/Unity/unity_apply/building-14.png')").removeClass("cur");
    }
    var dataset = handle.fn.getJoinQuery();
    handle.fn.floorSearch(dataset);
});
/***房屋效果处理 结束***/
handle.prototype.$application.find("div.record_box").mouseleave(function() {
    handle.prototype.$application.find("div.popup_box").hide();
});
handle.prototype.$application.find("div.popup_box").mouseenter(function () {
    handle.prototype.$application.find("div.popup_box").show();
});
handle.prototype.$application.find("div.popup_box").mouseleave(function () {
    handle.prototype.$application.find("div.popup_box").hide();
});
//点击上传户型图(1.验证用户是否登陆，2.验证用户是否已经提交申请户型图制作)
handle.prototype.$application.find(".select_upload").click(function () {
    if ($(this).attr("opened") == "false") {
        $(this).attr("opened", "true");
        $.getJSON("/common/checkunitystate/ApartmentState", function (result) {
            handle.prototype.$application.find(".select_upload").attr("opened", "false");
            if (result.Message == "nologin") {
                handle.fn.showDivLogin();
            }
            if (result.Message == "hasApply") {
                handle.fn.showTipMsg("你已提交户型图制作申请，在此期间不可申请");
            }
            if (result.Message == "ok") {
                handle.fn.showApplySecond();
            }
            if (result.Message == "fail") {
                console.log("程序处理出错");
            }
        });
    }
});
//隐藏放大镜
handle.prototype.$application.find("div.popup_box").click(function () {
    $(this).hide();
});

//面积Options选项选择
handle.prototype.$newApplyFirst.find("ul.option").eq(0).find("li").click(function () {
    $(this).find("i").toggleClass("cur");
    $(this).toggleClass("cur");
    var dataset = handle.fn.getJoinQuery();
    handle.fn.floorSearch(dataset);
});
//户型Options选项
handle.prototype.$newApplyFirst.find("ul.option").eq(1).find("li").click(function () {
    $(this).find("i").toggleClass("cur");
    $(this).toggleClass("cur");
    var dataset = handle.fn.getJoinQuery();
    handle.fn.floorSearch(dataset);
});

//左右滑动切换图片效果
var fnchangeRecordList = function () {
    $("ul.record_list").ZJiaJuControlSwitch(
    {
        leftBtn: "btnRecordLeft", //左移动按钮ID
        rightBtn: "btnRecordRight", //右移动按钮ID
        marginleft: 10, //额外移动边距
        defendRightBgImg: "url('/Content/Images/Unity/unity_apply/right_dark.png')", //不能向右移动的图片
        allowLeftBgImg: "url('/Content/Images/Unity/unity_apply/left_dark.png')", //可以向左移动的图片
        showLength: 4 //内容框显示元素的个数
    });
};


//点击搜索按钮
handle.prototype.$newApplyFirst.find("a.select_button").click(function () {
      var dataset = handle.fn.getJoinQuery();
      handle.fn.floorSearch(dataset);
  });

//使用户型图
handle.fn.applycomfirmAddress = function (dataset) {
    handle.prototype.$newApplyFirst.find("ul.record_list li").find(".use_button").attr("opened", "true");
    $.getJSON("/common/common/ConfirmApartmentInfo", dataset, function (result) {
        if (result.Message == "ok") {
            handle.prototype.$application.hide();
            ComfirmAddress(result.Data);
            handle.prototype.$newApplyFirst.find("ul.record_list li").find(".use_button").attr("opened", "false");
        } else {

        }
    });
};
//验证成功后执行
$.validator.setDefaults({
    submitHandler: function () {
        handle.fn.applicationOps();
    }
});
handle.fn.initValidator();

/***********************************特效展示**********************************************/
$(function () {
    //自动补全
    handle.fn.autoComplete("mq");
    //初始化上传
    handle.fn.initUploadify("select_upload");
    handle.fn.hoverHistory();
    handle.fn.clickExpmenu();
    handle.fn.countProductNumPrice();
    handle.fn.designBroadCasting();
    //进度条右上角关闭
    $(".loading-01 .load-close").click(function () {
        $(this).parents(".loading-box").fadeOut();
    });
    //取消冲突提醒框
    $("#operateTips div.none_tips a").click(function () {
        if ($(this).hasClass(".tipcheck")) {
            $(this).css("background", "url('/Content/Images/Unity/unity_apply/tips_btn02.png?v={0}')").removeClass(".tipcheck");
        } else {
            $(this).css("background", "url('/Content/Images/Unity/unity_apply/tips_btn01.png?v={0}')").addClass(".tipcheck");
        }
    });
    /*8.20添加*/
    setInterval(function () {
        var noA = $(".nav_line li").length;
        var noB = Math.floor(Math.random() * noA);
        $(".nav_line li").eq(noB).find("img").animate({
            width: "12px",
            height: "12px",
            marginLeft: "-2px",
            marginTop: "-2px",
            opacity: "0.6"
        }, 300).animate({
            width: "8px",
            height: "8px",
            marginLeft: "0",
            marginTop: "0",
            opacity: "1"
        }, 300);

    }, 600);
    /*8.20添加 end*/

    /*8.21修改*/
    var inB = $(".control input, .captcha input");
    inB.focus(function () {
        $(this).parent().children("textarea").hide();
    });
    inB.blur(function () {
        if ($(this).val() == "" || $(this).val() == null) {
            $(this).parent().children("textarea").show();
        }
    });
    $(".tips").focus(function () {
        $(this).hide();
        $(this).siblings("input").focus();
    });
    //楼盘、户型、面积选择
    $(".application_second form span").click(function () {
        $(this).children("i").toggleClass("cur");
    });

    //点击后字体变颜色、头部变化
    function togCur() {
        $(".sort_tada ul li").click(function () {
            $(this).toggleClass("cur");
        });
    }
    togCur();
    //点击分类
    $(".sort_title .title_01").click(function () {
        $(".sort_box").toggle();
    });
    //点击新建分类
    $(".sort_add .add_01").click(function () {
        $(this).siblings(".add_02").show();
        if ($(".sort_tada ul li").length > 0) {
            $(".sort_tada ul").css("height", "26px");
            $(".add_03, .add_04").css("marginTop", "3px");
        } else {
            $(".sort_tada ul").css("height", "0");
            $(".add_03, .add_04").css("marginTop", "30px");
        }
    });
    //点击创建
    $(".add_02 a").click(function () {
        var txt = $("#add_txt").val();
        var li = $("<li></li>");
        var sp_01 = $("<span class='data_icon'></span>");
        var sp_02 = $("<span class='data_txt'></span>");
        var sp_03 = $("<span class='data_No'></span>");
        if (0 >= txt.length) {
            $(".add_03").show();
            $(".add_04").hide();
        } else if (txt.length > 10) {
            $(".add_03").hide();
            $(".add_04").show();
        } else {
            $(".add_01").show().siblings().hide();
            sp_02.text(txt);
            sp_03.text("(" + 1 + ")");
            li.append(sp_01, sp_02, sp_03);
            $(".sort_tada ul").css("height", "52px").prepend(li).children("li");
            $(".sort_tada ul li").first().addClass("cur");
            $("#add_txt").val("");
        }
        $(".sort_tada ul li").unbind("click");
        togCur();
    });
});

/***********************************注册begin**********************************************/

var sendpath = "/Customer/BaseCustomer/";
function VerifyMyname(name) {
    $("#" + name + "tips").hide();
    var result = check();
    //console.log("result:" + result);
    if (result == 0) {
        $("#" + name + "Error").show();
        $("#" + name + "Error span").html("请输入您的有效手机号或邮箱");
        return false;
    }
    if (result == 2) {
        $("#" + name + "Error").show();
        $("#" + name + "Error span").html("不支持用户名注册,只支持邮箱或手机号注册");
        return false;
    } else {
        $("#" + name + "tips").hide();
        //验证用户名是否存在
        var data = verifyMynameIsExist(name, result);
        //console.log(data);
        if (!data) return false;
    }
    return true;
}

function FocusMyName() {
    $('span.sty_remind').hide();
    $("p.sty_txt").hide();
    $("#identifycodeError").hide();
    $("#mynametips").show();
}

function FocusPwd() {
    $('span.sty_remind').hide();
    $("p.sty_txt").hide();
    $("#identifycodeError").hide();
    $("#passwordtips").show();
}

function FocusConfirmPwd() {
    $('span.sty_remind').hide();
    $("#confirmPwdError").hide();
    $('#confirmPwdtips').show();
}

function FocusCode() {
    $('#identifycodeError').hide();
}
function check() {
    var myname = document.getElementById("myname");
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    var mynamevalue = $.trim(myname.value);
    if (myname.value.length <= 0) {
        return 0;
    }
    //console.log((/^1[3|4|5|8][0-9]\d{4,8}$/.test(myname.value)));
    if ((/^1[3|4|5|8][0-9]\d{4,8}$/.test(mynamevalue))) {
        //手机
        return mynamevalue;
    } else if (myreg.test(mynamevalue)) {
        //邮箱
        return mynamevalue;
    }
    // myname.focus();
    //
    return 2;
}

function VerifyPasswordComplexity(o) {
    $("#" + o + "Error").hide();
    $("#" + o + "tips").hide();
    //触发校验
    $("#" + o).keyup(function () {
        val = $(this).val();
        f = checkStrong(val);
        //console.log("密码强度等级检测" + f);
        //console.log(f);
        showTip(f);
    });
}

function VerifyPassword(name) {
    var o = document.getElementById(name);
    var value = $.trim(o.value);
    $("#" + name + "tips").hide();
    var flag = checkStrong(value);
    // console.log("密码强度等级检测"+flag);
    if (value.length <= 0) {
        $("#passwordstrong").hide();
        $("#" + name + "Error").show();
        $("#" + name + "Error span").html("请输入密码(6-20位,必须含有数字和字母)");
        return false;
    }
    if (value.length < 6 || value.length > 20) {
        $("#passwordstrong").hide();
        $("#" + name + "Error").show();
        $("#" + name + "Error span").html("密码长度应该在6-20位之间");

        // o.focus();
        return false;
    }
    if (flag < 2) {
        $("#passwordstrong").hide();
        $("#" + name + "Error").show();
        $("#" + name + "Error span").html("密码过于简单(6-20位,必须含有数字和字母)");
        // o.focus();
        return false;
    }
    return true;
}

function VerifyConfirmPwd(name) {
    $("#" + name + "Error").hide();
    var o = document.getElementById(name);
    var comfirmpwd = $.trim(o.value);
    var pwd = document.getElementById("password").value;
    if (comfirmpwd.length <= 0) {
        $("#" + name + "Error").show();
        $("#" + name + "Error span").html("请输入确认密码");
        // o.focus();
        return false;
    }
    if (comfirmpwd != pwd) {
        $("#" + name + "Error").show();
        $("#" + name + "Error span").html("确认密码不正确");
        //o.focus();
        return false;
    }
    return true;
}

function showTip(flag) {
    switch (flag) {
        case 0:
            $("#lowercolor").addClass("cur");
            $("#highcolor").removeClass("cur");
            $("#middlecolor").removeClass("cur");
            $("#passwordstrong").hide();
            break;
        case 1:
            $("#lowercolor").addClass("cur");
            $("#highcolor").removeClass("cur");
            $("#middlecolor").removeClass("cur");
            $("#passwordstrong").hide();
            break;
        case 2:
            $("#middlecolor").addClass("cur");
            $("#highcolor").removeClass("cur");
            $("#lowercolor").removeClass("cur");
            $("#passwordstrong").show();
            break;
        default:
            $("#highcolor").addClass("cur");
            $("#middlecolor").removeClass("cur");
            $("#lowercolor").removeClass("cur");
            $("#passwordstrong").show();
    }
}
//CharMode函数
//测试某个字符是属于哪一类.
function CharMode(iN) {
    if (iN >= 48 && iN <= 57) //数字
        return 1;
    if (iN >= 65 && iN <= 90) //大写字母
        return 2;
    if (iN >= 97 && iN <= 122) //小写
        return 4;
    else
        return 8; //特殊字符
}
//bitTotal函数
//计算出当前密码当中一共有多少种模式
function bitTotal(num) {
    modes = 0;
    for (i = 0; i < 4; i++) {
        if (num & 1) modes++;
        num >>>= 1;
    }
    return modes;
}
//返回密码的强度级别
//function checkStrong(sPW) {
//    if (sPW.length < 6)
//        return 0; //密码太短或全为数字
//    //if (isInteger(sPW)) return 0;
//    Modes = 0;
//    for (i = 0; i < sPW.length; i++) {
//        //测试每一个字符的类别并统计一共有多少种模式.
//        Modes |= CharMode(sPW.charCodeAt(i));
//        // console.log("当前字符的类别统计模式：" + Modes);
//    }
//    return bitTotal(Modes);
//}
//检验密码是否全为数字
function isInteger(s) {
    var isInteger = RegExp(/^[0-9]+$/);
    return (isInteger.test(s));
}

//简单的密码强度检测
function checkStrong(sValue) {
    var modes = 0;
    //正则表达式验证符合要求的
    if (sValue.length <= 6) return modes;
    if (/\d/.test(sValue)) modes++; //数字
    if (/[a-z]/.test(sValue)) modes++; //小写
    if (/[A-Z]/.test(sValue)) modes++; //大写
    if (/\W/.test(sValue)) modes++; //特殊字符

    //逻辑处理
    switch (modes) {
        case 1:
            return 1;
            break;
        case 2:
            return 2;
        case 3:
        case 4:
            return sValue.length < 16 ? 3 : 4;
            break;
    }
}
function sendMessage(o) {
    var myname = document.getElementById("myname");
    var result = check();
    //verifyMynameIsExist("", result);
    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    $.post(sendpath + "IsExsitMyName", { myname: result }, function (data) {
        if (data.Message != "exsit") {
            if (/^1[3|4|5|8][0-9]\d{4,8}$/.test(result)) {
                sendPhone(result);
                //120s后获取验证码
                time($(o));
                return;
            } else if (myreg.test(result)) {
                sendEmail(result);
                //120s后获取验证码
                time($(o));
                return;
            } else if (result == 2 || result == 0) {
                $("#identifycodeError").show();
                $("#identifycodeError span").html("请填写正确的手机号或邮箱");
                return;
            }
        }
    });
}

function sendPhone(result) {
    $.post(sendpath + "SendRegisterPhone", { phone: result }, function (data) {
        //console.log(data);
        if (data.Message == "fail") {
            $("#identifycodeError").show();
            $("#identifycodeError span").html("验证码发送失败,请稍后重试");
            return;
        } else {
            $("#identifycodetips").show();
        }
    });
}

function sendEmail(result) {
    $.post(sendpath + "SendRegisterEmail", { email: result }, function (data) {
        //console.log(data);
        if (data.Message == "fail") {
            $("#identifycodeError").show();
            $("#identifycodeError span").html("验证码发送失败,请稍后重试");
            return;
        } else {
            $("#identifycodetips").show();
        }
    });
}
//验证用户名是否存在
function verifyMynameIsExist(name, myname) {
    var o = document.getElementById("myname");
    var result = false;
    $.ajax({
        url: sendpath + "IsExsitMyName",
        data: { myname: myname },
        async: false,
        success: function (data) {
            $(o).show();
            if (data.Message == "exsit") {

                $("#" + name + "Error").show();
                $("#" + name + "Error span").html("注册的手机号或邮箱已存在");
                //  return false;
            } else if (data.Message == "emailprovidernoexsit") {
                $("#" + name + "Error").show();
                $("#" + name + "Error span").html("邮箱类型可能无法识别,建议您使用QQ邮箱");
                //  return false;
            } else {
                result = true;
            }
        }
    });
    return result;
}

//验证验证码格式
function VerifyIdentifyCode(name) {
    var o = document.getElementById(name);
    if (o.value.length != 6) {
        $("#identifycodeError").show();
        $("#identifycodeError span").html("验证码不正确");
        return false;
    }

    if (!/^\d{6}$/.test(o.value)) {
        $("#identifycodeError").show();
        $("#identifycodeError span").html("验证码不正确");
        return false;
    }
    return true;
}
//验证码倒计时
var wait = 120;//时间
function time(o, p) {//o为按钮的对象，p为可选，这里是60秒过后，提示文字的改变
    if (wait == 0) {
        o.removeAttr("disabled");
        o.val("点击发送验证码");//改变按钮中value的值
        //p.html("如果您在1分钟内没有收到验证码，请检查您填写的手机号码是否正确或重新发送");
        wait = 120;
    } else {
        o.attr("disabled", true);//倒计时过程中禁止点击按钮
        //o.removeAttr("onclick");
        o.val(wait + "秒后重新获取");//改变按钮中value的值
        wait--;
        setTimeout(function () {
            time(o, p); //循环调用
        },
            1000);
    }
}
/*************************************注册end***********************************************/

/*************************************登录begin*********************************************/
function checkUserName(id) {
    var myname = document.getElementById(id);
    var regphone = /^1[3|4|5|8][0-9]\d{4,8}$/;
    var regemail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    //大小写英文字母、汉字、数字、下划线
    var regname = /^([u4e00-u9fa5]|[ufe30-uffa0]|[a-za-z0-9_]){6,18}$/;

    var myvalue = $.trim(myname.value);
    if (regphone.test(myvalue)) {
        return true;
    } else if (regemail.test(myvalue)) {
        return true;
    } else if (regname.test(myvalue)) {
        return true;
    } else {
        $("p.sty_txt").hide();
        $("#" + id + "Error").show();
        $("#" + id + "Error  span").html("用户名输入不正确");
        return false;
    }
}

function checkYouPwd(id) {
    //必须含有字母和数字
    var regpwd = /[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/;
    var mypassword = document.getElementById(id);
    if (mypassword.value.length < 6 || mypassword.value.length > 20) {
        $("p.sty_txt").hide();
        $("#" + id + "Error").show();
        $("#" + id + "Error span").html("密码应该在6-20位之间");
        return false;
    }
    if (!regpwd.test(mypassword.value)) {
        $("p.sty_txt").hide();
        $("#" + id + "Error").show();
        $("#" + id + "Error span").html("密码不正确");
        return false;
    }
    return true;
}

function checkVerifyCode(id) {
    var verifycode = document.getElementById(id);
    if (verifycode == null) return true;
    if (verifycode.value.length != 4 || !isInteger(verifycode.value)) {
        $("p.sty_txt").hide();
        $("#" + id + "Error").show();
        $("#" + id + "Error span").html("验证码不正确");
        return false;
    }
}
/*************************************登录end*********************************************/

/**************************************快捷登录begin**************************************/
function fastlogin_checkmyname(id) {
    return VerifyMyname(id);
}

function fastlogin_checkpassword(id) {
    return VerifyPassword(id);
}

function fastlogin_checkconfirmpwd(id) {
    return VerifyConfirmPwd(id);
}
/**************************************快捷登录end****************************************/


/*初次载入页面或者刷新页面时候，只有一个产品打开，其他隐藏*/
$(".expmenu").children("li:not(:first)").children(".menu").hide();
/*初次载入页面或者刷新页面时候，只有一个产品打开，其他隐藏*/
/*unity插件图片箭头*/
setInterval(function() {
    $(".download .arrow_arrow").animate({ "marginTop": "55px" },500).animate({ "marginTop": "40px" },500);
}, 1000);
/*unity插件图片箭头 end*/