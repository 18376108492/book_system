<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
   <header id="header">
        <nav>
            <ul>
                <li>
                    <a href="/">首页</a>
                    <a href="/about">关于</a>
                </li>
            </ul>
            
            <div class="my-info" onmouseover="hiddeewm()" onmouseout="hiddeewm()">
                <figure></figure>
                <span>个人博客</span>
                <div id="hiddenewm" hidden="true" >
                    <img src="../images/me.jpg" width="200px" height="200px" >
                    <p></p>
                </div>
                
            </div>
        </nav>
    </header>
</html>