
$(function(){

	$("#subaccountForm").validate({
    	onfocusout:function(element){$(element).valid();},
    	errorPlacement: function(error, element){
    		error.appendTo(element.parent().parent());
    	},
    	rules: {
    		mobilePhone:{
    			required: true,
    			digits:true,
    			minlength: 11
    		}
    	},
    	messages: {
    		mobilePhone:{
				required: "请输入手机号码",
				digits: "请输入正确的手机号码",
				minlength: "请输入{0}位手机号码"
			}
    	},
    	submitHandler:function(form){
    		form.submit();
    	}
    });
});
