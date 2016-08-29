
$(function(){
	var province = $("#province").attr("value")=="" ? "请选择" : $("#province").attr("value");
	var city = $("#city").attr("value")=="" ? "请选择" : $("#city").attr("value");
	setup(province, city);

	var initJson = {};
	initJson.fileExt = "*.jpg;*.png;";
	initJson.fileDesc = "*.jpg;*.png;";
	veUploadify(initJson, "file_upload1");
	veUploadify(initJson, "file_upload2");


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
    		userName:{
    			required: true,
    			maxlength: 30
    		},
	    	mobilePhone:{
	    		required: true,
	    		maxlength: 11,
	    		isMobile:true
	    	},
	    	street:{
    			required: true,
    			maxlength: 90
    		},
	    	smsCode:{
	    		required: true,
	    		minlength: 6
	    	},
	    	validCode:{
	    		required: true,
	    		minlength: 4
	    	}
    	},
    	messages: {
    		userName:{
				required: "请输入您的姓名",
				maxlength: "请输入{0}个字以内"
			},
    		mobilePhone: {
	    		required: "请输入您的手机号",
	    		maxlength: "请输入{0}个字以内",
	    		isMobile:"请输入正确的手机号"
	    	},
	    	street: {
    			required: "请输入您的街道地址",
    			maxlength: "请输入{0}个字以内"
    		},
	    	smsCode: {
				required: "请输入短信验证码",
				minlength: "请输入{0}位短信验证码"
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

	//获取手机验证码
    $("#smsCodeBtn").click(function(){
    	sendSms($("#mobilePhone").val(), AGENT_APPLY);
    });
});

//需提供文件html元素id
function getfile_upload1Id(){
	return "idCardFileSn";
}
/*function getfile_upload1UrlParam(){
	return {type:"_pic_", cutSize:"400*400"};
}*/
//需提供文件html元素id
function getfile_upload2Id(){
	return "kbisFileSn";
}
/*function getfile_upload1UrlParam(){
	return {type:"_pic_", cutSize:"400*400"};
}*/