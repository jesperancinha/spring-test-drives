<html lang="en">
<head>
</head>
<body>
<h1>Welcome Neo - This is what you don't believe!</h1>
<iframe width="560" height="315" src="https://www.youtube.com/embed/pmcrG7ZZKUc" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen></iframe>

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
    <p><a href="/static/index.html">Back</a></p>
</form>
</body>
</html>