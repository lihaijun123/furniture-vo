
$(function() {
	createUnityObject();
});

var config = {
		/*width: "100%", 
	height: "100%",*/
		params: { enableDebugging:"0",disableContextMenu: true }				
};
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
	u.initPlugin(jQuery("#unityPlayer")[0], "http://139.196.173.139/fru.unity3d");
}

function loginCallBackToUnity(userId){
	u.getUnity().SendMessage("control","LoginSuccess", userId);
}

function LoadUnit(json){
	u.getUnity().SendMessage("control","LoadUnitAssetBundleWeb", json);
}

function LoadFurnitureItem(json){
	u.getUnity().SendMessage("control","LoadFurnitureItem", json);
}

//
$(function(){
	$("a[id^='recommendLink_'").click(function(){
		var url = $(this).attr("url");
    	var version = $(this).attr("v");
    	var houseId = $(this).attr("sn");
    	var rv = '{"url":"' + url + '","version":' + version + ',"id":"' + houseId + '"}';
    	LoadUnit(rv);
	});
});