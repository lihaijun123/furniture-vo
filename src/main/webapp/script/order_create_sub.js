$(function(){
	String.prototype.endWith = function(str){var t = this;return t.substring(t.length-str.length,t.length) == str;};

	//添加行
	$("#addLine").click(function() {
		var newTr = $("#itemBuyTb tr").eq($("#itemBuyTb tr").length - 2).clone();

		//修改删除链接属性
		newTr.find("a[id^='rm_line_']").each(function(){
			//更改name
			var name = $(this).attr("id");
			$(this).attr("id", "rm_line_" + ($("#itemBuyTb tr").length - 2));
			$(this).show();
			//绑定删除行事件
			$(this).bind("click", function(){
				if(($("#itemBuyTb tr").length - 2) > 1){
					$(this).parent().parent().remove();
					//总价
					calcTotalPrice();
				}
			});
		});
		//修改价格字段
		newTr.find("td:eq(1)").each(function(){
			var name = $(this).children().attr("name");
			$(this).children().attr("name", "items[" + ($("#itemBuyTb tr").length - 2 ) +  "].itemPrice");
		});
		//修改数量
		newTr.find("td:eq(2)").each(function(){
			var name = $(this).children().attr("name");
			$(this).children().attr("name", "items[" + ($("#itemBuyTb tr").length - 2 ) +  "].itemAmount");
		});
		//select事件绑定
		newTr.find("select[id^='product_list_']").each(function(){
			$(this).bind("click", function() {
				//数量清零
				$(this).parent().parent().children("td:eq(2)").children("input").val("");
				//总价
				calcTotalPrice();
			});
		});
		//单行数量输入事件绑定
		newTr.find("input[name$='itemAmount']").each(function(){
			$(this).bind("keyup", function() {
				//判断输入是否正整数
				var reg = /^\d+$/;
				if(reg.test($(this).val())){

				} else {
					$(this).val("");
				}
				//总价
				calcTotalPrice();
			});
		});
		newTr.find("input[name$='itemPrice']").each(function(){
			$(this).bind("keyup", function() {
				//判断输入是否正整数
				var reg = /^\d+\.?(\d*)$/;
				if(reg.test($(this).val())){

				} else {
					$(this).val("");
				}
				//总价
				calcTotalPrice();
			});
		});
		newTr.find("input[name$='itemPrice']").each(function(){
			$(this).bind("blur", function() {
				//判断输入是否正整数
				var reg = /^\d+\.?(\d*)$/;
				if(reg.test($(this).val())){
					if($(this).val().endWith(".")){
						var vl = $(this).val();
						$(this).val(vl.substring(0, vl.indexOf(".")));
					}
				} else {
					$(this).val("");
				}
				//总价
				calcTotalPrice();
			});
		});
		//设置默认值
		newTr.children("td:eq(1)").children().val(0);
		newTr.children("td:eq(2)").children().val(0);
		//增加一行
		newTr.insertBefore("#itemBuyTb tr:last");

	});
	//数量输入框事件
	$("input[name$='itemPrice']").keyup(function() {
		//判断输入是否正整数
		var reg = /^\d+\.?(\d*)$/;
		if(reg.test($(this).val())){

		} else {
			$(this).val("");
		}
		var itemAmount = $(this).val();
		//总价
		calcTotalPrice();
	});
	$("input[name$='itemPrice']").blur(function() {
		//判断输入是否正整数
		var reg = /^\d+\.?(\d*)$/;
		if(reg.test($(this).val())){
			if($(this).val().endWith(".")){
				var vl = $(this).val();
				$(this).val(vl.substring(0, vl.indexOf(".")));
			}
		} else {
			$(this).val("");
		}
		var itemAmount = $(this).val();
		//总价
		calcTotalPrice();
	});
	$("input[name$='itemAmount']").keyup(function() {
		//判断输入是否正整数
		var reg = /^\d+$/;
		if(reg.test($(this).val())){

		} else {
			$(this).val("");
		}
		var itemAmount = $(this).val();
		//总价
		calcTotalPrice();
	});
	$("input[name$='logtcPrice']").keyup(function() {
		//判断输入是否正整数
		var reg = /^\d+\.?(\d*)$/;
		if(reg.test($(this).val())){

		} else {
			$(this).val("");
		}
		//总价
		calcTotalPrice();
	});

	$("input[type='checkbox']").click(function(){
		if($(this).is(':checked')){
			$("#logtcPrice").hide();
		} else {
			$("#logtcPrice").show();
		}
	});
	var province = $("#province").attr("value")=="" ? "请选择" : $("#province").attr("value");
	var city = $("#city").attr("value")=="" ? "请选择" : $("#city").attr("value");
	setup(province, city);


	$("#createForm").validate({
    	onfocusout:function(element){$(element).valid();},
    	errorPlacement: function(error, element){
    		error.appendTo(element.parent());
    	},
    	rules: {
    		"receiveAddress.userName":{
    			required: true,
    			maxlength: 30
    		},
    		"receiveAddress.mobilePhone":{
	    		required: true,
	    		maxlength: 11,
	    		isMobile:true
	    	},
	    	"receiveAddress.street":{
    			required: true,
    			maxlength: 90
    		}
    	},
    	messages: {
    		"receiveAddress.userName":{
				required: "请输入您的姓名",
				maxlength: "请输入{0}个字以内"
			},
			"receiveAddress.mobilePhone": {
	    		required: "请输入您的手机号",
	    		maxlength: "请输入{0}个字以内",
	    		isMobile:"请输入正确的手机号"
	    	},
	    	"receiveAddress.street": {
    			required: "请输入您的街道地址",
    			maxlength: "请输入{0}个字以内"
    		}
    	},
    	submitHandler:function(form){
    		var totalMoney = $("#totalMoney").text();
    		if(totalMoney !== "0.00" && totalMoney !== "0"){
    			//运费检查
    			var logtcPrice = $("#logtcPrice").val();
    			if(!$("input[type='checkbox']").is(':checked') && (logtcPrice == "" || logtcPrice == "0.00" || logtcPrice == "0")){
    				$("#messageDiv").text("请选择是否收运费。");
    				$("#messageDiv").show();
    				setTimeout("$(\"#messageDiv\").hide()", 5000);
    			} else {
    				form.submit();
    			}
    		} else {
    			$("#messageDiv").text("请选择对应的产品和填写购买数量。");
    			$("#messageDiv").show();
    			setTimeout("$(\"#messageDiv\").hide()", 5000);
    		}
    	}
    });
});

//计算总价
function calcTotalPrice(){
	//总价
	var totalMoney = 0;
	$("#itemBuyTb tbody tr").each(function(){
		var num = 0;
		var price = 0;
		$(this).find("input[name^='items[']").each(function(){
			if($(this).attr("name").indexOf("itemAmount") != -1){
				num = $(this).val();
			}
			if($(this).attr("name").indexOf("itemPrice") != -1){
				price = $(this).val();
			}
		});
		totalMoney += (num * price);
	});
	var logtcPrice = parseFloat($("#logtcPrice").val());
	if(logtcPrice > 0){
		totalMoney += logtcPrice;
	}
	$("#totalMoney").text(totalMoney.toFixed(2));
	$("#totalPrice").val(totalMoney.toFixed(2));
}