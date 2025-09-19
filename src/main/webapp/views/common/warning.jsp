<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ha Ocurrido un Error</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/warning.css">
    
</head>
<body>
    <jsp:include page="/views/fragments/header.jspf"/>

    <main class="warning-container">
        <div class="warning-card">
            <div class="warning-icon">
                <div class="icon-circle">
                    <span class="icon-symbol">!</span>
                </div>
            </div>
            <h1>¡Ups! Algo salió mal</h1>
            <p>
                Lo sentimos, pero encontramos un problema técnico al procesar tu solicitud. 
                <c:if test="${not empty errorMessage}">
                    <br><em>Detalle: ${errorMessage}</em>
                </c:if>
            </p>
            <a href="${pageContext.request.contextPath}/home" class="glow-btn">Volver al Inicio</a>
        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf"/>
</body>
</html>

