


$('.del_delivery').on('click',function(){
	var $this=$(this);
	layer.alert('确定删除这条地址么', {
	  skin: 'layui-layer-lan' //样式类名
	  ,title:''
	  ,btn:['确定','取消']
	}, function(){
	    var ugdId = $this.parents('tr').attr('id');
        $.ajax({
            url:'/delivery/del/'+ugdId,
            method:'post',
            success:function () {
                $this.parents('tr').remove();
                layer.msg('删除成功', {icon: 1});
            },error:function () {
                layer.msg('服务器出错了啦', {icon: 0});
            }
        });


	});

});

$(".table").on('click','.set_default',function(){
    $address=$('<span id="defaultAddress" >默认地址</span>');
    $set=$('<button class="set_default">设为默认</button>');
    var ugdId=$(this).parents('tr').attr('id');
    $parent=$(this).parent();
    $parent.empty().append($address);
    $parent.parent().siblings().children('.address').empty().append($set);
    $.ajax({
        url:'/delivery/update/'+ugdId,
        method:'post',
        success:function () {
            layer.msg('设置成功', {icon: 1});
        },error:function () {
            layer.msg('服务器出错了啦', {icon: 0});
        }
    });

});