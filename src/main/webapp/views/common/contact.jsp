<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="/views/fragments/header.jspf" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Contacto</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/contact.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
	
</head>
<body>
    <main class="contact-container">
        <h1 class="contact-title title-underline">Contáctanos</h1>
        <p class="contact-intro">Si tienes preguntas, comentarios o sugerencias, completa el siguiente formulario y nos pondremos en contacto contigo lo antes posible.</p>

        <c:if test="${not empty successMessage}">
            <p class="success-message">${successMessage}</p>
        </c:if>

        <form class="contact-form" method="post" action="${pageContext.request.contextPath}/contact">
            <div class="form-group">
                <label for="nombre">Nombre completo</label>
                <input type="text" id="nombre" name="nombre" placeholder="Tu nombre" required>
            </div>

            <div class="form-group">
                <label for="email">Correo electrónico</label>
                <input type="email" id="email" name="email" placeholder="tunombre@mail.com" required>
            </div>

            <div class="form-group">
                <label for="asunto">Asunto</label>
                <input type="text" id="asunto" name="asunto" placeholder="Motivo del mensaje" required>
            </div>

            <div class="form-group">
                <label for="mensaje">Mensaje</label>
                <textarea id="mensaje" name="mensaje" rows="6" placeholder="Escribe tu mensaje aquí..." required></textarea>
            </div>

            <button type="submit" class="btn-submit">Enviar Mensaje</button>
        </form>
    </main>

    <%@ include file="/views/fragments/footer.jspf" %>
</body>
</html>
