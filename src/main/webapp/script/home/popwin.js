
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

//分享抓图lihaijun
function SharePicture2(backgroundImageData, imageData) {
	hideUnity();
	$("#print_share").show(50);
	changeBackground(backgroundImageData);
    var imghtml = "<img style=\"width: " + 590 + "px;height: " + 328 + "px\"  src=\"" + imageData + "\" shareUrl=\"" + imageData + "\"/>";
    $("#print_shareImgUrl").html("").append(imghtml);
}