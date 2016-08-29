(function ($) {
	$.getUrlParam = function (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]); return null;
	}
})(jQuery);

$(function(){
/*	var ptab = $.getUrlParam('ptab');
	var ctab = $.getUrlParam('ctab');
	$("#myTabs li:eq(" + (ctab - 1) + ") a").tab("show");*/
	var type = $("#type").val();
	$("#pNav > li").each(function(){
		var p = $(this);
		var href = p.children().attr("href");
		if(href === "#"){
			p.children().find("a").each(function(){
				if($(this).attr("href").indexOf(type) > 0){
					var cls = p.attr("class");
					p.attr("class", cls + " active");
				}
			});
		} else {
			if(href.indexOf(type) > 0){
				var cls = p.attr("class");
				p.attr("class", cls + " active");
			}
		}
	});
	var u = window.location.pathname;
	$("#cTabs a").each(function(){
		var href = $(this).attr("href");
		if(href === u || href.indexOf(u) > 0){
			//$(this).css("color", "red");
			var cls = $(this).parent().attr("class");
			$(this).parent().attr("class", cls + " active");
		}
	});


});