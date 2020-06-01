
$("#pay").click(function () {
    var orderId = $(".orderId").attr("id");
    $.ajax({
        method:"post",
        url:"/cart/payment",
        success:function (msg) {
            if(msg=='ok'){
                window.location.href="/cart/order_success/"+orderId;
            }else{
                layer.msg("你的余额不足,请充值",{icon:0});
            }
        },error:function () {
            layui.msg("系统发生错误",{icon:2});
        }
    });
});