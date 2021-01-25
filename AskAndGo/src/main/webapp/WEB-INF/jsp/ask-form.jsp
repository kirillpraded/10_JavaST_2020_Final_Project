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

    <title><fmt:message key="ask.title"
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

    <h1><fmt:message key="ask.form"
                     bundle="${ rb }"/></h1>
    <div class="form-group mt-3">
        <c:if test="${not empty requestScope.message}">
            <div class="alert alert-danger" role="alert"><fmt:message key="${message}" bundle="${ rb }"/></div>
        </c:if>
        <form action="<c:url value="/ask" />" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
            <div class="form-group">
                <fmt:message key="title" bundle="${ rb }"/> <input type="text"
                                                       class="form-control <c:if test="${not empty requestScope.title_error}">is-invalid</c:if>"
                                                       id="title"
                                                       placeholder="<fmt:message key="title" bundle="${ rb }"/>"
                                                       name="title"
                                                       value="${requestScope.title}" required/>
                <c:if test="${not empty requestScope.title_error}">
                    <div class="invalid-feedback">
                        <fmt:message key="${title_error}" bundle="${ rb }"/>
                    </div>
                </c:if>

            </div>
            <div class="form-group">
                <fmt:message key="text"
                             bundle="${ rb }"/>
                <div class="input-group">
                    <textarea class="form-control <c:if test="${not empty requestScope.text_error}">is-invalid</c:if>" rows="12" name="text" id="text"
                              aria-label="With textarea" required><c:if test="${not empty requestScope.text}">${requestScope.text}</c:if></textarea>
                    <c:if test="${not empty requestScope.text_error}">
                        <div class="invalid-feedback">
                            <fmt:message key="${text_error}" bundle="${ rb }"/>
                        </div>
                    </c:if>

                </div>

            </div>
            <div class="form-group">
                <fmt:message key="tags"
                             bundle="${ rb }"/> <input type="text"
                                                         class="form-control" id="tags" value="${tags}"
                                                         placeholder="<fmt:message key="tag" bundle="${ rb }"/>"
                                                         name="tags"/>
                <c:if test="${not empty requestScope.tags_error}">
                    <div class="invalid-feedback">
                        <fmt:message key="${tags_error}" bundle="${ rb }"/>
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <fmt:message key="screenshot"
                             bundle="${ rb }"/><input type="file" name="image"/>
            </div>

            <div class="form-group">
                <label for="category_id"></label>
                <c:if test="${not empty requestScope.error_category_message}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="${error_category_message}" bundle="${ rb }"/>
                    </div>
                </c:if>
                <fmt:message key="ask.select.category"
                             bundle="${ rb }"/>
                <select name="category_id" size="1" id="category_id">
                    <c:if test="${current_category != null}">
                        <c:forEach var="category" items="${categories}">
                            <tags:ask-form-tree category="${category}" space="" current_category="${current_category}"/>
                        </c:forEach>
                    </c:if>
                    <c:if test="${current_category == null}">
                        <c:forEach var="category" items="${categories}">
                            <tags:ask-form-tree category="${category}" space=""/>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="submit" bundle="${ rb }"/></button>
        </form>
    </div>
</div>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>