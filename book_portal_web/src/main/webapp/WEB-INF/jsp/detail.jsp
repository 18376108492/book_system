<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>博客系统</title>
    <link rel="shortcut icon" type="image/x-icon" href="../../images/web-icon.png" media="screen" />
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/base.css">
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
  
</head>

<body background="../../images/320238.jpg" >

<div>
    <jsp:include page="common/header.jsp"/>
    <div id="bg" >
        <p>
            重要的不是赚钱
            <br>
            而是让你有一段难忘的经历
        </p>
    </div>
    <div id="container">
<article class="article">
            <time id="time1">${article.localTime}</time>
            <h2 style="text-align: center; ">${article.title}</h2>
            <p style="position: center" class="text-info">点击量:${article.click}</p>
            <section>
                <blockquote>
                    <p>${article.desci}</p>
                </blockquote>
                <p id="zhengwen">
                    ${article.content}
                </p>
                <p style="text-align:center;color:#ccc;font-size:12px;margin-top:40px;">
                    希望你今年过得比去年好一点
                    <br>
                    是因为有我
                </p>
                <p style="margin: 5em 0 1em;text-align: center;color: #83b8ec;font-size: .8em">
                    <span>Have a nice day :)</span>
                </p>
            </section>
        </article>
    </div>
    <% int i =1;    %>
    <c:forEach items="${comments}" var="comment">


        <article class="comment">
                <section style="text-align:left">
                <%= i++  %>楼&nbsp;&nbsp;${comment.name}&nbsp;&nbsp;${comment.date}<br/><br/>
                <p>${comment.content}</p><br/>
                </section>
            </article>
    </c:forEach>
    		<div class="form-horizontal" role="form" style="margin:10px">
    			<div class="form-group">
                        <label for="inputPassword" class="col-sm-2 control-label">评论</label>
                        <div class="col-sm-3">
                               <textarea id="content"  class="form-control" rows="3"  placeholder="文明上网，理性发言" ></textarea>
                         </div>
                 </div>
        <input id="articleId" type="hidden" value="${article.id}" >
    					<div class="form-group">
                    		<label for="name" class="col-sm-2 control-label">昵称</label>

                    		<div class="col-sm-3">
                    			<input type="text" id="name" class="form-control"
                    				   placeholder="昵称">
                    		</div>
                    	</div>
                    	<div class="form-group">
                               <label for="email" class="col-sm-2 control-label">邮箱</label>
                                <div class="col-sm-3">
                                  <input type="email" id="email" class="form-control"  placeholder="邮箱">
                                </div>
                        </div>
                        <div class="form-group" style="position:relative;left:13%">
     <br/>
        <p style="text-align: right;color: red;position: absolute" id="info"></p>
        <br/>
     <button id="commentButton" class="btn btn-default" type="submit">提交</button>
                                                </div>

    			</div>
    			 <script>

                        $("#commentButton").click(function () {
                            if($("#content").val()==''&&$("#name").val()==''&&$("#email").val()==''){
                                $("#info").text("提示:请输入评论内容,昵称和邮箱");
                            }
                            else if ($("#content").val()==''){
                                $("#info").text("提示:请输入评论内容");
                            }
                            else if($("#name").val()==''){
                                $("#info").text("提示:请输入昵称");
                            }
                            else if($("#email").val()==''){
                                $("#info").text("提示:请输入邮箱");
                            }
                            else {
                             $("#info").text("");
                                $.ajax({
                                    type: "POST",
                                    url: "/comment/addComment",
                                    data: {
                                        content: $("#content").val() ,
                                        name: $("#name").val(),
                                        email: $("#email").val(),
                                        articleId:$("#articleId").val(),
                                    },
                                    dataType: "json",
                                    success: function(data) {
                                            $("#info").text(data.msg);
                                    }
                                });
                            }
                        })

                    </script>
    <div style="position: relative;left: 12%">
        <c:if test="${!empty lastArticle }">
            <div ><a href="/article/?id=${lastArticle.id}"><h4><span class="label label-primary">上一篇:${lastArticle.title}</span></h4></a></div>
        </c:if>
        <c:if test="${!empty nextArticle }">
            <div><a href="/article/?id=${nextArticle.id}"><h4><span class="label label-success">下一篇:${nextArticle.title}</span></h4></a></div>
        </c:if>
    </div>
    </div>

     <jsp:include page="common/footer.jsp"/>
</div>
</div>
</body>
</html>