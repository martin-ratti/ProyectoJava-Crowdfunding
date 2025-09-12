<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Acceso Denegado</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/forbidden.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
    
</head>
<body>
    <jsp:include page="/views/fragments/header.jspf"/>

    <main class="forbidden-container">
        <div class="forbidden-card">
            <div class="forbidden-icon">
                <div class="icon-circle">
                    <span class="icon-symbol">!</span>
                </div>
            </div>
            <h1>Acceso Denegado</h1>
            <p>Lo sentimos, no tienes los permisos necesarios para acceder a esta página.</p>
            <a href="${pageContext.request.contextPath}/home" class="glow-btn">Volver al Inicio</a>
        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf"/>
</body>
</html>

