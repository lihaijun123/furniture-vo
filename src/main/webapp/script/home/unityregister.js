//验证码倒计时
var wait = 60; //时间
var sendpath = '/Customer/BaseCustomer/';
$(function () {
    $("form#register_form").validate({
        //错误显示
        errorPlacement: function (error, element) {
            if (element.attr("id") === "identifycode") {
                $("#invitecodeErrorRegister").append(error);
            } else {
                element.next().append(error);
            }
        },
        rules: {
            //用户名验证
            myname: {
                required: true
                //isPhoneOrEmail: true,
                //validate远程验证
                /*remote: {
                    url: sendpath + 'CheckUserName',
                    type: 'get',
                    data: {
                        myname: function () {
                            return $("#myname").val();
                        }
                    }
                }*/
            },
            //密码验证
            password: {
                required: true,
                minlength: 3,
                maxlength: 20
                //isPasswordCheck: true
            },
            //确认密码验证
            confirmPwd: {
                required: true,
                equalTo: '#password'
            },
            //验证码验证
            verifyCodeReg: {
                required: true,
                minlength: 4,
                maxlength: 4
                //validate远程验证
                /*remote: {
                    url: sendpath + 'ValidateVerifyCode',
                    type: 'get',
                    data: {
                        verifycode: function () {
                            return $("#identifycode").val();
                        },
                        type: function () {
                            var data = $("#myname").val();
                            if (/^1\d{10}$/.test(data)) return "phone";
                            else return "email";
                        }
                    }
                }*/
            }
        },
        messages: {
            myname: {
                required: "<span></span>用户名不能为空"
                //isPhoneOrEmail: "<span></span>请输入用户名",
                //remote: "<span></span>输入的手机/邮箱已存在"
            },
            password: {
                required: "<span></span>密码不能为空",
                minlength: "<span></span>输入的密码不能小于3个字符",
                maxlength: "<span></span>输入的密码不能大于20个字符"
               // isPasswordCheck: "<span></span>密码至少需使用字母、数字或符号两种以上组合"
            },
            confirmPwd: {
                required: "<span></span>确认密码不能为空",
                equalTo: "<span></span>两次输入密码不一致"
            },
            identifycode: {
                required: "<span></span>验证码不能为空",
                minlength: "<span></span>验证码不能小于4个字符",
                maxlength: "<span></span>验证码不能大于4个字符"
                //remote: "<span></span>验证码不正确"
            }
        }
    });

    //unity弹窗获取验证码
    $("input#getverifycodebtn").click(function () {
        $("#invitecodeErrorRegister").empty();
        var result = $("#myname").val();
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        $.get(sendpath + "CheckUserName", { myname: result }, function (data) {
            if (data === "false") {
                $("#invitecodeErrorRegister").append("<label class='error' generated='true' ><span></span>无法发送验证码,请检查输入</label>");
            } else if (data === "true") {
                if (/^1\d{10}$/.test(result)) {
                    sendtime($("#getverifycodebtn"));
                    sendPhone(result);
                } else if (myreg.test(result)) {
                    sendtime($("#getverifycodebtn"));
                    sendEmail(result);
                }
            }
        });
    });
});

//unity弹窗注册
function ajaxSubmit() {
    var validate = $("form#register_form").valid();
    var option = {
        url: sendpath + '/Register',
        data: $("form#register_form").serialize(),
        type: 'post',
        success: registerresponse
    };
    if (validate) {
        $("form#register_form").ajaxSubmit(option);
        return;
    }
}
//弹窗注册后响应
function registerresponse(data) {
    if (data.Message === "error") {
        var msg = eval(data.Data);
        console.log(msg);
        for (var i = 0; i < msg.length; i++) {
            $("#" + msg[i].key).next().html("<label class=\"error\" ><span></span>" + msg[i].msg + "</label>");
        }

        return;
    } else {
        $("div.login").hide();
        //加载登录版头
        $("div.all_header").remove();
        var href = "/Cart/Shoppingcart/Index";
        var $header = $("#header");
        $header.append("<div class=\"all_header\">");
        $header.find(".all_header").append("<span class=\"sp01\"><a href=\"/Centent/Help/Index\"><b class=\"img\"></b>帮助中心</a></span> <span class=\"sp02\"><a href=\"/Customer/MyCollection/Index\"><b class=\"img\"></b>收藏夹&nbsp;&nbsp;&nbsp;</a>|</span> ");
        $header.find(".all_header").append("<span class=\"sp03\"><a href=\"" + href + "\"><b class=\"img\"></b>购物车</a><a href=\"" + href + "\" class=\"No\">0</a>&nbsp;&nbsp;&nbsp;|</span>");
        $header.find(".all_header").append("<span class=\"sp04\">Hi~[&nbsp;<a href=\"/Customer/UserCenter/Index\" class=\"log_in\">" + data.Data.NickId + "</a>&nbsp;]&nbsp;[&nbsp;<a href=\"/Customer/BaseCustomer/Logout\">退出</a>&nbsp;]&nbsp;<a id=\"check_hasDesign\" style=\"display: none\" href=\"/Unity/Unity/Details\"></a>&nbsp;<a id=\"check_lightMap\" style=\"display: none\" href=\"/Unity/Unity/Details\"></a>&nbsp;&nbsp;&nbsp;|</span>");
        $header.find(".all_header").append("<a class=\"left\" title=\"智家居\" href=\"/\"></a>");
        showUnity();
    }
    //console.log(data);
}

//==================copy from register.js 2015/03/26 begin======================

//发送手机验证码
function sendPhone(result) {
    $.post(sendpath + "SendRegisterPhone", { phone: result }, function (data) {
        if (data.Message == "fail") {
            $("#invitecodeErrorRegister").append("<label class='error' generated='true' ><span></span>验证码发送失败,请稍后重试</label>");
            return;
        } else {
            $("#invitecodeErrorRegister").empty();
        }
    });
}

//发送邮箱验证码
function sendEmail(result) {
    $.post(sendpath + "SendRegisterEmail", { email: result }, function (data) {
        if (data.Message == "fail") {
            $("#invitecodeErrorRegister").append("<label class='error' generated='true' ><span></span>验证码发送失败,请稍后重试</label>");
            return;
        } else {
            $("#invitecodeErrorRegister").empty();
        }
    });
}

function sendtime(o, p) { //o为按钮的对象，p为可选，这里是60秒过后，提示文字的改变
    if (wait == 0) {
        o.removeAttr("disabled");
        o.val("发送验证码"); //改变按钮中value的值
        //p.html("如果您在1分钟内没有收到验证码，请检查您填写的手机号码是否正确或重新发送");
        wait = 60;
    } else {
        o.attr("disabled", true); //倒计时过程中禁止点击按钮
        //o.removeAttr("onclick");
        o.val(wait + "秒后重新获取"); //改变按钮中value的值
        wait--;
        setTimeout(function () {
            time(o, p); //循环调用
        },
            1000);
    }
}

//================================== end =======================================