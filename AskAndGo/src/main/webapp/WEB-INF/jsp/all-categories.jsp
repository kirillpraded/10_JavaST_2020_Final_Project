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

    <title><fmt:message key="categories"
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

<div class="mt-4 m-1">
    <c:if test="${not empty requestScope.database_error}">
        <div class="alert alert-danger" role="alert">${database_error}</div>
    </c:if>
    <div class="card-columns mt-5">
        <c:forEach var="category" items="${categories}">

            <div class="card my-2">
                <h5 class="card-header">${category.name}</h5>
                <div class="card-body">
                    <p class="card-text">
                        <tags:clickable-tree category="${category}" space="0"/>
                    </p>
                </div>
            </div>
        </c:forEach>

    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>