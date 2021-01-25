<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.text" var="rb"/>

<footer class="footer mt-auto py-3 bg-light text-center text-lg-start" style="height: auto;">

    <div class="container p-4">

        <div class="row">

            <div class="col-lg-6 col-md-12 mb-4 mb-md-0">
                <h5 class="text-uppercase">Ask&Go</h5>

                <p align="left">
                    <fmt:message key="footer.info"
                                 bundle="${ rb }"/>
                </p>
            </div>

            <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                <h5 class="text-uppercase"><fmt:message key="footer.links"
                                                        bundle="${ rb }"/></h5>

                <ul class="list-unstyled mb-0">
                    <li>
                        <a href="https://www.linkedin.com/in/kirylpraded/" class="text-dark">LinkedIn</a>
                    </li>
                    <li>
                        <a href="https://twitter.com/Kiryl54482617" class="text-dark">Twitter</a>
                    </li>
                    <li>
                        <a href="https://www.reddit.com/user/Specific-Maximum4283" class="text-dark">Reddit</a>
                    </li>
                    <li>
                        <a href="https://github.com/kirillpraded" class="text-dark">GitHub</a>
                    </li>
                </ul>
            </div>

            <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                <h5 class="text-uppercase mb-0"><fmt:message key="footer.emails"
                                                             bundle="${ rb }"/></h5>
                <ul class="list-unstyled">
                    <li>
                        <a href="mailto:kirillpraded@bk.ru" class="text-dark"><fmt:message key="footer.developer"
                                                                                           bundle="${ rb }"/></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">&#169;2020 Copyright:
        <a class="text-dark" href="https://vk.com/ind_k">Kiryl Pradzed</a>
    </div>
</footer>
