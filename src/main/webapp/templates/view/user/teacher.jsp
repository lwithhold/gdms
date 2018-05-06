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
    <title>老师信息管理</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
    <script src="<%=basePath%>templates/admin/js/getGroup.js"></script>
</head>
<body>
<div class="kit-doc">
    <!--这里写页面的代码-->
    <blockquote class="layui-elem-quote">这是关于老师的信息管理页面</blockquote>
    <div class="kit-doc-title">
        <fieldset>
            <legend><a name="blockquote">老师信息管理</a></legend>
        </fieldset>
    </div>
    <div>
        <button class="layui-btn" id="add">添加</button>
        <div class="layui-inline" style="float: right">
            <div class="layui-input-inline">
                <input id="search" name="selectbysid" lay-verify="required|phone" autocomplete="off" class="layui-input" type="tel">
            </div>
            <button class="layui-btn layui-btn-primary" onclick="search()">搜索</button>
        </div>
    </div>
    <table class="layui-hide" id="test" lay-filter="demo">
    </table>
    <script type="text/html" id="barDemo">
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="edit"><i class="layui-icon">&#xe642;</i> 编辑</button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del"><i class="layui-icon">&#xe640;</i> 删除</button>
    </script>
</div>

<script>

    layui.config({
        base: '<%=basePath%>templates/style/build/js/'
    })

    // 添加
    layui.use(['jquery', 'layer', 'table'], function () {
        var table = layui.table;
        //让层自适应iframe
        $('#add').on('click', function(){
            var index = layer.open({
                type: 2,
                content: '<%=basePath%>user/goAddTeacher',
                area: ['600px', '450px'],
                maxmin: true,
                end: function () {
                    location.reload();
                }
            });
            parent.layer.iframeAuto(index);
        });
    });



    // 渲染数据
    layui.use('table', function(){
        var table = layui.table;
        var search = $('#search').val();

        table.render({
            elem: '#test'
            ,url:'<%=basePath%>user/teacherGetAllJson'
            ,where: {search: search}
            ,method: 'post'
            ,page: {layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']}
            ,cols: [[
                {field:'id',align:'center', width:80,  title: '序号'}
                ,{field:'tid', title: '工号'}
                ,{field:'tname', title: '姓名'}
                ,{field:'sex', title: '性别'}
                ,{field:'workTime', title: '参加工作时间'}
                ,{field:'hdegree', title: '最高学历'}
                ,{field:'teachingDirection', title: '教学方向'}
                ,{field:'position', title: '职称'}
                ,{field:'did', title: '所属院部'}
                ,{field:'mid', title: '所属专业'}
                ,{field:'identityId', title: '身份'}
                ,{field:'right',align:'center', width:150, toolbar: '#barDemo', title: '操作'}
            ]]
        });

        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'edit'){
                // 编辑
                var index = layer.open({
                    type: 2,
                    content: '<%=basePath%>user/goUpdateTeacher?id='+data.id,
                    area: ['600px', '450px'],
                    maxmin: true,
                    end: function () {
                        location.reload();
                    }
                });
                parent.layer.iframeAuto(index);
            } else if(obj.event === 'del'){
                layer.confirm('真的要删除么？', function(index){
                    // 写删除方法
                    $.post("<%=basePath%>user/delTeacher", {"id": data.id}, function (data) {
                        if (data.code == "200") {
                            layer.msg(data.msg, {icon: 1, time: 1000});
                            // 前端修改
                            layer.close(index);
                            window.location.reload();
                        } else {
                            layer.msg(data.msg, {icon: 0, time: 1000});
                            layer.close(index);
                        }
                    });
                });
            }
        });
    });
    function search(){
        var table = layui.table;
        var search = $('#search').val();
        table.render({
            elem: '#test'
            ,url:'<%=basePath%>user/teacherGetAllJson'
            ,where: {search: search}
            ,method: 'post'
            ,page: {layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']}
            ,cols: [[
                {field:'id',align:'center', width:80,  title: '序号'}
                ,{field:'tid', title: '工号'}
                ,{field:'tname', title: '姓名'}
                ,{field:'sex', title: '性别'}
                ,{field:'workTime', title: '参加工作时间'}
                ,{field:'hdegree', title: '最高学历'}
                ,{field:'teachingDirection', title: '教学方向'}
                ,{field:'position', title: '职称'}
                ,{field:'did', title: '所属院部'}
                ,{field:'mid', title: '所属专业'}
                ,{field:'identityId', title: '身份'}
                ,{field:'right',align:'center', width:150, toolbar: '#barDemo', title: '操作'}
            ]]
        });
    }
</script>

</body>
</html>
