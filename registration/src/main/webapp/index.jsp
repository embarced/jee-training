<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Jakarta EE Workshop</title>
</head>
<body>
<h1><%= "Jakarta EE Workshop" %>
</h1>
<br/>
<a href="hello-servlet?name=Falk">Hello Servlet</a>
<br/>
<a href="registration">Registrierungsformular</a>
<br/>
${param.success ? "<span style=\"color:green;\">Registrierung erfolgreich</span>" : "" }
<br/>
<a href="registration?eventId=2022-09-22&simulateCrud=true">Simuliere CRUD-Varianten</a>
<br/>
<a href="admin">Admin-Seite</a>
</body>
</html>