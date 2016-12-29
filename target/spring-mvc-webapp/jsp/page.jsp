<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Illest Blog on the Net</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <link href="https://fonts.googleapis.com/css?family=Calligraffitti" rel="stylesheet">
    </head>
    <body>
    <center>
        <div class="text-center" id="blogName">
            <h1>Amelia's Blog</h1>
            <h4>Storm Chasing Baby Saving Laser Jet Philanthropist</h4><br>
        </div> 
    </center>
    <%@include file="carouselFragment.jsp" %>
    <hr/>
    <div class="container">
        <%@include file="headerFragment.jsp" %>
        <center><h1>Causes</h1></center>
        <h2 style="color: white">${cause.title}</h2>
        <div id="aboutDiv">     
            ${cause.content}
        </div>
    </div>
    <%@include file="footerFrag.jsp" %>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/Home.js"></script>
    <script src="${pageContext.request.contextPath}/js/pageAdmin.js"></script>

</body>
</html>

