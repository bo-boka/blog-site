
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <div class="navbar">
        <ul class="nav nav-pills">
            <li role="presentation" class="${pageContext.request.requestURI eq '/BlogCapstone/jsp/home.jsp' ? ' active' : ''}"><a class="menu" href="${pageContext.request.contextPath}/home">Home</a></li>
            <li role="presentation" class="${pageContext.request.requestURI eq '/BlogCapstone/jsp/about.jsp' ? ' active' : ''}"><a class="menu" href="${pageContext.request.contextPath}/about">About</a></li>
            <li role="presentation" class="dropdown" class="${pageContext.request.requestURI eq '/BlogCapstone/jsp/page.jsp' ? ' active' : ''}">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Causes
                    <span class="caret"></span></a>
                <ul id="causesDD" class="dropdown-menu">
                    <c:forEach items="${pages}" var="causePage">
                        <li style='#ffffff'><a href="${pageContext.request.contextPath}/causes/${causePage.id}">${causePage.title}</a></li>
                        </c:forEach>
                </ul>
            </li>
            <sec:authorize access="isFullyAuthenticated()">
                <li role="presentation" class="${pageContext.request.requestURI eq '/BlogCapstone/jsp/admin.jsp' ? ' active' : ''}"><a class="menu" href="${pageContext.request.contextPath}/admin">Admin</a></li>
                </sec:authorize>
            <li role="presentation" class="${pageContext.request.requestURI eq '/BlogCapstone/jsp/blogListDisplay.jsp' ? ' active' : ''}"><a class="menu" href="${pageContext.request.contextPath}/blogListDisplay">Search</a></li>
                <sec:authorize access="!isFullyAuthenticated()">
                <li role="presentation" class="pull-right"><a href="${pageContext.request.contextPath}/login">login</a></li>
                </sec:authorize>
                <sec:authorize access="isFullyAuthenticated()">
                <li role="presentation" class="pull-right"><a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a></li> 
                </sec:authorize>
        </ul>    
    </div>
</div>