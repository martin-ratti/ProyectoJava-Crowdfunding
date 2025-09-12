<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Donaciones</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/user/styles/my-donations.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
</head>
<body>
    <jsp:include page="/views/fragments/header.jspf"/>

    <main class="donations-container">
        <h1>Mis Donaciones</h1>

        <c:choose>
            <c:when test="${not empty misDonaciones}">
                <div class="donations-grid">
                    <c:forEach var="donacion" items="${misDonaciones}">
                        <div class="donation-card">
                            <h3 class="project-title">${donacion.nombreProyecto}</h3>
                            
                            <p class="donation-amount">
                                <fmt:setLocale value="es_AR" />
                                <fmt:formatNumber value="${donacion.monto}" type="currency" currencySymbol="$ " />
                            </p>

                            <c:if test="${not empty donacion.comentario}">
                                <p class="donation-comment">"${donacion.comentario}"</p>
                            </c:if>

                            <div class="donation-details">
                                <span>ID Donación: #${donacion.idDonacion}</span>
                                <span>${donacion.fecha}</span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div class="no-donations">
                    <svg class="no-donations-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                        <path fill-rule="evenodd" d="M8.25 3.75A2.25 2.25 0 0110.5 6v1.5H6A2.25 2.25 0 003.75 9.75v1.5H18v-1.5A2.25 2.25 0 0015.75 7.5h-4.5V6A2.25 2.25 0 0113.5 3.75h-5.25zM6 12.75V18A2.25 2.25 0 008.25 20.25h7.5A2.25 2.25 0 0018 18v-5.25H6z" clip-rule="evenodd" />
                    </svg>
                    <p>Aún no has realizado ninguna donación.</p>
                    <a href="${pageContext.request.contextPath}/activeProjects" class="glow-btn">Explorar Proyectos</a>
                </div>
            </c:otherwise>
        </c:choose>
    </main>

    <jsp:include page="/views/fragments/footer.jspf"/>
</body>
</html>
