<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Page - Boss</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <%@include file="carouselFragment.jsp" %>
        
        <%@include file="headerFragment.jsp" %>
        
        <div class="container">        
            <div class="col-md-8">
                <table id="HomeTable" class="table table-hover">
                    <tbody id="HomeRows"></tbody>
                </table>
            </div>

            <div class="col-md-2">

            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/Home.js"></script>
        <script src="${pageContext.request.contextPath}/js/pageAdmin.js"></script>

    </body>
</html>

