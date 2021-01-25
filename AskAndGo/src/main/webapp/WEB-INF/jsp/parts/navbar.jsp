<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/main"/>">Ask&Go</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/categories"/>"><fmt:message key="navbar.categories"
                                                                                         bundle="${ rb }"/></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="https://vk.com/ind_k"><fmt:message key="navbar.contact"
                                                                                 bundle="${ rb }"/></a>
                </li>
                <c:if test="${not empty sessionScope.auth_user}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/ask"/>"><fmt:message key="question.ask"
                                                                                      bundle="${ rb }"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.auth_user.role.name() eq 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/admin"/>"><fmt:message key="navbar.admin"
                                                                                        bundle="${ rb }"/></a>
                    </li>
                </c:if>
            </ul>


            <div class="dropdown mr-2">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    &#127760;
                </button>

                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <form action="<c:url value="/lang" />" method="post" accept-charset="UTF-8">
                        <li>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="lang" id="flexRadioDefault1"
                                       value="eng"
                                       <c:if test="${sessionScope.locale eq 'en_US'}">checked</c:if>>
                                <label class="form-check-label" for="flexRadioDefault1">
                                    English
                                </label>
                            </div>
                        </li>
                        <li>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="lang" id="flexRadioDefault2"
                                       value="rus" <c:if test="${sessionScope.locale eq 'ru_RU'}">checked</c:if>>
                                <label class="form-check-label" for="flexRadioDefault2">
                                    Русский
                                </label>
                            </div>
                        </li>
                        <li>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="lang" id="flexRadioDefault3"
                                       value="bel" <c:if test="${sessionScope.locale eq 'by_BY'}">checked</c:if>>
                                <label class="form-check-label" for="flexRadioDefault3">
                                    Беларуская
                                </label>
                            </div>
                        </li>
                        <button type="submit" class="btn btn-primary"><fmt:message key="submit"
                                                                                   bundle="${ rb }"/></button>
                    </form>
                </div>
            </div>

            <form class="form-inline" accept-charset="UTF-8" action="<c:url value="/search" />">
                <input class="form-control mr-sm-2" type="text" name="q" placeholder="<fmt:message key="navbar.search"
                                                                                            bundle="${ rb }"/>"
                       aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="navbar.search"
                                                                                                bundle="${ rb }"/></button>

            </form>
            <div class="navbar-text mr-3 ml-3">
                <c:choose>
                    <c:when test="${not empty sessionScope.auth_user}">
                        <a href="<c:url value="/user?user_id=${sessionScope.auth_user.id}"/>">${sessionScope.auth_user.username}</a>
                        <a href="<c:url value="/logout"/>"><fmt:message key="navbar.logout" bundle="${ rb }"/></a>
                    </c:when>
                    <c:otherwise>
                        <div><a href="<c:url value="/login"/>"><fmt:message key="navbar.login" bundle="${ rb }"/></a>
                        </div>

                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </div>
</nav>

