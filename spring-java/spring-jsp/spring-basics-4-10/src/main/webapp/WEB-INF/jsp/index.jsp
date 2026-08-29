<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Title : ${title}</h1>
<h1>Message : ${message}</h1>

<sec:authorize access="hasRole('ROLE_USER')">
    <form action="/logout" method="post">
        <p>
            <label for="csrfTokenId">CSRF Token Id</label>
            <input type="text"
                   id="csrfTokenId"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </p>
        <p>
            <input type="submit" value="Logout">
        </p>
    </form>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/admin">You are an admin and so you can go to the admin page!</a>
</sec:authorize>

<iframe width="560" height="315" src="https://www.youtube.com/embed/w8mBplMtwJ8" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen></iframe>

</body>
</html>