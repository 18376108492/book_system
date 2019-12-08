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
<body background="../../images/323300.jpg" >

<div>
    <!-- header start -->
 <jsp:include page="common/header.jsp"/>
   <div id="bg" >
       <p>
           人生是一场来之不易的修行
           <br>
           请不要轻易放弃
       </p>
    </div>
</div>
<div class="progress">
  <div class="progress-bar progress-bar-danger progress-bar-striped" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
    <span class="sr-only">80% Complete (danger)</span>
  </div>
</div>
<div class="search" style="float: right;">
	 	<form name="from" class="navbar-form navbar-left" role="search" action="/admin/article/search" method="get" onsubmit=" return checkFrom()">
                   <div class="form-group">
                  <input type="text" name="keyword" class="form-control" placeholder="Search">
                 </div>
                <button type="submit" class="btn btn-default">搜索</button>
               </form>
</div>
<!--清除浮动-->
<div style="clear: both"></div>
<div id="container" >
<c:forEach items="${articles}" var="article">
    <article class="article" >
        <time>${article.localTime}</time>
        <h2 class="title"><a href="/article/getArticlebyId?id=${article.id}">${article.title}</a></h2>
        <span><i>${article.keywords}</i></span>
        <section class="article-content markdown-body">
            <blockquote>
                <p>${article.desci}</p>
            </blockquote>
            ......
        </section>
        <footer>
            <a href="/article/getArticlebyId?id=${article.id}">阅读全文</a>
        </footer>
    </article>
</c:forEach>
        <div style="text-align: center">
            <ul class="pagination" >
                <li <c:if test="${pageInfo.pageNum==1}">class="disabled"</c:if>><a href="/?page=1">&laquo;</a></li>
                <c:forEach begin="1" end="${pageInfo.total}" step="1" var="pageNo">
                    <li <c:if test="${pageInfo.pageNum==pageNo}">class="active"</c:if>><a href="/?page=${pageNo}">${pageNo}</a></li>
                </c:forEach>
                <li <c:if test="${pageInfo.pageNum==pageInfo.total}">class="disabled"</c:if>><a href="/?page=${pageInfo.total}">&raquo</a></li>
            </ul>
        </div>
</div>
    <jsp:include page="common/footer.jsp"/>
<script src="../../js/article_list.js"></script>
</body>
</html>