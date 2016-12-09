<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Amelia's Blogs</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <link href="https://fonts.googleapis.com/css?family=Calligraffitti" rel="stylesheet">

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
        <div class="col-sm-offset-2 col-sm-8">
            <div id="firstBlog"></div>
            <hr>
            <div id="HomeRows"></div>
            <div id="nextHomeRows" class="read-more-wrap"></div>
            <br/>
            <p><button class="btn btn-alert" id="blogHomeExpand">Show All</button></p>
        </div>

        <div class="col-md-2">

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

