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
<body background="../../images/323833.jpg" >

<div>
	   <!-- header start -->
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
            <p>
                <br><br><br><br>
            <h3>你来人间一趟，你要看看太阳</h3>
            <br><br>
           <%-- <h3>邮箱:<a href="mailto:withstars@126.com">withstars@126.com</a></h3>--%>
            <br><br><br><br>
            </p>
        </article>
    </div>

</div>

<jsp:include page="common/footer.jsp"/>
</div>
</div>
</body>
</html>