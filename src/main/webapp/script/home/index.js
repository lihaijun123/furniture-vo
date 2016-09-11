
var config = {
	/*width: "100%", 
	height: "100%",*/
	params: { enableDebugging:"0",disableContextMenu: true }				
};
			
window.onresize = function(){
	$("#unityPlayer").initSize();
}

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


function createUnityObject(){
	var u = new UnityObject2(config);
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
	u.initPlugin(jQuery("#unityPlayer")[0], "/unity3d/fru.unity3d");
}