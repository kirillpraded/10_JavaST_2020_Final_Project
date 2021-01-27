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

    <title>${category.name}</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <style>
        .p {
            width: 250px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .under {
            margin-top: 6%;
        }

    </style>
    <!-- Custom styles for navbar -->
    <link href="<c:url value="/css/navbar-top-fixed.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="parts/navbar.jsp"/>
<div class="container under card-columns">
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