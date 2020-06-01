
$(".pos").on('click', function() {

	var $this=$(this);
	layer.confirm('删除后,将无法在恢复。', {
		btn: ['确定', '关闭'],
		anim: 1
		,title: '您确定要删除该订单吗？'
	}, function() {
		$this.parents('.item').remove();
		layer.msg('删除成功', {
			icon: 1
		});
	});

});

$(".payment-order").click(function () {
    var orderId = $(this).parents(".item").attr("id");
    $("#order-pay").attr("action",'/cart/payMoney/'+orderId);
    $("#order-pay").submit();
});
