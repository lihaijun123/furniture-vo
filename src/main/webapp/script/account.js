
$(function(){
	var province = $("#province").attr("value")=="" ? "请选择" : $("#province").attr("value");
	var city = $("#city").attr("value")=="" ? "请选择" : $("#city").attr("value");
	setup(province, city);
});
