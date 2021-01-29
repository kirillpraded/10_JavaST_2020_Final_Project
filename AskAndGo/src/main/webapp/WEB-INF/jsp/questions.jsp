<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<html>
<head>
    <meta charset="utf-8">

    <title>
        <c:choose>
            <c:when test="${not empty category.name}">
                ${category.name}
            </c:when>
            <c:otherwise>
                <fmt:message key="navbar.search"
                             bundle="${ rb }"/>
            </c:otherwise>
        </c:choose>
    </title>
    <tags:styles />
    <style>
        .p {
            width: 250px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>

</head>
<body>
<jsp:include page="parts/navbar.jsp"/>
<div class="container card-columns">
    <c:if test="${not empty category}">
        <div>
            <tags:parent-recursive category="${category}"/>
            <form action="<c:url value="ask"/>" accept-charset="UTF-8">
                <input type="hidden" id="category_id" name="category_id" value="${category.id}">
                <button type="submit" class="btn btn-primary"><fmt:message key="question.ask"
                                                                           bundle="${ rb }"/></button>
            </form>
        </div>

    </c:if>
    <c:if test="${empty questions}">
        <h1><fmt:message key="questions.not.found" bundle="${ rb }"/></h1>
    </c:if>
    <hr>
    <br>
    <p></p>
    <div class="container">
        <c:forEach var="question" items="${questions}">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title"><c:if test="${question.containsCorrectAnswer}">&#9989; </c:if>${question.title}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${question.author.username}</h6>
                    <p class="p card-text">${question.text}</p>
                    <a href="<c:url value="/question?question_id=${question.id}"/>" class="card-link"><fmt:message
                            key="question.view"
                            bundle="${ rb }"/></a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>