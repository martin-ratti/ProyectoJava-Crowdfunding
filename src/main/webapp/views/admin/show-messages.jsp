<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="/views/fragments/header.jspf" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mensajes recibidos - ImpulsaMe</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/admin/styles/show-messages.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
</head>
<body>
    <main class="messages-container">
        <h1 class="title-underline">Mensajes recibidos</h1>

        <c:choose>
            <c:when test="${empty mensajes}">
             	<div class="no-messages-container">
                	<p class="no-messages">No hay mensajes por el momento.</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="messages-grid">
                    <c:forEach var="m" items="${mensajes}">
                        <div class="message-card">
                            <div class="message-content">
                                <div class="message-header">
                                    <h3>${m.asunto}</h3>
                                </div>
                                <p><strong>De:</strong> ${m.nombre} (${m.email})</p>
                                <span class="message-date">${m.fechaFormateada}</span>
                            </div>
                            <div class="message-actions">
                                <form method="post" action="${pageContext.request.contextPath}/showMessages">
                                    <input type="hidden" name="id" value="${m.idContacto}">
                                    <button type="submit" class="little-glow-btn">Revisar</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </main>

    <%@ include file="/views/fragments/footer.jspf" %>
</body>
</html>
