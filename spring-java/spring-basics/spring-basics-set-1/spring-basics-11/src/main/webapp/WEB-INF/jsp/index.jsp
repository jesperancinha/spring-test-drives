<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<body>
<h1>Explore Form parameters</h1>

<%--@elvariable id="music" type="org.jesperancinha.sftd.flash11.form.Music"--%>
<%--<form:form method = "POST" action="/req" commandName="music">--%>
<form:form method="POST" action="/req" modelAttribute="music">
    <form:label path="artist">Artist:</form:label>
    <form:input type="text" path="artist"/>
    <form:label path="song">Song:</form:label>
    <form:input type="text" path="song"/>
    <%--    <p><label for="artistId">Artist:</label><input id="artistId" name="artist" type="text" value="Tracy Chapman"></p>--%>
    <%--    <p><label for="songId">Song:</label><input id="songId" name="song" type="text" value="Talkin' About A Revolution"></p>--%>
    <p><input type="submit" value="Submit"/></p>
</form:form>
<iframe width="560" height="315" src="https://www.youtube.com/embed/Xv8FBjo1Y8I" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen></iframe>
</body>
</html>

