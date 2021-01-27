<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib prefix="ctg" uri="custom" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>

<html>
<head>
    <meta charset="UTF-8">

    <title><fmt:message key="edit.answer"
                        bundle="${ rb }"/></title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .under {
            margin-top: 6%;
        }
    </style>
    <!-- Custom styles for navbar -->
    <link href="<c:url value="/css/navbar-top-fixed.css" />" rel="stylesheet">

</head>
<body>
<jsp:include page="parts/navbar.jsp"/>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">

    <h1>Answer Edit Form</h1>
    <div class="form-group mt-3">
        <c:if test="${not empty requestScope.message}">

        </c:if>
        <form action="<c:url value="/edit-answer" />" id="addAnswerForm" method="post" accept-charset="UTF-8">
            <div class="form-group">
                Text
                <textarea class="form-control <c:if test="${not empty requestScope.message}">is-invalid</c:if>" rows="12" name="text" id="text"
                              aria-label="With textarea" required>${requestScope.answer.text}</textarea>
                <c:choose>
                    <c:when test="${not empty requestScope.message}">
                        <div class="invalid-feedback">
                            <fmt:message key="${message}" bundle="${ rb }"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="invalid-feedback">
                            <fmt:message key="answer.validation.error" bundle="${ rb }"/>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
            <input type="hidden" name="user_id" value="${requestScope.answer.author.id}">
            <input type="hidden" name="answer_id" value="${requestScope.answer.id}">
            <input type="hidden" name="question_id" value="${requestScope.answer.question.id}">

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
<script src="<c:url value="/js/validators.js" />"></script>
<script src="<c:url value="/js/answerValidation.js"/>"></script>
</body>
</html>