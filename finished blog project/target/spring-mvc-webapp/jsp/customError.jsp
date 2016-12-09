<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link href="https://fonts.googleapis.com/css?family=Calligraffitti" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
    <center>
        <div class="container" id="blogName">
            <h1>Amelia's Blog</h1>
            <h4>Storm Chasing Baby Saving Laser Jet Philanthropist</h4><br>
        </div> 
    </center>
    <hr/>
    <%@include file="headerFragment.jsp" %>
    <div class="container">
        <div class="row">
            <h1>An error has occurred...</h1>
            <h3 style="color: white">${errorMessage}</h3>
        </div>
    </div>
    <div class="container">    
        <img src="https://s-media-cache-ak0.pinimg.com/originals/b7/dd/4a/b7dd4ac35daf24d48d06190d3c9dfd74.gif">
    </div>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

