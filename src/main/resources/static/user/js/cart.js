$(function(){
	
	/**
	 * 全选按钮操作
	 */
	$(".selected").click(function(){
		if($(this).is(":checked")){
			$(".top").prop("checked",true);
			sumProduct();
		}else{
			$(".top").prop("checked",false);
			$("#all").text("0");
			$("#money").text('¥0.00');
		}
		
	
	});
	
	/**
	 * 单个商品的选中操作
	 */
		
	$(".top").on("click",function(){
		if(!$(this).is(":checked")) {
			$(".selected").prop("checked",false);
			sumProduct();
			return;
		}else{
		sumProduct();
		}
		var len=$(".top").length;
		for(var i=0;i<len;i++){
			if(!$(".top").eq(i).is(":checked")){
				$(".selected").prop("checked",false);
				return ;
			}
		}
        $(".selected").prop("checked",true);

	
	
		
	});
		/**
		 * 加上一个商品数量
		 */
	
	$(".operi").on("click",function(){
		
		var $num=$(this).prev();//获取上个邻节点
		var len =parseInt($num.val())+1;
        var prodId=$(this).parents(".item").attr("id");
		if(len>10) {	
			len=10;
			$(this).next().text("至多购买10件").show();
		}else{
			$(this).next().hide();
            $.ajax({
                url:"/cart/add",
                type: "post",
                data:{"prodId":prodId}
            });
		}
		$num.val(len);
		countItemSum($(this),len);
		if(!checkSelected($(this))){
			return ;
		}else{
			sumProduct();
		}
	});
	
	/**
	 * 减去一个商品数量
	 */
	$(".operd").on("click",function(){
		
		var $num=$(this).next();//获取下个邻节点
		var len =parseInt($num.val())-1;
        var prodId=$(this).parents(".item").attr("id");
		if(len<1) {
			len=1;
			$num.next().next().text("至少购买1件").show();	
		}else{
			$num.next().next().hide();
            $.ajax({
                url:"/cart/add",
                data:{"type":"sub","prodId":prodId},
                type: "post"
            });
		}
		$num.val(len);



		countItemSum($(this),len);
		if(!checkSelected($(this))){
			return ;
		}else{
			sumProduct();
		}
	});
	
	/**
	 * 删除某行商品
	 */
	
	$(".item-del").on('click',function(){
		var $item=$(this).parents('.item');
		var open=layer.open({
		  btn: ['确认','关闭'],
		  content:'确定要删除该宝贝吗?',
		  title: '删除宝贝',
		  yes: function(open){
              layer.close(open);
		      var prodIds=[];
		      var prodId=$item.attr('id');
		      prodIds.push(prodId);
		      $.ajax({
                  url:'/cart/remove',
                  method:'post',
                  contentType:"application/json",
                  data:JSON.stringify(prodIds),
                  success:function () {
                      $item.remove();
                      layer.msg('已从购物车中移除',{icon:1});
                      sumProduct();
                  },error:function () {
                      layer.msg('服务器出错啦',{icon:2});
                  }
              });


		  }
		});
	
		

	});
	
	/**
	 * 删除选中商品
	 */
	$("#del").on('click',function(){
		delProduct();
	});
	
	
	
});

function sumProduct(){
	var len=$(".top").length;
	var num=0;
	var total=0;

	for(var i=0;i<len;i++){
		$current =$(".top").eq(i);
		if($current.is(":checked")){
			var  n=$current.parent(".left").next().find(".num").val();
			var p=$current.parent(".left").next().find(".real").text();
				total+=(parseInt(n)*parseFloat(p));
				num+=parseInt(n);
		}
	}
	$("#all").text(num);
	$("#money").text('¥'+toDecimal2(total));
}

function countSelectedItme() {
    var len=$(".top").length;
    var a=[];
    var count=0;
    for(let i=0;i<len;i++){
        $current =$(".top").eq(i);
        if($current.is(":checked")){
            a[count++]=$current.parents('.item').attr('id');
        }
    }
    if(count==len) {$(".selected").prop("checked",false);}
    if(count==0){
        layer.alert('请至少选中一项删除的内容', {
            icon: 0,
            title:'提示信息',
            btn:['知道了'],
            skin: 'layer-ext-moon'
        });
        sumProduct();
        return 0;
    }
    return a;
}

function delProduct(){


   var a= countSelectedItme();
   if(a==0) return;
   var count=a.length;
	var open=layer.open({
	  btn: ['确认','关闭'],
	  content:'确定要删除该宝贝吗?',
	  title: '删除宝贝',
	  yes: function(open){
		  $.ajax({
             url:"/cart/remove",
              method: "post",
              contentType:"application/json",
              data:JSON.stringify(a),
              success:function () {
                  while(count>0){
                      $(`#${a[--count]}`).remove();
                  }
                  layer.msg('已从购物车中移除',{icon:1});
                  sumProduct();
              },error:function () {
                  layer.msg('服务器出错啦',{icon:2});
              }
          });
		  layer.close(open);
	  }
	});	


}



function countItemSum($my,num){
	var price=parseFloat($my.parents('.right').find('.price .real').text());
	var sum='¥ '+toDecimal2(price*num);
	$my.parents('.number').next().text(sum);

}


function checkSelected($node){
	var $input =$node.parents(".item").find(".top");
	var flag=false;
	if($input.is(":checked")){
		flag=true;
	}
	return flag;
	
}
function toDecimal2(x){
	var f=parseFloat(x);
	if(isNaN(f)){
		return false;
	}
	var f=Math.round(x*100)/100;
	var s=f.toString();
	var rs=s.indexOf(".");
	if(rs<0){
		rs=s.length;
		s+='.';
	}
	while (s.length<=rs+2){
		s+='0';
	}
	return s;
}

//添加未支付订单,默认5分钟
$("#buy").click(function () {
    var a= countSelectedItme();
    if(a==0) return;
    var prodIds=[];

    var price=0;

    for(let i=0;i<a.length;i++){
        prodIds.push(a[i]);
    }
    $.ajax({
        url:"/cart/add_product_temporarily",
        method: "post",
        contentType:"application/json",
        data:JSON.stringify(prodIds),
        success:function () {
            window.location.href="http://localhost:8080/cart/Payment_order"
        },error:function () {
            layer.msg('服务器出错啦', {icon: 2});
        }
    });
});

