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

    <tags:styles/>

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
        <form action="<c:url value="/ask" />" id="askForm" method="post" accept-charset="UTF-8"
              enctype="multipart/form-data">
            <div class="form-group">
                <fmt:message key="title" bundle="${ rb }"/> <input type="text"
                                                                   class="form-control <c:if test="${not empty requestScope.title_error}">is-invalid</c:if>"
                                                                   id="title"
                                                                   placeholder="<fmt:message key="title" bundle="${ rb }"/>"
                                                                   name="title"
                                                                   value="${requestScope.title}" required/>
                <c:choose>
                    <c:when test="${not empty requestScope.title_error}">
                        <div class="invalid-feedback">
                            <fmt:message key="${title_error}" bundle="${ rb }"/>
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
                <div class="input-group">
                    <textarea class="form-control <c:if test="${not empty requestScope.text_error}">is-invalid</c:if>"
                              rows="12" name="text" id="text"
                              aria-label="With textarea" required><c:if
                            test="${not empty requestScope.text}">${requestScope.text}</c:if></textarea>
                    <c:choose>
                        <c:when test="${not empty requestScope.text_error}">
                            <div class="invalid-feedback">
                                <fmt:message key="${text_error}" bundle="${ rb }"/>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="invalid-feedback">
                                <fmt:message key="question.text.validation.error" bundle="${ rb }"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

            </div>
            <div class="form-group">
                <fmt:message key="tags"
                             bundle="${ rb }"/> <input type="text"
                                                       class="form-control" id="tags" value="${tags}"
                                                       placeholder="<fmt:message key="tag" bundle="${ rb }"/>"
                                                       name="tags"/>
                <c:choose>
                    <c:when test="${not empty requestScope.tags_error}">
                        <div class="invalid-feedback">
                            <fmt:message key="${tags_error}" bundle="${ rb }"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="invalid-feedback">
                            <fmt:message key="tags.validation.error" bundle="${ rb }"/>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group">
                <fmt:message key="screenshot"
                             bundle="${ rb }"/><input type="file" name="image" multiple="false"
                                                      accept="image/jpeg,image/png,image/gif,image/tiff,image/jpg, image/bmp"/>
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
<script src="<c:url value="/js/validators.js" />"></script>
<script src="<c:url value="/js/questionValidation.js"/>"></script>
</body>
</html>