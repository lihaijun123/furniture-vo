(function($) {
	$.fn.initSize = function() {
		var upW = $(".in_top").width();
		$(this).width(upW * 0.78);
		$(this).height(($(this).width() * 9) / 16);
	};
	$.fn.fullScreenSize = function() {
		var upW = $(".in_top").width();
		$(this).width(upW);
		$(this).height((upW * 9) / 16);
	};
})(jQuery);
