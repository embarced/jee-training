<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet?name=Falk">Hello Servlet</a>
<br/>
<a href="registration?eventId=2022-09-22">Registrierungsformular</a>
<br/>
${param.success ? "Registrierung erfolgreich" : "" }
<br/>
<a href="admin">Admin-Seite</a>
</body>
</html>