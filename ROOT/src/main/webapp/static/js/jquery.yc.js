/**
 * 减少代码的输出，将一些能公用的代码抽取出来，以及装一个工具类来使用
 * 
 */
(function($) {
	$.fn.extend({
		/**
		 * 父iframe获得子frame的文本框
		 */
		iframe :function(index) {
			return $(window.frames["layui-layer-iframe" + index].document).find($(this).selector);
		} 
	}), $.extend({
		/**
		 * 传递的参数使用encode来加密，通过decode来解析并且将JSON转成对象
		 */
		openframe : function(title, width, height, url) {
			width = width == "" ? "300px" : width;
			height = height == "" ? "300px" : height;
			parent.layer.open({
				type : 2,
				icon : 0,
				skin : 'layui-layer-rim',
				title : '<i class="fa fa-compass"></i> ' + title,
				maxmin : false,
				area : [ width, height ],
				content : url,
				closeBtn : "1"
			});
		},
		/**
		 * 传递的参数使用encode来加密，通过decode来解析并且将JSON转成对象
		 */
		openframe4Btn : function(title, width, height, url, callback) {
			parent.layer.open({
				type : 2,
				title : '<i class="fa fa-compass"></i> ' + title,
				skin : 'layui-layer-demo', // 样式类名
				closeBtn : 1, // 不显示关闭按钮
				icon : 1,
				anim : 2,
				shadeClose : true, // 开启遮罩关闭
				area : [ width, height ],
				content : url,
				btn : [ '确定', '取消' ] // 只是为了演示
				,
				yes : callback,
				btn2 : function(index) {
					parent.layer.close(index);
				}
			});
		},
		/**
		 * 传递的参数使用encode来加密，通过decode来解析并且将JSON转成对象
		 */
		openWFframe : function(title, width, height, url) {
			parent.layer.open({
				type : 2,
				icon : 0,
				skin : 'layui-layer-rim',
				title : '<i class="fa fa-user-circle-o"></i> 业务处理',
				maxmin : false,
				area : [ "90%", "90%" ],
				content : "/wfm/common?url=" + encodeURIComponent(url),
				closeBtn : "1"
			});
		},
		/**
		 * 传递的参数使用encode来加密，通过decode来解析并且将JSON转成对象
		 */
		openHtmlfragmentByurl : function(width, height, url) {
			parent.layer.open({
				type : 2,
				skin : 'layui-layer-rim',
				title : 'iframe',
				maxmin : true,
				area : [ '300px', '200px' ],
				content : ctx + uri
			});
		},
		/**
		 * 传递的参数使用encode来加密，通过decode来解析并且将JSON转成对象
		 */
		openHtmlfragmentByDom : function(title, id, callback) {
			parent.layer.open({
				type : 1,
				title : '<i class="fa fa-user-circle-o"></i> ' + title,
				skin : 'layui-layer-demo', // 样式类名
				closeBtn : 1, // 不显示关闭按钮
				icon : 1,
				anim : 2,
				shadeClose : true, // 开启遮罩关闭
				area : [ '300px', '200px' ],
				content : $("#" + id).html(),
				btn : [ '确定', '取消' ] // 只是为了演示
				,
				yes : callback,
				btn2 : function(index) {
					parent.layer.close(index);
				}
			});
		},
		getUrlParam : function(url, key) {
			var theRequest = new Object();
			if (url.indexOf("?") != -1) {
				var str = url.substr(1);
				strs = str.split("&");
				for (var i = 0; i < strs.length; i++) {
					theRequest[strs[i].split("=")[0]] = unescape(strs[i]
							.split("=")[1]);
				}
			}
			return theRequest[key];
		},
		// 重写Lay的代码
		layConfirm : function(title, style, callback) {
			style = (style == "" || style == undefined) ? "1" : style;
			return parent.layer.confirm(title, {
				icon : style,
				title : '提示'
			}, callback);
		},
		layAlert : function(title, style) {
			style = (style == "" || style == undefined) ? "1" : style;
			return parent.layer.alert(title, {
				icon : style
			});
		},
		layMsg : function(title, style) {
			style = (style == "" || style == undefined) ? "6" : style;
			parent.layer.msg(title, {
				icon : style
			});
		},
		layLoad : function(style) {
			style = (style == "" || style == undefined) ? "1" : style;
			return parent.layer.load(style);
		},
		layClose : function(index) {
			return parent.layer.close(index);
		},
		layCloseAll : function() {
			return parent.layer.closeAll();
		},
		layCloseIframe : function() {
			return parent.layer.closeAll('iframe');
		},
		bootstraprefresh : function(id) {
			parent.r_fcon_m().$("#" + id).bootstrapTable("refresh");
		},
		getObject : function(id) {
			return parent.r_fcon_m();
		}
	})
})(jQuery);