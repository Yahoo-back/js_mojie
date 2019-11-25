$(function(){
	$("#loginBtn").on("click",function(){
		Yclogin();
	});
    
    $('#Password').keyup(function (event) {
        if (event.keyCode == "13") {
        	Yclogin();
        }
    });

    $('.n_page-container form .n_username, .n_page-container form .n_password').keyup(function(){
        $('.error').fadeOut('fast');
    });

});



var Yclogin = function(){
	 var username = $('.n_username').val();
     var password = $('.n_password').val();
     if(username == '') {
         $('.n_error').fadeOut('fast', function(){
             $(this).css('top', '27px');
         });
         $('.n_error').fadeIn('fast', function(){
             $('.n_username').focus();
         });
         return false;
     }
     if(password == '') {
         $('.n_error').fadeOut('fast', function(){
             $(this).css('top', '96px');
         });
         $('.n_error').fadeIn('fast', function(){
             $('.n_password').focus();
         });
         return false;
     }
	$('.n_login-box form').attr({action: ctx+"/login", method: "post" });
	$('.n_login-box form').submit();
	return false;

}