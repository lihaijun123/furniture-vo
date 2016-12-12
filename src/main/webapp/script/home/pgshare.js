$(function(){
	$("#pgShare").click(function(){
		var data = '{type:"sina",title:"分享测试",url:"http://www.3d-focus.com",pic:"http://www.3d-focus.com/images/case_wx_lg.png"}'
			pgShare(data);
		
	});
});
/**
 * 
 * 页面分享
 * 
 */
function pgShare(data){
	/*
	var obj = eval('(' + data + ')');
	var url = "";
	var surl = encodeURIComponent(obj.url);
	var stitle = encodeURIComponent(obj.title);
	var spic = encodeURIComponent(obj.pic);
	var type = obj.type;
	if(type == "qq"){
		url = "http://connect.qq.com/widget/shareqq/index.html?url=" + surl + "&desc=" + stitle + "&pics=" + spic + "&source=''&sourceUrl=''";
	}
	if(type == "sina" ){
		url = "http://service.weibo.com/share/share.php?appkey=1343713053&url=" + surl + "&title=" + stitle + "&pic=" + spic;
	}
	window.open(url);
	*/
	share_dialogOpen(data);
}

var share_dialog = null;
function share_dialogOpen(fileId){
	alert(fileId);
	hideUnity();
	if(share_dialog){
		share_dialog = null;
	}
	$("iframe").attr("src", "/pgshare/" + fileId);
	share_dialog = $("#share-dialog-listLk").dialog({
	      resizable: false,
	      draggable: false,
	      position: {my: "left top", at: "left top", of: "#cont"},
	      height:$(".in_top").width() - 500,
	      width:$(".in_top").width() + 50,
	      modal: true
	 });
}

function toShare(type){
	var url = "";
	var surl = encodeURIComponent("http://139.196.173.139:8888/index");
	var stitle = encodeURIComponent("信利家具");
	var spic = encodeURIComponent($("#picUrl").val());
	if(type == "qq"){
		url = "http://connect.qq.com/widget/shareqq/index.html?url=" + surl + "&desc=" + stitle + "&pics=" + spic + "&source=''&sourceUrl=''";
	}
	if(type == "sina" ){
		url = "http://service.weibo.com/share/share.php?appkey=1343713053&url=" + surl + "&title=" + stitle + "&pic=" + spic;
	}
	window.open(url);
}

	/*
	window._bd_share_config = {
		common : {
			bdText : '自定义分享内容',	
			bdDesc : '自定义分享摘要',	
			bdUrl : 'http://www.3d-focus.com/', 	
			bdPic : 'http://www.3d-focus.com/images/case_wx_lg.png'
		},
		share : [{
			"bdSize" : 16
		}]
	}
	with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
	*/