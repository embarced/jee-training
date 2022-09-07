<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrierungsformular</title>
</head>
<body>
<h1>Registrierungsformular</h1>
<form action="registration" method="post">
    Name: <input type="text" name="name">
    <br>
    E-Mail: <input type="text" name="email">
    <input type="hidden" name="eventId" value="${param.eventId}">
    <br>
    <input type="submit" value="Anmelden">
    <a href="${pageContext.request.contextPath}">Zurück</a>

</form>
</body>
</html>