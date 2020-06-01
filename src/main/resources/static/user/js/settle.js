$('input[name="address"]').click(function(){
	
	$('.sendTo').removeClass('choosed');
	$('.sendTo').children('.wth').empty();
	var $send=$(this).parents('.sendTo');
	$send.children('.wth').text('寄送至');
	$send.addClass('choosed');
	
});


$('#submit-order').click(function(){
	var ugaId=$('input[name="address"]:checked').attr('id');
    $("#create-order").attr("action",'/cart/order_deal/'+ugaId);
	$("#create-order").submit();

});