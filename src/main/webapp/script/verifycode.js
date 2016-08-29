


$(function(){


	$("#ctChange").click(function(){
		$(this).prev().prev().val("");
		$(this).prev().attr("src", "/captchas/" + new Date().getTime() + ".jpg");
	});
	$("#ctChange").prev().click(function(){
		$(this).prev().val("");
		$(this).attr("src", "/captchas/" + new Date().getTime() + ".jpg");
	});
});


var AGENT_LOGIN = "5";
var AGENT_APPLY = "6";
var AGENT_MODIFY_PASSWORD = "7";

        function sendSms(mobile, type){
         	if(mobile && mobile.length === 11){
        		//发送短信
        		$.ajax({
        			url:"/sms/send",
        			data:{
        				mobilePhone:mobile,
        				type:type
        			},
	        		type:"post",
	        		cache:false,
	        		dataType:"json",
	        		success:function(data){

	        		},
	        		error:function(){

	        		}
        		});
        		//倒计时
        		$("#smsCode").val("");
        		var btnText = "获取验证码";
        		var obj = $("#smsCodeBtn");
        		var btnStatus = obj.attr("class", "btn btn-default disabled");
        		var seconds = 60;

        		if(obj.text() === btnText){
        			var t = setInterval(function(){
        				if(seconds <= 0){
        					obj.text(btnText);
        					obj.attr("class", "btn btn-default");
        					window.clearInterval(t);
        				}
        				else {
        					obj.attr("class", "btn btn-default disabled");
        					obj.text(seconds + "秒后重发");
        				}
        				-- seconds;
        			}, 1000);
        		}
        	} else {
        		$("form").validate().element($("#mobilePhone"));
        	}
        }