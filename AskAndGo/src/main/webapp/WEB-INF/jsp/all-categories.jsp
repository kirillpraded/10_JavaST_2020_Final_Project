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

    <tags:styles />

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