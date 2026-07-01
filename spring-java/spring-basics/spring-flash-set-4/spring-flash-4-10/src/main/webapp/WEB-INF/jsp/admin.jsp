<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Title : ${title}</h1>
<h1>Message : ${message}</h1>

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
<a href="/welcome">Back!</a>

<iframe width="560" height="315" src="https://www.youtube.com/embed/7kfe2tdb0go" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen></iframe>

</body>
</html>