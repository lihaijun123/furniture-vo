
$(function(){
	$("#infomodifyForm").validate({
    	onfocusout:function(element){$(element).valid();},
    	errorPlacement: function(error, element){
    		error.appendTo(element.parent());
    	},
    	rules: {
    		password:{
    			required: true,
    			maxlength: 30
    		},
    		passwordNew:{
	    		required: true,
	    		maxlength: 30
	    	},
    		passwordNewS:{
    			required: true,
    			maxlength: 30,
    			equalTo: "#passwordNew"
    		}
    	},
    	messages: {
    		password:{
				required: "请输入原始密码",
				maxlength: "请输入{0}个字以内"
			},
			passwordNew: {
	    		required: "请输入新密码",
	    		maxlength: "请输入{0}个字以内"
	    	},
			passwordNewS: {
				required: "请再次输入新密码",
				maxlength: "请输入{0}个字以内",
			    equalTo: "两次新密码输入不一致"
			}
    	},
    	submitHandler:function(form){
    		var smsCode = $("#smsCode").val();
    		if(!smsCode){
    			$("#message").text("请输入手机验证码");
    		} else {
    			form.submit();
    		}
    	}
    });
	//修改密码，获取手机验证码
    $("#smsCodeBtn").click(function(){
    	sendSms($("#mobilePhone").val(), AGENT_MODIFY_PASSWORD);
    });
});
