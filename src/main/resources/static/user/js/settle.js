$('input[name="address"]').click(function(){
	
	$('.sendTo').removeClass('choosed');
	$('.sendTo').children('.wth').empty();
	var $send=$(this).parents('.sendTo');
	$send.children('.wth').text('寄送至');
	$send.addClass('choosed');
	
});


$('#submit-order').click(function(){
	var ugaId=$('input[name="address"]:checked').attr('id');

	$.ajax({
        url:'/cart/order_deal/'+ugaId,
        method:'post',
        success:function (msg) {
            if(msg=='not'){
                layer.msg('你的当前余额不足,请充值',{icon:0});
            }else{
                window.location.href='http://localhost:8080/cart/order_success'
            }
        },error:function () {

        }
    });
});