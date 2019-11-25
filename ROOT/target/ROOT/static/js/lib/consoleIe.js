/**
 * Created with JetBrains WebStorm. User: ruizhang Date: 13-8-19 Time: 下午4:34 To
 * change this template use File | Settings | File Templates.
 */
(function(window, $) {

	var dd = $("#DEBUG_DIV");

	if (window.console) {

	} else {
		if (dd.size() > 0) {
			var _debug = $("<div>DEBUG:</div>");
			function echo(str) {
				_debug.append($('<div><pre>' + _.escape(str) + '</pre></div>'));
			}
			window.console = {
				log : function(str) {
					echo('LOG:' + str);
				},
				warn : function(str) {
					echo('WARN:' + str);
				},
				debug : function(str) {
					echo('DEBUG:' + str);
				}
			}

			$(function() {
				$("#DEBUG_DIV").append(_debug);
			});
		} else {
			window.console = {
				log : function() {
				},
				warn : function() {
				},
				debug : function() {
				}
			}
		}
	}

})(window, jQuery);