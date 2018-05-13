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
    <title>查看课题通过情况</title>
    <link rel="stylesheet" href="<%=basePath%>templates/style/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=basePath%>templates/style/build/css/doc.css" media="all">
    <script src="<%=basePath%>templates/style/plugins/layui/layui.js"></script>
    <script src="<%=basePath%>templates/style/plugins/layui/jquery-3.3.1.min.js"></script>
    <script src="<%=basePath%>templates/admin/js/getGroup.js"></script>
</head>
<body>
<div class="kit-doc">
    <!--这里写页面的代码-->
    <blockquote class="layui-elem-quote">这是关于学生查看课题通过情况的页面</blockquote>
    <div class="kit-doc-title">
        <fieldset>
            <legend><a name="blockquote">查看课题通过情况</a></legend>
        </fieldset>
    </div>
    <div>
    </div>

    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width:120px">课题名称:</label>
            <div class="layui-input-inline">
                <input name="topicName" value="课题一" lay-verify="required" autocomplete="off" class="layui-input layui-disabled" type="text"  style="width: 300px" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"style="width:120px">附件:</label>
            <%--<a href="/apiCommon/download/?fileUrl=/uplodefiles/file/4090a9a26f2743dab05c32fa8f48fffb.xlsx">课题申请书</a>--%>
            <div><a href="<%=basePath%>apiCommon/download?fileName=de19907f241e487cb3e6128f903c0b39.xlsx">课题申请书</a></div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-sm layui-btn-danger" lay-submit lay-filter="add"><i class="layui-icon">&#xe640;取消申请</i></button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use(['element','upload','form'], function(){
        var $ = layui.jquery;
        var form = layui.form,upload = layui.upload;

        //选完文件后不自动上传
        upload.render({
            elem: '#test8'
            ,url: '/apiCommon/setFile'
            ,auto: false
            ,accept: 'file'
            // ,exts:'docx|doc|xlsx|txt|xls'
            , field: 'layuiFile' //设定文件域的字段名
            // , before: function (obj) { //文件提交上传前的回调
            //     //预读本地文件示例，不支持ie8
            //     obj.preview(function (index, file, result, data) {
            //         //index:文件的索引，file:文件的对象，result:文件base64编码，比如图片
            //         $('#demo1').css('display','block').attr('src', result); //链接（base64）
            //     });
            // }
            //,multiple: true
            ,bindAction: '#test9'
            // ,choose: function(obj){
            //     //将每次选择的文件追加到文件队列
            //     var files = obj.pushFile();
            //     //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
            //     obj.preview(function(index, file, result){
            //         console.log(index); //得到文件索引
            //         console.log(file); //得到文件对象
            //         console.log(result); //得到文件base64编码，比如图片
            //     });
            // }
            ,done: function(res){
                //如果上传失败
                if (res.code!=200) {
                    return layer.msg('上传失败');
                }
                //上传成功
                if(res.code == 200){
                    $('#attachmentUrl').val(res.data.fileUrl);
                    $('#attachmentName').val(res.data.fileName);
                }
            }
            // , error: function () {
            //     //演示失败状态，并实现重传
            //     var demoText = $('#demoText');
            //     demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            //     demoText.find('.demo-reload').on('click', function () {
            //         uploadInst.upload();
            //     });
            // }
        });

        //监听提交，发送请求
        form.on('submit(add)', function(data){
            $.post("<%=basePath%>advise/retreat",data.field,function(data){
                // 获取 session
                if(data.code!=200){
                    layer.msg(data.msg,{offset: 'auto',icon: 5});
                }
                if(data.code==200){
                    layer.msg(data.msg,{offset: 'auto',icon: 6});
                }
            });
            return false;
        });
    });
</script>

</body>
</html>
