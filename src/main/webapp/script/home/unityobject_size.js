(function($) {
	$.fn.initSize = function() {
		var upW = $(".in_top").width();
		$(this).width(upW * 1);
		$(this).height(($(this).width() * 1) / 2.07);
	};
	$.fn.fullScreenSize = function() {
		var upW = $(".in_top").width();
		$(this).width(upW);
		$(this).height((upW * 1) / 2.07);
	};
})(jQuery);
