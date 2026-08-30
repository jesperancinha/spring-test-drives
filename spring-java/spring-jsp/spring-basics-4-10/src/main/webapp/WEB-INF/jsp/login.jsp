<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<h1>Ava Max - My Head and My Heart</h1>

<div id="login-box">

    <h2>Login Page</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form name='loginForm'
          action="<c:url value='/auth/login_check?targetUrl=${targetUrl}' />"
          method='POST'>

        <table>
            <tr>
                <td>Username:</td>
                <td><input type='text' name='username'></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password'/></td>
            </tr>
        </table>
        <input name="submit" type="submit"
               value="submit"/>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

    </form>
</div>

</body>
</html>