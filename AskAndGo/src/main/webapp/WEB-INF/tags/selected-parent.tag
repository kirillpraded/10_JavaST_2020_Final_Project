<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<%@ attribute name="cat" required="true" rtexprvalue="true" type="by.praded.ask_and_go.entity.Category" %>

<%@ attribute name="space" required="true" rtexprvalue="true" type="java.lang.String" %>

<option value="${cat.id}" <c:if test="${category.parent.id eq cat.id}">selected</c:if>><jsp:text>${space}</jsp:text>${category.name}</option>


<c:forEach var="categ" items="${cat.subcategories}">

    <tags:selected-parent cat="${categ}" space="${space}&nbsp;&nbsp;&nbsp;&nbsp;"/>

</c:forEach>
