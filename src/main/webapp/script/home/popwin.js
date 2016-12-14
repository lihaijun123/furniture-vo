
$(function(){
	
	$("#selectHouse").click(function(){
		 //showLoginBox();
		 //showApplication();
		  Apply3DExperience();
	});
	$("#sharePic").click(function(){
		SharePicture2("http://cdn.jiajia1.com/Storage1/image/2016/12/13/fdc899cd98024013a85931c224c73f0d.png", "http://cdn.jiajia1.com/Storage1/image/2016/12/13/fdc899cd98024013a85931c224c73f0d.png");
	});
});

function house_dialogOpen(){
	Apply3DExperience();
}

function openShoppingCart(){
	var width = window.innerWidth - 100;
	var height = window.innerHeight - 250;
	$.jBox("iframe:http://localhost:7001/fntshoppingcart/home/list", { 
	    title: "购物车", 
	    width: width, 
	    height: height, 
	    buttons: { '关闭': true } 
	});
}