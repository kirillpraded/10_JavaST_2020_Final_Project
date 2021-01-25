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

    <title>${question.title}</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <link href="<c:url value="/css/navbar-top-fixed.css" />" rel="stylesheet">

</head>
<body>
<jsp:include page="parts/navbar.jsp"/>

<c:if test="${question.author.id eq sessionScope.auth_user.id and not question.closed}">
    <div class="ml-5">
        <form action="<c:url value="/close"/>" method="post">
            <input type="hidden" name="question_id" value="${question.id}">
            <input type="hidden" value="${question.author.id}" name="user_id">
            <button type="submit" class="btn btn-primary"><fmt:message key="question.close"
                                                                       bundle="${ rb }"/></button>
        </form>
    </div>
</c:if>
<c:if test="${not empty requestScope.message}">
    <div class="alert alert-danger" role="alert">
        <fmt:message key="${requestScope.message}" bundle="${ rb }"/>
    </div>
</c:if>
<div class="container mt-5">
    <div>
        <c:if test="${question.closed}">
            <p><strong><fmt:message key="question.closed"
                                    bundle="${ rb }"/></strong></p>
        </c:if>
        <div class="card mb-3" style="max-width: 1000px;">

            <div class="row g-0">
                <div class="col-md-4">
                    <img src="<c:url value="/img/user/${question.author.profileImage}"/>"
                         style="max-height: 200px; max-width: 200px;">
                </div>
                <div class="col-md-8">

                    <div class="mt-1">
                        <c:if test="${sessionScope.auth_user.id eq question.author.id}">

                            <form action="<c:url value="/edit-question"/>" method="get">
                                <input type="hidden" name="question_id" value="${question.id}">
                                <input type="hidden" name="user_id" value="${question.author.id}">
                                <button value="submit" class="btn btn-primary"><fmt:message key="edit"
                                                                                            bundle="${ rb }"/></button>
                            </form>

                        </c:if>
                        <c:if test="${question.author.id eq sessionScope.auth_user.id
                                            or sessionScope.auth_user.role.name() eq 'MODERATOR'}">

                            <form action="<c:url value="/delete-question"/>" method="post">
                                <input type="hidden" value="${question.id}" name="question_id">
                                <input type="hidden" value="${question.author.id}" name="user_id">
                                <button type="submit" class="btn btn-warning">
                                    <fmt:message key="delete" bundle="${ rb }"/>
                                </button>
                            </form>

                        </c:if>
                        <c:if test="${sessionScope.auth_user.role.name() eq 'MODERATOR'
                                    and question.author.role.name() != 'READER'
                                    and question.author.username != sessionScope.auth_user.username}">

                            <form action="<c:url value="/block-author"/>" method="post">
                                <input type="hidden" value="${question.author.id}" name="user_id">
                                <input type="hidden" value="${question.id}" name="question_id">
                                <button type="submit" class="btn btn-danger"><fmt:message key="author.block"
                                                                                          bundle="${ rb }"/></button>
                            </form>

                        </c:if>
                    </div>

                    <div class="card-body">
                        <h5 class="card-title">${question.author.username} ${question.date}</h5>
                        <p class="card-text"><b>${question.title}</b></p>
                        <p class="card-text">${question.text}</p>
                        <c:if test="${not empty question.imageName}">
                            <details>
                                <summary><fmt:message key="screenshot"
                                                      bundle="${ rb }"/></summary>
                                <img src="<c:url value="/img/question/${question.imageName}"/>"
                                     style="max-height: 200px; max-width: 200px;">
                            </details>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:forEach var="answer" items="${question.answers}">
    <div class="container">
        <div class="card mb-3" style="max-width: 1000px;">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="<c:url value="/img/user/${answer.author.profileImage}"/>"
                         style="max-height: 200px; max-width: 200px;">
                </div>
                <div class="col-md-8">

                    <div class="mt-1">
                        <c:if test="${answer.author.id eq sessionScope.auth_user.id
                                            or sessionScope.auth_user.role.name() eq 'MODERATOR'}">

                            <form class="m-1" action="<c:url value="/delete-answer"/>" method="post">
                                <input type="hidden" value="${answer.id}" name="answer_id">
                                <input type="hidden" value="${question.id}" name="question_id">
                                <input type="hidden" value="${answer.author.id}" name="user_id">

                                <button type="submit" class="btn btn-warning"><fmt:message key="delete"
                                                                                           bundle="${ rb }"/></button>
                            </form>

                        </c:if>
                        <c:if test="${sessionScope.auth_user.id eq answer.author.id}">

                            <form action="<c:url value="/edit-answer"/>" method="get">
                                <input type="hidden" name="answer_id" value="${answer.id}">
                                <input type="hidden" name="user_id" value="${answer.author.id}">
                                <button value="submit" class="btn btn-primary"><fmt:message key="edit"
                                                                                            bundle="${ rb }"/></button>
                            </form>

                        </c:if>

                        <c:if test="${sessionScope.auth_user.role.name() eq 'MODERATOR'
                                    and answer.author.role.name() != 'READER'
                                    and answer.author.username != sessionScope.auth_user.username}">

                            <form class="m-1" action="<c:url value="/block-author"/>" method="post">
                                <input type="hidden" value="${answer.author.id}" name="user_id">
                                <input type="hidden" value="${question.id}" name="question_id">
                                <button type="submit" class="btn btn-danger"><fmt:message key="author.block"
                                                                                          bundle="${ rb }"/></button>
                            </form>

                        </c:if>
                        <c:choose>
                            <c:when test="${answer.correct}">
                                &#9989; <fmt:message key="answer.correct"
                                                     bundle="${ rb }"/>
                            </c:when>
                            <c:when test="${question.author.id eq sessionScope.auth_user.id
                                    and answer.author.id != sessionScope.auth_user.id
                                    and answer.correct eq false}">
                                <form class="m-1" method="post" action="<c:url value="/update-correct"/>">
                                    <input type="hidden" name="question_id" value="${question.id}">
                                    <input type="hidden" name="answer_id" value="${answer.id}">
                                    <input type="hidden" name="user_id" value="${question.author.id}">
                                    <button type="submit" class="btn btn-secondary"><fmt:message
                                            key="mark.correct"
                                            bundle="${ rb }"/></button>
                                </form>

                            </c:when>
                        </c:choose>
                    </div>

                    <div class="card-body">
                        <h5 class="card-title">${answer.author.username} ${question.date}</h5>
                        <p class="card-text">${answer.text}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
