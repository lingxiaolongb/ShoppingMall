$(".addProdToCart").on('click',function () {
  var prodId=  $(this).attr('prodId');
    $.ajax({
        url:'/cart/add',
        type:'post',
        data:{
          "prodId":prodId
        },
        success:function (msg) {
            switch (msg) {
                case 'logout':
                    window.location.href='http://localhost:8080/toLogin';
                    break;
                case 'duplicate':
                    layer.msg('该商品已存在我的购物车',{icon:2});
                    break;
                case 'ok':
                    layer.msg('已成功添加在我的购物车',{icon:1});
                    break;
            }
        },error:function () {
            layer.msg('服务器出错啦,请稍后再试',{icon:2});
        }
    })

});