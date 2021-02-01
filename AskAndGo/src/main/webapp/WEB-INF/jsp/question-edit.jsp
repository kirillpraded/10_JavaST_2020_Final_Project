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

    <title><fmt:message key="question.edit.title"
                        bundle="${ rb }"/></title>

    <tags:styles />

</head>
<body>
<jsp:include page="parts/navbar.jsp"/>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">

    <h1><fmt:message key="question.edit.form"
                     bundle="${ rb }"/></h1>
    <div class="form-group mt-3">
        <c:if test="${not empty requestScope.message}">
            <div class="alert alert-danger" role="alert">${requestScope.message}</div>
        </c:if>
        <form action="<c:url value="/app/edit-question" />" id="questionEditForm" method="post" accept-charset="UTF-8">
            <div class="form-group">
                <fmt:message key="title"
                             bundle="${ rb }"/> <input type="text"
                                                       class="form-control <c:if test="${not empty requestScope.title_error}">is-invalid</c:if>"
                                                       id="title" placeholder="Title"
                                                       name="title" value="${requestScope.question.title}" required/>
                <c:choose>
                    <c:when test="${not empty requestScope.title_error}">
                        <div class="invalid-feedback">
                            <fmt:message key="${requestScope.title_error}" bundle="${ rb }"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="invalid-feedback">
                            <fmt:message key="question.title.validation.error" bundle="${ rb }"/>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="form-group">
                <fmt:message key="text"
                             bundle="${ rb }"/>

                <textarea class="form-control <c:if test="${not empty requestScope.text_error}">is-invalid</c:if>"
                          rows="12" name="text" id="text"
                          aria-label="With textarea" required>${requestScope.question.text}</textarea>
                <c:choose>
                    <c:when test="${not empty requestScope.text_error}">
                        <div class="invalid-feedback">
                            <fmt:message key="${requestScope.text_error}" bundle="${ rb }"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="invalid-feedback">
                            <fmt:message key="question.text.validation.error" bundle="${ rb }"/>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
            <input type="hidden" name="user_id" value="${requestScope.question.author.id}">
            <input type="hidden" name="question_id" value="${requestScope.question.id}">
            <input type="hidden" value="${param.page}" name="page">

            <button type="submit" class="btn btn-primary"><fmt:message key="submit"
                                                                       bundle="${ rb }"/></button>
        </form>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
<script src="<c:url value="/js/validators.js" />"></script>
<script src="<c:url value="/js/questionEditValidation.js"/>"></script>
</body>
</html>