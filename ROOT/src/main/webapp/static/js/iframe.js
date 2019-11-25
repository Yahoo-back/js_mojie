(function($) {
	$.fn.iframe_autoresize = function(option) {
		/**
		 * 版本号
		 */
		this.version = "1.0";

		var $this = $(this);
		var defaults = {
			"height" : 1,
			"width" : 0
		};
		var opts = $.extend(defaults, option);

		resize($this);

		function resize() {
			var $cur = $($this.get(0));// 仅处理第一个

			// 设置高度
			if (opts.height != 0) {
				var height = $(window).height();
				if (opts.height < 0) {
					height = height + opts.height;
					if ($(window).height() - height + opts.height < 4) {
						// 解决IE的4像素问题，IE的应用可能有一些限制
						$("html").css("overflow-y", "hidden");
					}
				} else {
					if (opts.height > 1) {
						opts.height = 1;
					}
					height = $(window).height() * opts.height;
					if ($(window).height() - height < 4) {
						// 解决IE的4像素问题，IE的应用可能有一些限制
						$("html").css("overflow-y", "hidden");
					}
				}
				$cur.height(height - 115);
			}

			// 设置宽度
		}

		$(window).resize(function() {
			resize();
		});
	}
})(jQuery);