<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/decoration.css" rel="stylesheet" type="text/css">
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
    <hr>
    <%@include file="headerFragment.jsp" %>
        <div class="container" id="searchBar">
            <center><h1 id="blogName" style="color: white; display: inline">Search By:</h1>
            <select name="searchCategory" id="searchCategory" style="display: inline">
                <option value="---">Choose..</option>
                <option value="Title">Title</option>
                <option value="Category">Category</option>
                <option value="Author">Author</option>
                <option value="Tags">Tags</option>
            </select></center>
            <br/>
            <center>
            <input type="text" id="searchInfo" placeholder="Search..">
            <br/>
            <br/>
            <button type="button" id="searchButton">Go</button></center>
            <div id="HomeRows"></div>
        </div>     

    <%@include file="footerFrag.jsp" %>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/BlogListDisplay.js"></script>

</body>
</html>