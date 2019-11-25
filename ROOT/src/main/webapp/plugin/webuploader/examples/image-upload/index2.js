jQuery(function() {
    var $ = jQuery,
        $list = $('#fileList'),
        ratio = window.devicePixelRatio || 1,
        thumbnailWidth = 100 * ratio,
        thumbnailHeight = 100 * ratio,
        uploader;
    uploader = WebUploader.create({
        auto: true,
        swf: '../../dist/Uploader.swf',
        server: '../../server/up.php',
        pick: {
			id: '#filePicker',	
			multiple: false
		},
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
});

    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        alert (file.name+"被加载进来");
    });
	
	uploader.on("uploadAccept", function( file, data){ 
		if (data.success=="1") { 
			alert (data.url);
		}
	 });

    uploader.on( 'uploadError', function( file ) {
        alert ("上传失败了");
    });


});