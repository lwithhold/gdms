<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2018/4/26
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>查看所有的指导老师</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
    <script src="<%=basePath%>templates/admin/js/getGroup.js"></script>
</head>
<body>
<div class="kit-doc">
    <!--这里写页面的代码-->
    <blockquote class="layui-elem-quote">这是关于所有指导老师的页面</blockquote>
    <div class="kit-doc-title">
        <fieldset>
            <legend><a name="blockquote">所有的指导老师</a></legend>
        </fieldset>
    </div>
    <table class="layui-hide" id="test" lay-filter="demo">
    </table>
    <script type="text/html" id="barDemo">
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="approval">查看所带学生</button>
    </script>
</div>

<script>

    layui.config({
        base: '<%=basePath%>templates/style/build/js/'
    })

    // 渲染数据
    layui.use('table', function() {
        var table = layui.table;
        table.render({
            elem: '#test'
            , url: '<%=basePath%>advise/AllAdviseTeacherGetAllJson'
            , method: 'post'
            , cols: [[
                {type: 'numbers', title: '序号', fixed:'left'},
                {field:'id',align:'center', width:80, title: '编号'}
                ,{field:'tid', title: '工号',width:150, sort:true}
                ,{field:'tname', title: '姓名',width:80}
                ,{field:'sex', title: '性别',width:80,sort:true}
                ,{field:'workTime', title: '参加工作时间',width:120,sort:true}
                ,{field:'hdegree', title: '最高学历',width:100}
                ,{field:'teachingDirection', title: '教学方向',width:120,sort:true}
                ,{field:'position', title: '职称',width:120}
                ,{field:'department', title: '所属院部',width:220}
                ,{field:'major', title: '所属专业',width:120}
                ,{field:'phone', title: '联系电话',width:120}
                ,{field:'email', title: '邮箱',width:180}
                ,{field:'haveNumber', title: '已带人数',width:100}
                ,{field:'limitNumber', title: '限制人数',width:100}
                , {field: 'right', align: 'center', width:120, toolbar: '#barDemo', title: '操作', fixed: 'right'}
            ]]
            , page: true
        });
        //监听工具条
        table.on('tool(demo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'approval') {
                var index = layer.open({
                    type: 2,
                    content: '<%=basePath%>advise/goCarryStudent?id='+data.id+'&tid='+data.tid,
                    area: ['800px', '450px'],
                    maxmin: true,
                    end: function () {
                        location.reload();
                    }
                });
                layer.full(index);
            }
        });
    });
</script>

</body>
</html>
