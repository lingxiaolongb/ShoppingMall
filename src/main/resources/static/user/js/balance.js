$(function(){
    var str=$("#balance").text();
    $("#balance").text(str=dealMoney(str));
});

function dealMoney(money){
    var index=money.indexOf(".");
    var arr1;
    var arr2;
    if(index==-1){
        arr1=money;
    }else{
        arr1=money.split('.')[0];
        arr2=money.split('.')[1];
    }

    var arr3 =arr1.split('');
    var len=arr3.length;
    var value='';
    var count =1;
    for(var i=len-1;i>=0;i--){
        if(count%3==0 & typeof(arr3[i-1])!='undefined'){
            value=','+arr3[i]+value;
        }else{
            value=arr3[i]+value;
        }
        count++;
    }
    if(money.indexOf(".")!=-1){
        return value+'.'+arr2;
    }else{
        return value+'.00元';
    }

}

/*
*充值余额界面
* 
*/
$('#cz').on('click',function(){
	var con= createCz();

	 layer.open({
	  type: 1,
	  skin: 'layui-layer-demo', 
	  title:'充值',
	  area: ['350px', '250px'],
	  anim: 2,
	  shadeClose: true, 
	  content: con
	});
	

});
/*
*提现功能界面
* 
*/
$('#tx').on('click',function(){
	
	var con= createTx();
	
	 layer.open({
	  type: 1,
	  skin: 'layui-layer-demo', 
	  title:'提现',
	  area: ['350px', '270px'],
	  anim: 2,
	  shadeClose: true, 
	  content: con
	});
	
	
});

/*
*转账功能界面
* 
*/
$('#zz').on('click',function(){
	var con= createZz();
	
	 layer.open({
	  type: 1,
	  skin: 'layui-layer-demo', 
	  title:'转账',
	  area: ['350px', '340px'],
	  anim: 2,
	  shadeClose: true, 
	  content: con
	});
	
	
	
});

/*
*余额宝功能界面
* 
*/
$('#yeb').on('click',function(){
		layer.confirm('您是如何看待前端开发？', {
		  btn: ['重要','奇葩'] //按钮
		}, function(){
		  layer.msg('的确很重要', {icon: 1});
		}, function(){
		  layer.msg('也可以这样', {
		    time: 2000, //20s后自动关闭
		    btn: ['明白了', '知道了']
		  });
		});
	});
$(function(){

	
});

/*
*创建充值功能
* 
*/
function createCz(){
	
	var node=`<div class="add">
						<p class="desc">充值金额</p>
						<div class="write">￥&nbsp;<input autocomplete="off" id="addMoney" class="tc"     /></div>
						<div class="tif">
								<button  class="layui-btn layui-btn-normal" id="increment" >充值</button>	
						</div></div>
				<script type="text/javascript">	$('.tc').focus();</script>`;
 
				

	return node;
	
}
/*
*创建提现功能
* 
*/

function createTx(){
    var money=$("#balance").text();
	var  node=`<div class="add">
							<p class="desc">提现</p>
							<div class="write bbm">￥&nbsp;<input id="subMoney" autocomplete="off" class="tc"   /></div>
							<div class="rest">
								可用余额&nbsp;<span >${money}</span>
							</div>
							<div class="tif">
								<button  class="layui-btn layui-btn-normal" id="decrease">确认提现</button>	
							</div>
					</div><script type="text/javascript">$('.tc').focus();</script>`;
	return node;
}
/*
*创建转账功能
* 
*/
function createZz(){
	var  node=`<div class="add">
							<p class="desc">转到对方余额内</p>
							<div class="target">
								<span style="color: red;">*</span>转账人:
								<input type="text" id="targetName" placeholder="请填写转账人ID与手机号" />
							</div>
							<p class="zzje">转账金额</p>
							<div class="write bbm">￥&nbsp;<input id="transferMoney" autocomplete="off" class="tc"   /></div>
							<div class="rest">
								<input  placeholder="添加备注" autocomplete="off" 
								 name="msg">
							</div>
							<div class="tif">
								<button  class="layui-btn layui-btn-normal" id="transfer">确认转账</button>	
							</div>
					</div><script type="text/javascript">$('.tc').focus();</script>`;
	return node;
}
/*
*去数据库查询余额
* 
*/
function selectRest(){
	
}

$('body').on('click','#increment',function(){

        var money=$("#addMoney").val();
        $.ajax({
           url:"/balance/add",
            method:'post',
            data:{
               "balance":money
            },success:function (msg) {
                layer.closeAll('page');
                layer.msg('充值成功', {icon: 1,time:800});
            },error:function () {
                layer.closeAll('page');
                layer.msg('充值失败', {icon: 0,time:800});
            }
        });

});

$('body').on('click','#decrease',function(){
       var money= $("#subMoney").val();
    $.ajax({
        url:"/balance/sub",
        method:'post',
        data:{
            "balance":money
        },success:function (msg) {
            layer.closeAll('page');
           if(msg=='ok'){
               layer.msg('提现成功', {icon: 1,time:800});
           }else if(msg=='notEnough'){
               layer.msg('你的账户余额不足', {icon: 0,time:800});
           }

        },error:function () {
            layer.closeAll('page');
            layer.msg('提现失败', {icon: 0,time:800});
        }
    });
});

$('body').on('click','#transfer',function(){
    var targetName= $("#targetName").val();
    var money=$("#transferMoney").val();
    $.ajax({
        url:"/balance/transfer",
        method:'post',
        data:{
            "balance":money,
            'targetName':targetName
        },success:function (msg) {
            layer.closeAll('page');
            if(msg=='ok'){
                layer.msg('转账成功', {icon: 1,time:800});
            }else if(msg=='notExist'){
                layer.msg('转账用户不存在', {icon: 2,time:800});
            }else if(msg=='cantYourself'){
                layer.msg('不能转给自已', {icon: 2,time:800});
            }else if(msg=='notEnough'){
                layer.msg('你的账户余额不足', {icon: 2,time:800});
            }
        },error:function () {
            layer.closeAll('page');
            layer.msg('转账失败', {icon: 0,time:800});
        }
    });
});