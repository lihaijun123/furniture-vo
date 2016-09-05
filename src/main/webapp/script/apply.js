
$(function(){
	
	$("#applyForm").validate({
    	onfocusout:function(element){$(element).valid();},
    	errorPlacement: function(error, element){
    		if (element.attr("name") == "smsCode" || element.attr("name") == "validCode"){
    			error.appendTo(element.parent().parent());
    		} else {
    			error.appendTo(element.parent());
    		}
    	},
    	rules: {
	    	mobilePhone:{
	    		required: true,
	    		maxlength: 11,
	    		isMobile:true
	    	},
	    	"loginInfo.password":{
	    		required: true,
	    		maxlength: 30
	    	},
	    	"loginInfo.passwordConfirm":{
    			required: true,
    			maxlength: 30,
    			equalTo: "#password"
    		},
	    	validCode:{
	    		required: true,
	    		minlength: 4
	    	}
    	},
    	messages: {
    		mobilePhone: {
	    		required: "请输入您的手机号",
	    		maxlength: "请输入{0}个字以内",
	    		isMobile:"请输入正确的手机号"
	    	},
	    	"loginInfo.password": {
	    		required: "请输入新密码",
	    		maxlength: "请输入{0}个字以内"
	    	},
	    	"loginInfo.passwordConfirm": {
				required: "请再次输入新密码",
				maxlength: "请输入{0}个字以内",
			    equalTo: "两次新密码输入不一致"
			},
			validCode: {
	    		required: "请输入验证码",
	    		minlength: "请输入{0}位验证码"
	    	}
    	},
    	submitHandler:function(form){
    		form.submit();
    	}
    });

	/*//获取手机验证码
    $("#smsCodeBtn").click(function(){
    	sendSms($("#mobilePhone").val(), AGENT_APPLY);
    });*/
});
