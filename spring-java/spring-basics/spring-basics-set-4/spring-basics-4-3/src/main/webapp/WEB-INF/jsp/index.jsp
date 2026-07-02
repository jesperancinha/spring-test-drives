<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<div class="container">
    <h1>The Secrets We Keep</h1>
    <p>
        You are logged in as <b><c:out value="${pageContext.request.remoteUser}"/></b>
    </p>
    <sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
        <p class="xFiles">Maja Reid seeks the truth</p>
    </sec:authorize>
    <sec:authorize access="hasRole('ADMIN')">
        <p class="xFiles">Thomas Steinmann has a secret</p>
    </sec:authorize>
    <c:url var="logoutUrl" value="/logout"/>
    <form class="form-inline" action="${logoutUrl}" method="post">
        <input type="submit" value="Log out"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <iframe width="560" height="315" src="https://www.youtube.com/embed/8Jv6f59Z4Y8" frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen></iframe>
</div>
</body>
</html>