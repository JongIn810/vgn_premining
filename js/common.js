

/* 레이어팝업 */
	function layer_open(idx) {
		$('#pop_layer').fadeIn();
		$('.pop_close').addClass("active");
		//$('body').css('position', 'fixed');
		var temp = $('#layer1');
		if (temp.outerHeight() < $(document).height() ) temp.css('');
		else temp.css('top', '0px');
		if (temp.outerWidth() < $(document).width() ) temp.css('margin-left', '-' + temp.outerWidth() / 2 + 'px');
		else temp.css('left', '0px');
		$('#idx').val(idx);
	}

	function layer_close() {
		$('#pop_layer').fadeOut();
		$('.pop_close').removeClass("active");
		//$('body').css('position', '');
	}


$(document).ready(function(){

    $('body').scrollspy({ target: ".navbar", offset: 50 });
    $('#myNavbar a').on('click', function(event) {
        if (this.hash !== "") {
            event.preventDefault();
            var hash = this.hash;
            $('html, body').animate({
                scrollTop: $(hash).offset().top
            }, 800, function() {
                window.location.hash = hash;
            });
        }
    });

	//
	$('.head_side_sns').find(' > a').click(function(){
		$('.head_side_sns').find(' > ul').slideToggle("300");
		$('.sel').toggleClass('on');
		$('.global').find(' > ul').removeClass("on");
	});

	//
	$('.global').find(' > a').click(function(){
		$('.g_sel').toggleClass('on');
		$('.global').find(' > ul').toggleClass("on");
		$('.sel').removeClass('on');
		$('.head_side_sns').find(' > ul').slideUp("300");
	});

	$('.navbar').find(' > a').click(function(){
		$('.navbar').find(' > .top_menu').slideToggle("300");
		$('.open_sidemenu').toggleClass('active');
	});

    /*$(".contents_box01 .more").on('click', function() {
		$(this).toggleClass("open");
		$(".contents_box01 .story_contents").toggleClass("open");
	});*/
	$('.contents_box01 .more').click(function () { 
		$(this).toggleClass('open'); 
		if ($(this).hasClass("open")) { 
			$(".contents_box01 .story_contents").addClass("open");
		} else if (!$(this).hasClass("open")) { 
			$(this).removeClass("open");
			$(".contents_box01 .story_contents").removeClass("open");
		} 
	});

});

// 스크롤시 nav를 고정시킴
$(window).scroll(function() {

    if( $(this).scrollTop() >= 20 ) {
        // #nav 에 class 적용
		$("#header").addClass("active");
		$("#gnb").addClass("active");

     } else {
		$("#header").removeClass("active");
		$("#gnb").removeClass("active");
     }

});


/*스크롤 탑 상단으로 부드럽게 이동*/
$(function(){
	$(".footer_top_btn a.b_tb").click(function(){
		$("html, body").animate({ scrollTop: 0 }, 600);//화살표 클릭시 화면 스크롤 속도
			return false;
    });
});

$(window).scroll(function() {
   if($(window).scrollTop() + $(window).height() == $(document).height()) {
       $(".footer_top_btn").addClass('top');
   } else {
		$(".footer_top_btn").removeClass('top');
	}
});




