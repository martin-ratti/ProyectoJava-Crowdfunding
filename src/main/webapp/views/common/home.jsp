<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="modelo.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ImpulsaMe - Crowdfunding</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/messages.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/hero-text.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
	
</head>
<body>

    <jsp:include page="/views/fragments/header.jspf" />
    <%@ include file="/views/fragments/messages.jspf" %>

    <main>
        <section class="hero-section">
            <div class="hero-content-box">
                <h1>Financia Tus Sueños</h1>
                <p>Bienvenido a nuestra plataforma de crowdfunding. Aquí puedes encontrar proyectos innovadores para apoyar o puedes crear tu propio proyecto y buscar el financiamiento que necesitas para hacerlo realidad.</p>
                
                <section class="hero-text">
                    <h1>
                        I'M A 
                        <span>
                            <span>CREATOR</span>
                            <span>DONOR</span>
                            <span>ENTREPRENEUR</span>
                            <span>INNOVATOR</span>
                        </span>
                    </h1>
                </section>

                <div class="hero-buttons">
                    <a href="${pageContext.request.contextPath}/activeProjects" class="glow-btn">Explorar Proyectos</a>
                    <a href="${pageContext.request.contextPath}/login" class="glow-btn-inverse">Crear Proyecto</a>
                </div>
            </div>
        </section>
    </main>

    <jsp:include page="/views/fragments/footer.jspf" />

</body>
</html>

