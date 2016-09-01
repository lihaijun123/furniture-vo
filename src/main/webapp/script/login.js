$(function(){
	//获取手机验证码
    $("#smsCodeBtn").click(function(){
    	sendSms($("#loginName").val(), AGENT_LOGIN);
    });

    $("#loginForm").validate({
    	onfocusout:function(element){$(element).valid();},
    	errorPlacement: function(error, element){
    		error.appendTo(element.parent().parent());
    	},
    	rules: {
    		loginName:{
    			required: true,
    			maxlength: 11,
    			isMobile:true
    		},
    		password:{
	    		required: true,
	    		maxlength: 40

	    	},
	    	validCode:{
	    		required: true,
	    		minlength: 4
	    	}
    	},
    	messages: {
    		loginName:{
				required: "请输入您的手机号",
				maxlength: "请输入{0}个字以内",
				isMobile:"请输入正确的手机号"
			},
			password: {
	    		required: "请输入您的密码",
	    		maxlength: "请输入{0}个字以内"
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

});