layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;


        //监听提交
        form.on('submit(formDemo)', function(data){
             var loginLoading = top.layer.msg('登录中，请稍候',
                {
                    icon : 16,
                    time : false,
                    shade : 0.8
                }
                );
            //记录ajax请求返回值
            var ajaxReturnData;
             //登陆验证
            $.ajax(
            {
                url : '/user/login',
                type : 'post',
                async : false,
                data : data.field,
                success : function (data)
                {
                    ajaxReturnData = data;
                }
            }
            );

            //登陆成功
            if (ajaxReturnData.code == 0)
            {
                window.location.href = "/dzjz/getInfo";
                top.layer.close(loginLoading);
                return false;
            }
            else
            {
                top.layer.close(loginLoading);
                top.layer.msg(ajaxReturnData.msg,
                {
                    icon : 5
                }
                );
                return false;
            }

        });



    });