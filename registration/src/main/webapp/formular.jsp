<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrierungsformular</title>
</head>
<body>
<h1>Registrierungsformular</h1>
${events}
<form action="registration" method="post">
    Event: <select name="eventId">
        <c:forEach items="${events}" var="event">
            <option value='${event.date}'>${event.id}</option>
        </c:forEach>
    </select>
    <br>
    Name: <input type="text" name="name">
    <br>
    E-Mail: <input type="text" name="email">
    <br>
    <input type="submit" value="Anmelden">
    <a href="${pageContext.request.contextPath}">Zurück</a>

</form>
</body>
</html>