<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/fragments/header.jspf" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cómo Funciona - ImpulsaMe</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    
    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/how-it-works.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
	
</head>
<body>
    <main class="howitworks-container">
        <h1 class="howitworks-title title-underline">Cómo funciona ImpulsaMe</h1>

        <div class="howitworks-grid">

            <div class="howitworks-card">
                <h2>1. Crea tu proyecto</h2>
                <p>Regístrate y comparte tu idea con el mundo. Describe tu objetivo, fija una meta de financiamiento, agrega imágenes o videos y cuenta por qué tu proyecto merece ser apoyado.</p>
            </div>

            <div class="howitworks-card">
                <h2>2. Comparte con tu comunidad</h2>
                <p>Difunde tu proyecto entre familiares, amigos, redes sociales y comunidades interesadas. Cuanto más lo compartas, más posibilidades tendrás de alcanzar tu meta.</p>
            </div>

            <div class="howitworks-card">
                <h2>3. Recibe donaciones</h2>
                <p>Las personas interesadas pueden aportar a tu proyecto de manera sencilla y segura. Cada contribución suma para alcanzar tu objetivo y hacer tu idea realidad.</p>
            </div>

            <div class="howitworks-card">
                <h2>4. Da seguimiento</h2>
                <p>Mantén informados a tus donantes compartiendo avances, logros e hitos importantes de tu proyecto. La transparencia genera confianza y motiva más apoyo.</p>
            </div>

            <div class="howitworks-card">
                <h2>5. Logra tu meta</h2>
                <p>Una vez alcanzado el monto fijado, podrás utilizar los fondos para llevar a cabo tu proyecto y generar el impacto positivo que te propusiste desde el inicio.</p>
            </div>

            <div class="howitworks-card">
                <h2>6. Agradece y crece</h2>
                <p>Agradece a quienes te apoyaron, comparte resultados y considera lanzar nuevas iniciativas. Cada proyecto exitoso impulsa más oportunidades en la comunidad.</p>
            </div>
        </div>
    </main>

    <%@ include file="/views/fragments/footer.jspf" %>
</body>
</html>
