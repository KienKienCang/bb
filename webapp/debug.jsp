<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>

<%
    List<String> debugLog = (List<String>) request.getAttribute("debugLog");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Debug Log</title>
</head>
<body>
    <h2>Debug Log</h2>
    <ul>
        <% if (debugLog != null) {
            for (String log : debugLog) { %>
                <li><%= log %></li>
            <% }
        } else { %>
            <li>Không có dữ liệu debug.</li>
        <% } %>
    </ul>
</body>
</html>
