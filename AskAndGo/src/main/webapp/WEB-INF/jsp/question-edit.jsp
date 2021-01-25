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
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">

    <h1><fmt:message key="question.edit.form"
                     bundle="${ rb }"/></h1>
    <div class="form-group mt-3">
        <c:if test="${not empty requestScope.message}">
            <div class="alert alert-danger" role="alert">${requestScope.message}</div>
        </c:if>
        <form action="<c:url value="/edit-question" />" method="post" accept-charset="UTF-8">
            <div class="form-group">
                <fmt:message key="title"
                             bundle="${ rb }"/> <input type="text"
                                                       class="form-control <c:if test="${not empty requestScope.title_error}">is-invalid</c:if>"
                                                       id="title" placeholder="Title"
                                                       name="title" value="${requestScope.question.title}" required/>
                <c:if test="${not empty requestScope.title_error}">
                    <div class="invalid-feedback">
                        <fmt:message key="${requestScope.title_error}" bundle="${ rb }"/>
                    </div>
                </c:if>

            </div>
            <div class="form-group">
                <fmt:message key="text"
                             bundle="${ rb }"/>
                <p>
                    <c:if test="${not empty requestScope.text_error}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${requestScope.text_error}" bundle="${ rb }"/>
                        </div>
                    </c:if>
                </p>
                <div class="input-group">
                    <textarea class="form-control" rows="12" name="text" id="text"
                              aria-label="With textarea" required>${requestScope.question.text}</textarea>
                    <c:if test="${not empty requestScope.text_error}">
                        <div class="invalid-feedback">
                            <fmt:message key="${requestScope.text_error}" bundle="${ rb }"/>
                        </div>
                    </c:if>
                </div>
            </div>
            <input type="hidden" name="user_id" value="${requestScope.question.author.id}">
            <input type="hidden" name="question_id" value="${requestScope.question.id}">

            <button type="submit" class="btn btn-primary"><fmt:message key="submit"
                                                                       bundle="${ rb }"/></button>
        </form>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>