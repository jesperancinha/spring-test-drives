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
    </style>
</head>
<body>
<h1>Your Jules! Please refer to Readme.md for instructions on how to test this application</h1>
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