
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../../css/bootstrap4.0.min.css" >
    <script src="../../../js/jquery.slim.min.js" ></script>
    <script src="../../../js/popper.min.js" ></script>
    <script src="../../../js/bootstrap4.0.min.js"></script>
    <script type="text/javascript" src="../../js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="../../js/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript"  src="../../js/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<div style="position: relative;top: 10%">
    <div class="alert alert-success" role="alert">
        <label> ${msg}</label>
        <label><a href="/admin/main">返回后台主页</a></label>
    </div>
</div>
<div class="container">
    <form id="editForm" action="/admin/article/edit/do" method="post">
        <input type="hidden" value="${article.id}" name="id">
        <input type="hidden" value="${article.pic}" name="pic">
        <input type="hidden" value="${article.click}" name="click">
        <input type="hidden" value="${article.time}" name="time">

        <div class="form-group">
            <label for="title">文章标题</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="文章标题" value="${article.title}">
        </div>
        <div class="form-group">
            <label for="catalogId">栏目</label>
            <select class="form-control" id="catalogId" name="catalogId">
                <option value="0" <c:if test="${article.catalogId==0}">selected="selected"</c:if>>学习</option>
                <option value="1" <c:if test="${article.catalogId==1}">selected="selected"</c:if>>生活</option>
            </select>
        </div>
        <div class="form-group">
            <label for="keywords">关键字</label>
            <input type="text" class="form-control" id="keywords" name="keywords" placeholder="关键字" value="${article.keywords}">
        </div>
        <div class="form-group">
            <label for="desci">简介</label>
            <textarea class="form-control" id="desci" rows="3" name="desci" placeholder="简介">${article.desci}</textarea>
        </div>
        <div id="cont" style="display: none">
            ${article.content}
        </div>
        <div class="form-group">
            <label for="editor">内容</label>
            <textarea class="form-control"  type="text/plain" id="editor" name="content" rows="15"> ${article.content}</textarea>
            <%--<script id="editor" type="text/plain"  name="content" style="width:1024px;height:500px;" >--%>

            <%--</script>--%>
            </div>
            <input type="submit" />
                </form>

                <script type="text/javascript">
                // $(function(){
                //     var ue = UE.getEditor('editor');
                //     ue.ready(function() {
                //         ue.setContent($("#cont").html());
                //     });
                // });
            </script>
        </div>
</body>
</html>