
        function dataadd() {
             layer.open({
                 type: 2,
                 title: '数据录入',
                 maxmin: true,
                 area: ['620px', '580px'],
                 shadeClose: false, //点击遮罩不会关闭
                 content: '/adddzjz',//添加设备的from表单是在另一个html中，这是弹出方式的第三种方式
                 end:function(){
                     $("#ss").click();
                 }
             })
          }

 layui.use(['table','laydate','form'],function () {
        var table = layui.table,
            form = layui.form,
            laydate = layui.laydate;



        //常规用法
        laydate.render({
            elem: '#beginTime'
            ,type: 'datetime'
        });


        laydate.render({
            elem: '#endTime'
            ,type: 'datetime'
        });


        table.render({
            elem: '#demo',
            toolbar:'#toolbarDemo',    //添加头部工具栏，工具栏的定义在script模块中

            /* even:true,
             defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
             title: '提示'
                 ,layEvent: 'LAYTABLE_TIPS'
                  ,icon: 'layui-icon-tips'
             }],*/
            url: '/dzjz/getInfo',
            cellMinWidth: 80 ,//全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [
                    [
                        {type: 'checkbox', fixed: 'left'},
                        {field: 'id',title:'id',hide:true},
                        {field: 'xh',title: '序号', templet:'#zizeng',width: 80,sort: true,fixed: 'left'},
                        {field: 'badw',title: '办案单位' },
                        {field: 'time',title: '时间',sort:true},
                        {field: 'ajsl',title: '今日受理案件数量',sort : true},
                        {field: 'ajxz',title: '案件性质'},
                        {field: 'bamj',title: '办案民警'},
                        {field: 'ajbh',title: '案件编号',width:230},
                        {field: 'isdzjz',title: '是否上传电子卷宗'},
                        {field: 'wscyy' ,title: '未上传原因'},
                        {field: 'isbl',title: '案卷材料是否包含笔录'},
                        {field: 'wbhyy' ,title: '没有笔录原因'},
                        ,{fixed: 'right', title:'操作', toolbar: '#barDemo',width:150}
                    ]
                ]
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                ,groups: 1 //只显示 1 个连续页码
                ,first: false //不显示首页
                ,last: false //不显示尾页

            }
            ,height: 'full-190'
            ,text: {
                none: '暂无相关数据'
            }

        });

        //监听搜索
        form.on('submit(LAY-user-front-search)', function(data){
            var field = data.field;

            //执行重载
            table.reload('demo', {
                where: field
            });
        });





        //监听头工具栏事件，toolbar代表的是头部工具栏，test是表格的lay-filter的值
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：'+ data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });
        table.on('tool(test)',function (obj) {
           var data = obj.data;     //获取整行的数据
           var event = obj.event;   //获取事件
           var tr = obj.tr;         //获取当前的DOM对象

            if(event === 'del'){
                layer.confirm('确定删除时间为：'+data.time+' 的数据吗？',function () {
                    var id = data.id;
                    $.ajax({
                        url:'/dzjz/deldata',
                        data:{'id':id},
                        type:'post',
                        success: function (result) {
                            if(result.code == '1'){
                                layer.msg(result.msg);
                                obj.del();
                            }else {
                                layer.msg(result.msg)
                            }
                        }
                    })
                },function () {
                    layer.close();
                })
            }else if(event === 'edit'){
                    layer.open({
                        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        type: 2,
                        maxmin: true,
                        title: "修改信息",
                        area: ['620px', '580px'],
                        content: '/updzjz',//引用的弹出层的页面层的方式加载修改界面表单,
                        end:function(){
                            table.reload('demo',{  });
                        },
                        success:function(layero, index){
                            var contentdata = obj.data;
                            var iframeWin = window[layero.find('iframe')[0]['name']];
                            layero.find('iframe').contents().find('[name=time]').val(contentdata.time);
                            layero.find('iframe').contents().find('[name=ajsl]').val(contentdata.ajsl);
                            layero.find('iframe').contents().find('[name=id]').val(contentdata.id);

                            $.ajax({
                                url: '/dzjz/getCode/AJXZ',
                                success: function (data) {
                                    $.each(data,function (index,item) {
                                        if(contentdata.ajxz == item.codedesc){
							                layero.find('iframe').contents().find('[name=ajxz]').append($("<option>").attr("value",item.code).attr("selected","selected").text(item.codedesc));
                                        }else{
                                            layero.find('iframe').contents().find('[name=ajxz]').append($("<option>").attr("value",item.code).text(item.codedesc));
                                        }
                                    })

					               iframeWin.layui.form.render("select");
                                }
                            });

                            layero.find('iframe').contents().find('[name=bamj]').val(contentdata.bamj);
                            layero.find('iframe').contents().find('[name=ajbh]').val(contentdata.ajbh);

                            if(contentdata.isdzjz == '是'){
                                layero.find('iframe').contents().find('input[name=isdzjz][value=1]').attr("checked",true);
                                layero.find('iframe').contents().find('.isdzjzs').hide();
                            }else if(contentdata.isdzjz == '否'){
                                layero.find('iframe').contents().find('input[name=isdzjz][value=2]').attr("checked",true);
                                layero.find('iframe').contents().find('.isdzjzs').show();
                            }

                            layero.find('iframe').contents().find('[name=wscyy]').val(contentdata.wscyy);

                            if(contentdata.isbl == '是'){
                                layero.find('iframe').contents().find('input[name=isbl][value=1]').attr("checked",true);
                                layero.find('iframe').contents().find('.isbls').hide();
                            }else if(contentdata.isbl == '否'){
                                layero.find('iframe').contents().find('input[name=isbl][value=2]').attr("checked",true);
                                layero.find('iframe').contents().find('.isbls').show();
                            }

                            layero.find('iframe').contents().find('[name=wbhyy]').val(contentdata.wbhyy);
                            iframeWin.layui.form.render();
                        }
                    });
            }
        });
        //监听事件，这会是监听单元格编辑事件
        table.on('edit(test)',function (obj) {
            var value = obj.value    //得到修改后的值
            ,filed = obj.field       //得到字段
                , data = obj.data;   //得到所有的键值，从这里边可以获得表格的所有值

            //在这里写与后台交互的代码
            //alert(value);
            var user = {
                "id":data.id,
                "name":value
            };
            $.ajax({
                url:'/user/upname',
                data:JSON.stringify(user),
                type:'post',
                contentType:'application/json',
                success: function (result) {
                    //console.log(result.code)
                    if(result.code == '0'){
                        layer.msg(result.msg)
                    }else {
                        layer.msg(result.msg)
                    }
                }
            });

        });


    });