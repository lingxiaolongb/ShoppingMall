$('#save').on('click',function(){
    var realName=$("#realName").val();
    var email=$("#email").val();
    var phone=$("#phone").val();
    var gender= $("input[name='gender']:checked").val();
    $.ajax({
        url:"/my_jindong/update.user",
        type:"post",
        data:{
            "realName":realName,
            'email':email,
            'phone':phone,
            'gender':gender
        },
        success:function (msg) {
            layer.msg('您的信息以保持成功',{icon:1,time:1000})
        },error:function () {
            layer.msg('服务器出错了',{icon:2,time:1000})
        }

    });
    ;
});