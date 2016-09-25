
var config = {
	/*width: "100%", 
	height: "100%",*/
	params: { enableDebugging:"0",disableContextMenu: true }				
};
			
window.onresize = function(){
	$("#unityPlayer").initSize();
}
var dialogObj;
$(function() {
	$(".in_top a:eq(2)").click(function(){
		$(".in_sjll").fadeOut(1000);
		$(".in_case").fadeOut(1000);
		$("#unityPlayer").fullScreenSize();
	});
	$(".in_top a:eq(3)").click(function(){
		$(".in_sjll").fadeIn(1000);
		$(".in_case").fadeIn(1000);
		$("#unityPlayer").initSize();
	});
	
	$("#houseDialogLk").click(function(){
		houseSelect_dialogOpen();
	});
	$("#productDialogLk").click(function(){
		productSelect_dialogOpen();
	});
	//alert("width:" + upW + ",height:" + window.innerHeight);
	$("#unityPlayer").initSize();
	//alert("width:" + $("#unityPlayer").width() + ",height:" + $("#unityPlayer").height());
	/*
	config.width = window.innerWidth - 700;
	config.height = window.innerHeight - 70;
	alert("width:" + config.width + ",height:" + config.height);
	*/
	createUnityObject();
});
//打开户型选择窗口
function houseSelect_dialogOpen(){
	if(dialogObj){
		dialogObj = null;
	}
	$("iframe").attr("src", "/fnthouse/search");
	dialogObj = $("#dialog-listLk" ).dialog({
	      resizable: true,
	      height:$(".in_top").width() - 500,
	      width:$(".in_top").width() - 200,
	      modal: true
	 });
}
//户型选择框返回的url
function houseSelect_dialogCallbak(jsonStr){
	//alert("户型选择框返回的url:" + jsonStr);
	if(dialogObj){
		dialogObj.dialog("close");
	}
	LoadUnit(jsonStr);
}
//打开添加家具窗口
var prouductDialog;
function productSelect_dialogOpen(){
	if(prouductDialog){
		prouductDialog = null;
	}
	$("iframe").attr("src", "/fntproduct/search");
	prouductDialog = $("#product-dialog-listLk").dialog({
	      resizable: true,
	      height:$(".in_top").width() - 450,
	      width:$(".in_top").width() - 200,
	      modal: true
	 });
}
//添加家具窗口返回的数据
function productSelect_dialogCallbak(jsonStr){
	//alert("户型选择框返回的url:" + jsonStr);
	if(prouductDialog){
		prouductDialog.dialog("close");
	}
	//LoadUnit(jsonStr);
	alert(jsonStr);
}
//unity
var u;
function createUnityObject(){
	u = new UnityObject2(config);
	var $missingScreen = jQuery("#unityPlayer").find(".missing");
	var $brokenScreen = jQuery("#unityPlayer").find(".broken");
	$missingScreen.hide();
	$brokenScreen.hide();
	u.observeProgress(function (progress) {
		switch(progress.pluginStatus) {
			case "broken":
				$brokenScreen.find("a").click(function (e) {
					e.stopPropagation();
					e.preventDefault();
					u.installPlugin();
					return false;
				});
				$brokenScreen.show();
			break;
			case "missing":
				$missingScreen.find("a").click(function (e) {
					e.stopPropagation();
					e.preventDefault();
					u.installPlugin();
					return false;
				});
				$missingScreen.show();
			break;
			case "installed":
				$missingScreen.remove();
			break;
			case "first":
			break;
		}
	});
	u.initPlugin(jQuery("#unityPlayer")[0], "/unity3d/fru.unity3d?v=3");
}

function LoadUnit(json){
	u.getUnity().SendMessage("control","LoadUnitAssetBundle", json);
}