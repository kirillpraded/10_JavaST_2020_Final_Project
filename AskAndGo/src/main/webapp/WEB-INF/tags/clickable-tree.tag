<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<%@ attribute name="category" required="true" rtexprvalue="true" type="by.praded.ask_and_go.entity.Category" %>

<%@ attribute name="space" required="true" rtexprvalue="true" type="java.lang.Integer" %>

<div class="ml-${space}">
    <c:choose>
        <c:when test="${category.subcategories.size() eq 0}">
            <a href="<c:url value="/questions?category_id=${category.id}"/>">
                <c:out value="${category.name}"/>
            </a>
        </c:when>
        <c:otherwise>

            &#8226;<c:out value="${category.name}"/>
        </c:otherwise>
    </c:choose>


</div>
<c:forEach var="cat" items="${category.subcategories}">

    <tags:clickable-tree category="${cat}" space="${space+2}"/>

</c:forEach>
