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

    <tags:styles />
    <style>
        .center {
            margin-left: auto;
            margin-right: auto;
            width: 6em
        }
    </style>

</head>
<body>
<jsp:include page="parts/navbar.jsp"/>

<c:if test="${question.author.id eq sessionScope.auth_user.id and not question.closed}">
    <div class="ml-5">
        <form action="<c:url value="/close"/>" method="post">
            <input type="hidden" name="question_id" value="${question.id}">
            <input type="hidden" value="${question.author.id}" name="user_id">
            <input type="hidden" value="${param.page}" name="page">

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
                                <input type="hidden" value="${param.page}" name="page">
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
                                <input type="hidden" value="${param.page}" name="page">
                                <button type="submit" class="btn btn-danger"><fmt:message key="author.block"
                                                                                          bundle="${ rb }"/></button>
                            </form>

                        </c:if>
                    </div>

                    <div class="card-body">
                        <h5 class="card-title"><a href="<c:url value="/user?user_id=${question.author.id}"/>">${question.author.username}</a> ${question.date}</h5>
                        <p class="card-text"><b>${question.title}</b></p>
                        <p class="card-text">${question.text}</p>
                        <p class="card-text">
                            <small class="text-muted"><c:forEach var="tag" items="${question.tags}"><a href="<c:url value="/search?q=${tag.text}"/>">${tag.text}</a> </c:forEach>
                            </small>
                        </p>
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
<hr>
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
                                <input type="hidden" value="${param.page}" name="page">
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
                                <input type="hidden" value="${param.page}" name="page">
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
                                    <input type="hidden" value="${param.page}" name="page">

                                    <button type="submit" class="btn btn-secondary"><fmt:message
                                            key="mark.correct"
                                            bundle="${ rb }"/></button>
                                </form>

                            </c:when>
                        </c:choose>
                    </div>

                    <div class="card-body">
                        <h5 class="card-title"><a href="<c:url value="/user?user_id=${answer.author.id}"/>">${answer.author.username}</a> ${answer.date}</h5>
                        <p class="card-text">${answer.text}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
<div class="center">
    <nav aria-label="...">
        <ul class="pagination">
            <li class="page-item <c:if test="${param.page eq 1}">disabled</c:if>">
                <a class="page-link" href="<c:url value="/question?question_id=${question.id}&page=${param.page - 1}"/>" tabindex="-1"
                        <c:if test="${param.page eq 1}">
                            aria-disabled="true"
                        </c:if>
                >Previous</a>
            </li>
            <li class="page-item active" aria-current="page">
                <a class="page-link" href="#">${param.page}</a>
            </li>

            <li class="page-item <c:if test="${question.answers.size() < 5}">disabled</c:if>">
                <a class="page-link" href="<c:url value="/question?question_id=${question.id}&page=${param.page + 1}" />"
                        <c:if test="${question.answers.size() < 5}">
                            aria-disabled="true"
                        </c:if>
                >Next</a>
            </li>
        </ul>
    </nav>
</div>
<c:if test="${not question.closed and sessionScope.auth_user.role.name() eq 'WRITER'}">
    <div class="container">
        <div class="mb-3" style="max-width: 1000px;">
            <div class="row g-0">
                <div class="col-md-8">
                    <form accept-charset="UTF-8" id="addAnswerForm" action="<c:url value="/answer"/>" method="post">
                        <input type="hidden" value="${param.page}" name="page">

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
                                <textarea class="form-control <c:if test="${not empty error_message}">is-invalid</c:if>"
                                          rows="12"
                                          name="text"
                                          id="text"
                                          aria-label="With textarea"
                                          required>${answer_text}</textarea>
                                <c:choose>
                                    <c:when test="${not empty requestScope.error_message}">
                                        <div class="invalid-feedback">
                                            <fmt:message key="${error_message}" bundle="${ rb }"/>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="invalid-feedback">
                                            <fmt:message key="answer.validation.error" bundle="${ rb }"/>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <button type="submit" class="btn btn-secondary btn-sm mt-2"><fmt:message key="submit" bundle="${ rb }"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:if>
<jsp:include page="parts/footer.jsp"/>
<script src="<c:url value="/js/validators.js" />"></script>
<script src="<c:url value="/js/answerValidation.js"/>"></script>
</body>
</html>