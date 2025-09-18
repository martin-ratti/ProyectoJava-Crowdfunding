<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Avances del Proyecto - ${proyecto.nombreProyecto}</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/project/styles/advances.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
</head>
<body>

    <jsp:include page="/views/fragments/header.jspf"/>

    <main class="advances-container">
        <div class="advances-header">
            <h1>Avances de: ${proyecto.nombreProyecto}</h1>
        </div>

        <c:choose>
            <c:when test="${not empty avances}">
                <section class="latest-advance">
                    <h2>Último Avance</h2>
                    <div class="advance-card highlighted">
                        <img src="${pageContext.request.contextPath}/uploads/${ultimoAvance.foto}" alt="Foto del último avance">
                        <div class="advance-info">
                            <p class="advance-date">${ultimoAvance.fecha}</p>
                            <p>${ultimoAvance.descripcion}</p>
                        </div>
                    </div>
                </section>

                <c:if test="${not empty historialAvances}">
                    <section class="all-advances">
                        <h2>Historial de Avances</h2>
                        <div class="carousel">
                            <c:forEach var="avance" items="${historialAvances}">
                                <div class="advance-card">
                                    <img src="${pageContext.request.contextPath}/uploads/${avance.foto}" alt="Foto del avance">
                                    <div class="advance-info">
                                        <p class="advance-date">${avance.fecha}</p>
                                        <p>${avance.descripcion}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </section>
                </c:if>
            </c:when>
            <c:otherwise>
                <div class="no-content-message">
                    <p>Este proyecto aún no tiene avances publicados.</p>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="advances-actions">
            <c:if test="${not empty sessionScope.usuario and sessionScope.usuario.telefono != null and sessionScope.usuario.idUsuario != proyecto.idCreador}">
                <a href="${pageContext.request.contextPath}/views/user/donation.jsp?idProyecto=${proyecto.idProyecto}" class="glow-btn-inverse">Apoyar este Proyecto</a>
            </c:if>
            <a href="${pageContext.request.contextPath}/projectDetails?id=${proyecto.idProyecto}" class="glow-btn">Volver al Proyecto</a>
        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf"/>

</body>
</html>

