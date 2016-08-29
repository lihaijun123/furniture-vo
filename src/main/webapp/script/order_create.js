$(function(){

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
		//修改隐藏域
		newTr.find("input[type='hidden']").each(function(){
			//更改name
			var name = $(this).attr("name");
			$(this).attr("name", "items[" + ($("#itemBuyTb tr").length - 2 ) +  "].encryptProductId");

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
			    var price = $(this).find("option:selected").attr("p");
				var currentTr = $(this).parents("tr");
				currentTr.children("td:eq(1)").children().val(price);
				//currentTr.children("td:eq(1)").attr("oriPrice", price);
				//隐藏域
				var productId =  $(this).find("option:selected").val();
				$(this).next().val(productId);
				//数量清零
				$(this).parent().parent().children("td:eq(2)").children("input").val("");
				//总价
				calcTotalPrice();
			});
		});
		//单行数量输入事件绑定
		newTr.find("input[type='text']").each(function(){
			$(this).bind("keyup", function() {
				//判断输入是否正整数
				var reg = /^\d+$/;
				if(reg.test($(this).val())){

				} else {
					$(this).val("");
				}
				//根据数量计算价格
				var selectObj = $(this).parent().parent().children("td:eq(0)").children("select");
				var prodSn = selectObj.val();
				var prodName = selectObj.find("option:selected").text();
				var itemAmount = $(this).val();
				getItemPrice(prodSn, prodName, itemAmount, $(this));
				//总价
				calcTotalPrice();
			});
		});

		//设置默认值
		newTr.children("td:eq(1)").children().val(0);
		newTr.children("td:eq(2)").children().val(0);
		//newTr.children("td:eq(1)").attr("oriPrice", 0);
		//增加一行
		newTr.insertBefore("#itemBuyTb tr:last");

	});

	//删除行
	$("a[id^='rm_line_']").click(function() {
		if(($("#itemBuyTb tr").length - 2) > 1){
			$(this).parent().parent().remove();
			//总价
			calcTotalPrice();
		}
	});

	//下拉框事件
	$("select[id^='product_list_']").on("click",function() {
	    var price = $(this).find("option:selected").attr("p");
		var currentTr = $(this).parents("tr");
		currentTr.children("td:eq(1)").children().val(price);
		//currentTr.children("td:eq(1)").attr("oriPrice", price);
		//隐藏域
		var productId =  $(this).find("option:selected").val();
		$(this).next().val(productId);
		//数量清零
		$(this).parent().parent().children("td:eq(2)").children("input").val("");
		//总价
		calcTotalPrice();
	});

	//数量输入框事件
	$("#itemBuyTb  input[type='text']").keyup(function() {
		//判断输入是否正整数
		var reg = /^\d+$/;
		if(reg.test($(this).val())){

		} else {
			$(this).val("");
		}
		//根据数量计算价格
		var selectObj = $(this).parent().parent().children("td:eq(0)").children("select");
		var prodSn = selectObj.val();
		var prodName = selectObj.find("option:selected").text();
		var itemAmount = $(this).val();
		getItemPrice(prodSn, prodName, itemAmount, $(this));
		//总价
		calcTotalPrice();
	});
	//发票
	$("input[type='checkbox']").click(function(){
		 if($(this).is(':checked')) {
			 $("#invoiceContent").show();
		 } else {
			 $("#invoiceContent").hide();
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
    			form.submit();
    		} else {
    			$("#messageDiv").show();
    			setTimeout("$(\"#messageDiv\").hide()", 3000);
    		}
    	}
    });
});

//计算总价
function calcTotalPrice(){
	//总价
	var totalMoney = 0;
	$("#itemBuyTb input[type='text']").each(function(){
		var num = $(this).val();
		var reg = /^\d+$/;
		if(reg.test(num)){
			var price = $(this).parent().parent().children("td:eq(1)").children().val().trim();
			totalMoney += (num * price);
		}
	});
	$("#totalMoney").text(totalMoney.toFixed(2));
}

function getItemPrice(prodSn, prodName, itemAmount, obj){
	if(prodName != "" && itemAmount != "" && prodName != "请选择"){
		$.ajax({
			url : "/order/getItemPrice",
			data : {
				"prodSn" : prodSn,
				"prodName" : prodName,
				"itemAmount" : itemAmount
			},
			type : "get",
			cache:false,
			async: false,
			dataType:"json",
			success : function(result) {
				var prodSn = result.prodSn;
				var prodName = result.prodName;
				var itemAmount = result.itemAmount;
				var itemPrice = result.itemPrice;
				//价格
				obj.parent().parent().children("td:eq(1)").children("input").val(itemPrice);
				//产品sn隐藏字段
				obj.parent().parent().children("td:eq(0)").children("input").val(prodSn);
				//计算总价
				calcTotalPrice();
			}
		});
	}
}