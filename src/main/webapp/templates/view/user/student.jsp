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
    <title>学生信息管理</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
    <script src="<%=basePath%>templates/admin/js/getGroup.js"></script>
</head>
<body>
<div class="kit-doc">
    <!--这里写页面的代码-->
    <blockquote class="layui-elem-quote">这是关于学生的信息管理页面</blockquote>
    <div class="kit-doc-title">
        <fieldset>
            <legend><a name="blockquote">学生信息管理</a></legend>
        </fieldset>
    </div>
    <div>
        <button class="layui-btn" id="add">添加</button>
        <%--<div class="layui-inline" style="float: right">--%>
        <%--<div class="layui-input-inline">--%>
        <%--<input name="selectbysid" lay-verify="required|phone" autocomplete="off" class="layui-input" type="tel">--%>
        <%--</div>--%>
        <%--<button class="layui-btn layui-btn-primary">搜索</button>--%>
        <%--</div>--%>
    </div>

    <table class="layui-table" lay-filter="kittable">
        <colgroup>
            <col width="40">
            <col width="80">
            <col width="60">
            <col width="200">
            <col>
            <col width="70">
        </colgroup>
        <thead>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>院部</th>
            <th>专业</th>
            <th>年级</th>
            <th>班级</th>
            <%--<th>联系电话</th>--%>
            <%--<th>邮箱</th>--%>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
                <tr>
                    <td>B20140304689</td>
                    <td>老王</td>
                    <td>男</td>
                    <td>计算机工程与应用数学学院</td>
                    <td>软件工程</td>
                    <td>14级</td>
                    <td>软件开发1班</td>
                    <%--<td>15664161151</td>--%>
                    <%--<td>179111651161@qq.com</td>--%>
                    <td>
                        <button class="layui-btn layui-btn-sm layui-btn-warm" onClick="look(this,'1')"><i class="layui-icon">&#xe621;</i>查看</button>
                    <button class="layui-btn layui-btn-sm layui-btn-normal" onClick="update(this)"><i class="layui-icon">&#xe642;</i> 编辑</button>
                    <button class="layui-btn layui-btn-sm layui-btn-danger" onClick="del(this,'1')"><i class="layui-icon">&#xe640;</i> 删除</button>
                    </td>
                </tr>
        </tbody>
    </table>
    <!--这里写页面的代码-->
</div>

<script>

    layui.config({
        base: '<%=basePath%>templates/style/build/js/'
    })

    layui.use(['jquery', 'layer', 'table'], function () {

        var table = layui.table;

        //让层自适应iframe
        $('#add').on('click', function(){
            var index = layer.open({
                type: 2,
                title:'添加学生',
                content: '<%=basePath%>user/addStudent',
                area: ['600px', '450px'],
                maxmin: true,
                end: function () {
                    location.reload();
                }
            });
            parent.layer.iframeAuto(index);
        });
    });


    // 删除
    function del(obj,id){
        //询问框
        layer.confirm('真的要删除么？', {
            btn: ['确定','取消'], //按钮
            end: function () {
                location.reload();
            }
        }, function(){
            $.post("<%=basePath%>user/delStudent",{'id':id},function(data){
                // 获取 session
                if(data.code!=200){
                    layer.msg(data.msg, {icon: 5});
                }
                if(data.code==200){
                    layer.msg(data.msg, {icon: 6});
                }
                parent.layer.iframeAuto(index);
            });
        }, function(){
        });
    };

    // 修改
    function update(obj){
        var index = layer.open({
            type: 2,
            title:'修改学生信息',
            content: '<%=basePath%>user/updatestudent',
            area: ['600px', '450px'],
            maxmin: true,
            end: function () {
                location.reload();
            }
        });
        parent.layer.iframeAuto(index);
    };

    function look(obj,sid){
        var index = layer.open({
            type: 2,
            title:'查看学生信息',
            content: '<%=basePath%>user/lookstudent?sid='+sid,
            area: ['600px', '450px'],
            maxmin: true,
            end: function () {
                location.reload();
            }
        });
        parent.layer.iframeAuto(index);
    };


</script>

</body>
</html>