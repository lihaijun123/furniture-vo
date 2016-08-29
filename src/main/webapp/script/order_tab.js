$(function(){
	var tabIdx = $("#tabIdx").val();
	$("#cTab li:nth-child(" + tabIdx + ")").attr("class", "active");

});
