<%@ page import="org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<body>
<h1>Using JSP's in SpringBoot</h1>
<h2>The following message came to our attention:</h2>
<p><%="Nice one!"%>
</p>
<p>${notification}</p>
<h2>Index</h2>
<p><%=ConsolerizerGraphs.getUnicorns(10)%>
</p>

<iframe width="560" height="315" src="https://www.youtube.com/embed/aAkMkVFwAoo" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen></iframe>
</body>
</html>

