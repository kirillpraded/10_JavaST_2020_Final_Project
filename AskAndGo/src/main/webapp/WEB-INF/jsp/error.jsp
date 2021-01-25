<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<html>
<head>
    <meta charset="UTF-8">

    <title><fmt:message key="error"
                        bundle="${ rb }"/></title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    <!-- Custom styles for navbar -->
    <link href="<c:url value="/css/navbar-top-fixed.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/navbar.jsp"/>

<div class="container">

    <c:choose>
        <c:when test="${not empty error}">
            <h2>${error}</h2>
        </c:when>
        <c:when test="${pageContext.errorData.statusCode eq 404}">
            <h2>${pageContext.errorData.statusCode}</h2>
            <p>${pageContext.errorData.requestURI}</p>
            <p><fmt:message key="error-page.not-found"
                             bundle="${ rb }"/></p>
        </c:when>
        <c:when test="${pageContext.errorData.statusCode eq 403}">
            <h2>${pageContext.errorData.statusCode}</h2>
            <p>${pageContext.errorData.requestURI}</p>


            <p><fmt:message key="${msg}"
                             bundle="${ rb }"/></p>
        </c:when>
        <c:when test="${pageContext.errorData.statusCode eq 500}">
            <h2>${pageContext.errorData.statusCode}</h2>
            <p>
                <fmt:message key="error-page.server" bundle="${ rb }"/>
            </p>
        </c:when>
        <c:otherwise>
            <h3>
                <fmt:message key="error-page.server" bundle="${ rb }"/>
            </h3>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="parts/footer.jsp"/>
</body>
</html>
