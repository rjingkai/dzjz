
layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;


    getBadw();

    function getBadw() {
        $.ajax({
            url: '/dzjz/getCode/BADW',
            success: function (data) {
               $.each(data,function (index,item) {
                   $('#badwid').append(new Option(item.codedesc,item.code));
               })

                layui.form.render("select");
            }
        })
    }

        getAjxz();

        function getAjxz() {
            $.ajax({
                url: '/dzjz/getCode/AJXZ',
                success: function (data) {
                    $.each(data,function (index,item) {
                        $('#ajxzid').append(new Option(item.codedesc,item.code));
                    })

                    layui.form.render("select");
                }
            })
        }


    //日期
    laydate.render({
        elem: '#date'
        ,value: new Date()
        ,type: 'datetime'   //将日期框改为带时间的
    });

    //给radio绑定监听事件，radio发生改变后判断文本域的显示或隐藏
    form.on('radio(isdzjzf)',function (data) {
       var d = data.value;
        if(d == '2'){
            $(".isdzjzs").show();
            $(".isdzjzs").attr("lay-verify","required");
            $(".isdzjzs").attr("lay-reqtext","未上传电子卷宗原因必填");
        }else{
            $(".isdzjzs").hide();
            $(".isdzjzs").removeAttr("lay-verify");
            $(".isdzjzs").removeAttr("lay-reqtext");
        }
    });

    form.on('radio(isblf)',function (data) {
        var d = data.value;
        if(d == '2'){
            $(".isbls").show();
            $(".isbls").attr("lay-verify","required");
            $(".isbls").attr("lay-reqtext","没有笔录原因必填");
        }else{
            $(".isbls").hide();
            $(".isbls").removeAttr("lay-verify");
            $(".isbls").removeAttr("lay-reqtext");
        }
    });
    //创建一个编辑器
  /*  var editIndex = layedit.build('LAY_demo_editor');*/

    //自定义验证规则
   /* form.verify({
        title: function(value){
            if(value.length < 5){
                return '标题至少得5个字符啊';
            }
        }
        ,pass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
        ,content: function(value){
            layedit.sync(editIndex);
        }
    });*/

    //监听指定开关
  /*  form.on('switch(switchTest)', function(data){
        layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
            offset: '6px'
        });
        layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
    });*/

    //监听提交
    form.on('submit(demo1)', function(res){
        $.ajax({
            url:'/dzjz/add',
            type :'post',
            contentType:'application/json',
            data : JSON.stringify(res.field),
            success: function (data) {
                var data = data;
                alert(data);
                if (data.code == 1) {
                    layer.alert('成功！', {
                        icon: 6,
                        title: "提交结果"
                    });
                }else {
                    layer.alert('失败！', {
                        icon: 5,
                        title: "提交结果"
                    });
                }
            },
            error:function (XmlHttpRequest,textStatus,errorThrown) {
                alert("出错:"+XmlHttpRequest.responseText);
                //layer.msg(XmlHttpRequest.responseText);
            }
        });

    });

    //表单赋值
  /*  layui.$('#LAY-component-form-setval').on('click', function(){
        form.val('example', {
            "username": "贤心" // "name": "value"
            ,"password": "123456"
            ,"interest": 1
            ,"like[write]": true //复选框选中状态
            ,"close": true //开关状态
            ,"sex": "女"
            ,"desc": "我爱 layui"
        });
    });*/

    //表单取值
   /* layui.$('#LAY-component-form-getval').on('click', function(){
        var data = form.val('example');
        alert(JSON.stringify(data));
    });*/

});




