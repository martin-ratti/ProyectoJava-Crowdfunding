<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="/views/fragments/header.jspf" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle del Mensaje</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/admin/styles/message-detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
</head>
<body>
    <main class="detail-container">
        <div class="message-detail-card">
            <a href="${pageContext.request.contextPath}/showMessages" class="back-link">&larr; Volver a Mensajes</a>
            <h1>${mensaje.asunto}</h1>
            <div class="message-meta">
                <p><strong>De:</strong> ${mensaje.nombre} (${mensaje.email})</p>
                <p><strong>Fecha:</strong> ${mensaje.fechaFormateada}</p>
            </div>
            <div class="message-body">
                <p>${mensaje.mensaje}</p>
            </div>
            <div class="message-footer">
                <form method="post" action="${pageContext.request.contextPath}/messageDetail">
                    <input type="hidden" name="id" value="${mensaje.idContacto}">
                    <input type="hidden" name="action" value="visto">
                    <button type="submit" class="little-glow-btn">✔ Marcar como visto</button>
                </form>
            </div>
        </div>
    </main>
    <%@ include file="/views/fragments/footer.jspf" %>
</body>
</html>

