<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acerca de Nosotros</title>

    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/about.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">

</head>

<body>

	<jsp:include page="/views/fragments/header.jspf" />
    <main class="flex-grow p-8">
        <div class="container main-content-container">
            <div class="content-layout">
                <div class="text-section">
                    <h1 class="main-title">Acerca de Nosotros</h1>
                    <p class="main-subtitle">
                        En ImpulsaMe, somos más que una empresa de tecnología; somos un equipo de soñadores prácticos. Nuestra misión ha siempre sido crear soluciones web innovadoras que simplifiquen la vida y ayuden a proyectos a crecer. Hoy, estamos orgullosos de materializar esta visión con una plataforma de crowdfunding diseñada para impulsar iniciativas de toda índole.
                        <br><br>
                        Creemos que las ideas más transformadoras surgen de la pasión, pero se concretan con el apoyo adecuado. Por eso, hemos construido un espacio donde la creatividad y la experiencia técnica se unen para brindar herramientas digitales poderosas, seguras y fáciles de usar, asegurando que cada proyecto, sin importar su tamaño o ámbito, tenga la oportunidad de brillar.
                        <br><br>
                        <u>Nuestros Pilares:</u><br><br>
                        <b>Transparencia:</b> Operamos con integridad y claridad en todo lo que hacemos. Para nosotros, la confianza es fundamental, por lo que garantizamos procesos honestos y una comunicación abierta entre creadores y patrocinadores.
                        <br><br>
                        <b>Calidad:</b> Somos profesionales apasionados por la excelencia. Desde la conceptualización hasta el lanzamiento, nos dedicamos a ofrecer una plataforma de alta calidad y una experiencia de usuario excepcional que supere las expectativas.
                        <br><br>
                        <b>Colaboración:</b> Trabajamos codo a codo con nuestra comunidad. No solo proporcionamos una herramienta, sino que fomentamos una red de apoyo donde la orientación y la colaboración son clave para el éxito de cada campaña.
                        <br><br>
                        El equipo detrás de ImpulsaMe está compuesto por personas dedicadas y entusiastas, unidas por un objetivo común: ayudar a que las ideas crezcan.
                        <br><br>
                    </p>
                        <div class="image-section">
                    		<img src="${pageContext.request.contextPath}/assets/about.jpg" alt="Imagen de nuestro equipo" class="about-image">
                		</div>
                    <p class="main-subtitle">
						<br><br>
                        <b>Estamos aquí para ser el puente que conecta tu visión con una comunidad global dispuesta a apoyarla.</b>
                        <br><br>
                        <b>Gracias por ser parte de nuestra historia.</b>
                    </p>
                </div>
            </div>
        </div>
    </main>

	<jsp:include page="/views/fragments/footer.jspf" />

</body>
</html>
