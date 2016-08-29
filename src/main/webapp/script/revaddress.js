
$(function(){
	var province = $("#province").attr("value")=="" ? "请选择" : $("#province").attr("value");
	var city = $("#city").attr("value")=="" ? "请选择" : $("#city").attr("value");
	setup(province, city);


	$("#revaddressForm").validate({
    	onfocusout:function(element){$(element).valid();},
    	errorPlacement: function(error, element){
    		error.appendTo(element.parent());
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
			}
    	},
    	submitHandler:function(form){
    		form.submit();
    	}
    });

	$("#backBtn").click(function(){
		window.location.href = "/subaccount";
	});
});
