<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <style>
      table {
        background-color: blueviolet;
        text-align: center;
        width: 100%;
      }

      form {
        text-align: center;
        width: 100%;
      }

      p {
        text-align: center;
        width: 100%;
      }

      h1 {
        text-align: center;
        width: 100%;
        background-color: greenyellow;
      }

      .xFiles {
        background-color: black;
        color: greenyellow;
      }
    </style>
    <title>Evaluating current user login info</title>
</head>
<body>
<h1>Evaluating current user login info</h1>
<table>
    <thead>
    <tr>
        <th>Security Parameter</th>
        <th>Value</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Username</td>
        <td>${name}</td>
    </tr>
    <tr>
        <td>Roles</td>
        <td>${roles}</td>
    </tr>
    </tbody>
</table>

<sec:authorize access="hasRole('ADMIN')">
    <p class="xFiles">The Truth is out There</p>
</sec:authorize>
<p class="xFiles"><sec:authentication property="principal"/></p>

<form method="post" action="/logout">
    <sec:csrfInput/>
    <input type="submit" value="Logout">
</form>

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

</body>
</html>