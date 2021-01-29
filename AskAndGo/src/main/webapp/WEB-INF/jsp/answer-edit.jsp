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

    <tags:styles />

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