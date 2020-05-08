


layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate
            ,$ = layui.jquery;




        //监听提交
        form.on('submit(formDemo)', function(res){
             var loginLoading = top.layer.msg('登录中，请稍候',
                {
                    icon : 16,
                    time : false,
                    shade : 0.8
                }
                );

                //登陆验证
                $.ajax({
                    url : '/user/dl',
                    type : 'post',
                    datatype:'json',
                    data : {username:res.field.username,password:res.field.password},
                    success:function (data){
                        if(data.code == 0){
                            if(data.msg == '100000'){
                                window.location.href = "/index";
                                top.layer.close(loginLoading);
                                return false;
                            }else{
                                window.location.href = "/indexpcs";
                                top.layer.close(loginLoading);
                                return false;
                            }
                        } else{
                             top.layer.close(loginLoading);
                             top.layer.msg(data.msg,
                             {
                                 icon : 5
                             }
                             );
                             return false;
                         }

                    },
                    error:function(e){
                        alert('登录出错.....');
                    },
                    fail:function(e){
                        alert("登录失败。。。。");
                    }
                });

        });



    });