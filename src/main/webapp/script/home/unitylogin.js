﻿//unity弹窗登录
var actionpath = "/common/loginAuth/";
var logout = "/common/logout";
var thirdpartpath = "/Base/SinaOAuth/";
$(function () {
    $("form#login_form").validate({
        //错误显示
        errorPlacement: function (error, element) {
            if (element.attr("id") === "verifyCodelogin") {
                $("#verifyCodeloginError").append(error);
            } else {
                element.next().append(error);
            }
        },
        rules: {
            //用户名验证
            mynamelogin: {
                required: true
                //isPhoneOrEmail: true
            },
            //密码验证
            passwordlogin: {
                required: true
                //minlength: 6,
               // maxlength: 20
                //isPasswordCheck: true
            }

            //验证码验证
            /*verifyCodelogin: {
                required: true,
                minlength: 4,
                maxlength: 4,
                //validate远程验证
                remote: {
                    url: actionpath + 'CheckVerifyCode',
                    type: 'get',
                    data: {
                        verifycode: function () {
                            return $("#verifyCodelogin").val();
                        }
                    }
                }
            }*/
        },
        messages: {
            mynamelogin: {
                required: "<span></span>用户名不能为空",
                isPhoneOrEmail: "<span></span>请输入用户名正确格式"
                //remote: "<span></span>输入的手机/邮箱已存在"
            },
            passwordlogin: {
                required: "<span></span>密码不能为空"
                //minlength: "<span></span>输入的密码不能小于6个字符",
                //maxlength: "<span></span>输入的密码不能大于20个字符",
                //isPasswordCheck: "<span></span>密码至少需使用字母、数字或符号两种以上组合"
            },
            verifyCodelogin: {
                required: "<span></span>验证码不能为空"
               // minlength: "<span></span>验证码不能小于4个字符",
                //maxlength: "<span></span>验证码不能大于4个字符",
               // remote: "<span></span>验证码不正确"
            }
        }
    });

    $("#passwordlogin,#mynamelogin").keyup(function () {
        $("#passwordlogin").next().empty();
    });
});

$("#RegisterRightNow").click(function () {
    $("form.register_form").show();
    $("form.login_form").hide();
    // $("div.login").hide();
});
//lihaijun
$("#loginBtn").click(function () {
    var validate = $("#login_form").valid();
    console.log("validate:" + validate);
    var option = {
        url: actionpath + "login",
        dataType:"json",
        data: $("form#login_form").serialize(),
        type: 'post',
        success: Loginresponse
    };
    if (validate) {
        $("form#login_form").ajaxSubmit(option);
        return;
    }
});

$(function(){
	$("img[id^='vecodeId']").click(function(){
		 $(this).attr("src", "/captchas/" + new Date().getTime() + ".jpg");
	});
	$("#logoutId").click(function(){
		doLogout();
	});
});

function doLogout(){
	$.getJSON(logout, "", function(result) {
		loginCallBackToUnity("");
		$("#all_header2").remove();
        var href = "";
        var $header = $("#header2");
        $header.append("<div class='dh float_l' id='all_header2'>");
        var helpUrl = "#";
        $header.find("#all_header2").append("<a href='#'>购物车0</a> | <a href='#'>收藏夹</a> | <a href='#'>帮助中心</a> | <a href='#'>套入我家</a> | <a href='#'>分享</a> | <a href='/login'>[登陆]</a>");
	});
}
//unity弹窗登录响应
function Loginresponse(data) {
    if (data.Message == "alreadylogin") {
        HideThisDiv("loginAndRegister");
        loginCallBackToUnity(data.userId);
        return;
    }
    if (data.Message == "error") {
        //var msg = eval(data.Data);
        //console.log(msg);
        $("#loginMsg").text(data.Data);
        /*for (var i = 0; i < msg.length; i++) {
            $("#" + msg[i].key).next().html("<label class=\"error\" ><span></span>" + msg[i].msg + "</label>");
        }*/
        $("#vecodeId").attr("src", "/captchas/" + new Date().getTime() + ".jpg");
        return;
    } else if (data.Message == "locked") {
        alert("您已经输入3次密码，账号暂时锁定，15分钟后重新登录");
    }
    else {
        //登录或者注册成功,隐藏登录窗体，弹出Unity Web Player
        HideLoginShowUnity("#loginAndRegister", "");
        $("#all_header2").remove();
        loginCallBackToUnity(data.userId);
        var href = "";
        var $header = $("#header2");
        $header.append("<div class='dh float_l' id='all_header2'>");
        var helpUrl = "#";
        $header.find("#all_header2").append("hi[&nbsp;" + data.userName + "&nbsp;]");
        $header.find("#all_header2").append("| <a href='#'>购物车0</a> | <a href='#'>收藏夹</a> | <a href='#'>帮助中心</a> | <a href='#'>套入我家</a> | <a href='#'>分享</a> | <a href='#' id='logoutId'>[退出]</a>");
        $("#logoutId").bind("click", function(){
        	doLogout();
        });
        CallCricleProcessBar();
    }
}
//============================第三方登录begin==========================================
// 如需添加回调函数，请在wbml标签中添加onlogin="login" onlogout="logout"，并定义login和logout函数。
//WB2.anyWhere(function (W) {
//    W.widget.connectButton({
//        id: "wb_connect_btn",
//        type: "6,2",
//        callback: {
//            login: loginSinaResponse,
//            logout: loginoutSinaResponse
//        }
//    });
//});
////微博登录响应
//function loginSinaResponse(o) {
//    var newobj = {
//        uid: o.idstr,
//        profile_image_url: o.profile_image_url,
//        screen_name: o.screen_name
//    };
//    var newobjstr = JSON.stringify(newobj);
//    $.post(thirdpartpath + "AjaxSinaOAuthCallBack", { userInfo: newobjstr }, function (data) {
//        if (data.Message == "fail") {
//            alert("微博授权失败");
//        } else {
//            window.open(data.Message, '第三方登录授权窗体', 'height=100,width=400,top=0,left=0,toolbar=no,menubar=no,resizable=no,location=no,status=no');
//        }
//    }, "json");
//}
////微博退出响应
//function loginoutSinaResponse(o) {
//}