<c:if test="${not question.closed and sessionScope.auth_user.role.name() eq 'WRITER'}">
    <div class="container">
        <div class="mb-3" style="max-width: 1000px;">
            <div class="row g-0">
                <div class="col-md-8">
                    <form accept-charset="UTF-8" action="<c:url value="/answer"/>" method="post">
                        <div class="m-3">
                            <input type="hidden" value="${question.id}" id="question_id" name="question_id">
                            <div class="form-group">
                                <c:if test="${not empty requestScope.connection_error_message}">
                                    <div class="alert alert-danger" role="alert"><fmt:message
                                            key="${connection_error_message}"
                                            bundle="${ rb }"/></div>
                                </c:if>

                                <fmt:message key="answer.text"
                                             bundle="${ rb }"/>
                                <c:if test="${not empty error_message}">
                                    <div class="alert alert-danger" role="alert">
                                        <fmt:message key="${error_message}" bundle="${ rb }"/>
                                    </div>
                                </c:if>
                                <div class="input-group">

                                <textarea
                                        class="form-control"
                                        rows="12"
                                        name="text"
                                        id="text"
                                        aria-label="With textarea"
                                        required>${answer_text}</textarea>

                                </div>
                            </div>
                            <button type="submit" class="btn btn-secondary btn-sm mt-2"><fmt:message key="submit"
                                                                                                     bundle="${ rb }"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:if>
<jsp:include page="parts/footer.jsp"/>
</body>
</html>