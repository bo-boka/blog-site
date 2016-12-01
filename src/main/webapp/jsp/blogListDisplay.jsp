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

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        
        <%@include file="carouselFragment.jsp" %>
        
        <div class="container">
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/about">About</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Causes
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a role="presentation"><a href="${pageContext.request.contextPath}/causes">Placeholder</a></li>
                            <li><a role="presentation"><a href="${pageContext.request.contextPath}/causes">Placeholder</a></li>
                            <li><a role="presentation"><a href="${pageContext.request.contextPath}/causes">Placeholder</a></li> 
                        </ul>
                    </li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/blogListDisplay">Search</a></li>
                </ul>    
            </div>
        </div>

        <div class="container">        
            <div class="col-md-8">
                <table id="HomeTable" name="HomeTable" class="table table-hover">
                    <tbody id="HomeRows" name="HomeRows"></tbody>
                </table>
            </div>
        
            <div class="col-md-2">
                Categories:
                <select name="searchCategory" id="searchCategory">
                    <option value="">---</option>
                    <option value="Storms">Storms</option>
                    <option value="Causes">Causes</option>
                    <option value="Philanthropy">Philanthropy</option>
                    <option value="Events">Events</option>
                    <option value="Other">Other</option>
                </select>
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/BlogListDisplay.js"></script>

    </body>
</html>