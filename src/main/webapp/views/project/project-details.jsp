<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle del Proyecto</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/project/styles/project-details.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
</head>
<body>
    <%@ include file="/views/fragments/header.jspf" %>
    <main class="projects-container">
        <div class="project-card">
            <h1>${proyecto.nombreProyecto}</h1>
            <img src="${pageContext.request.contextPath}/uploads/${proyecto.foto}" alt="Imagen del Proyecto">
            <p><strong>Descripción:</strong> ${proyecto.descripcion}</p>
            <p><strong>Monto Meta:</strong> $${proyecto.montoMeta}</p>
            <p><strong>Monto Recaudado:</strong> $${proyecto.montoRecaudado}</p>
            
            <c:if test="${proyecto.montoMeta > 0}">
                <c:set var="porcentaje" value="${(proyecto.montoRecaudado * 100) / proyecto.montoMeta}" />
                <div class="progress-bar-container">
                    <div class="progress-bar-fill" style="width: ${porcentaje > 100 ? 100 : porcentaje}%; ">
                        <span><fmt:formatNumber value="${porcentaje}" maxFractionDigits="2" />%</span>
                    </div>
                </div>
            </c:if>

            <p><strong>Fecha Inicio:</strong> ${proyecto.fechaIni}</p>
            <p><strong>Fecha Fin:</strong> ${proyecto.fechaFin}</p>
            <p><strong>Creador:</strong> ${proyecto.creador.nombre} ${proyecto.creador.apellido}</p>
            <p><strong>Estado:</strong> ${proyecto.estado}</p>
            <p><strong>Categoría:</strong> ${proyecto.categoria.nombreCategoria}</p>
            <p><strong>País:</strong> ${proyecto.pais.nombrePais}</p>

            <c:if test="${not empty sessionScope.usuario and empty sessionScope.usuario.telefono and proyecto.estado eq 'Pendiente'}">
                <div class="admin-actions">
                    <form action="${pageContext.request.contextPath}/approveProject" method="post" style="display:inline;">
                        <input type="hidden" name="idProyecto" value="${proyecto.idProyecto}">
                        <button class="little-glow-btn-inverse" type="submit">Aprobar</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/rejectProject" method="post" style="display:inline;">
                        <input type="hidden" name="idProyecto" value="${proyecto.idProyecto}">
                        <button class="little-glow-btn" type="submit">Rechazar</button>
                    </form>
                </div>
            </c:if>

            <div class="project-actions">
                <a href="${pageContext.request.contextPath}/projectAdvances?idProyecto=${proyecto.idProyecto}" class="glow-btn">
                    Ver Avances
                </a>
                <c:if test="${not empty sessionScope.usuario 
                              and sessionScope.usuario.telefono != null 
                              and sessionScope.usuario.idUsuario ne proyecto.idCreador}">
                    <a href="${pageContext.request.contextPath}/views/user/donation.jsp?idProyecto=${proyecto.idProyecto}" class="glow-btn-inverse">
                        Donar a este Proyecto
                    </a>
                </c:if>
                <c:if test="${empty sessionScope.usuario}">
                    <a href="${pageContext.request.contextPath}/login" class="glow-btn-inverse">Donar a este Proyecto</a>
                </c:if>
            </div>

            <section id="comments" class="comments-section">
                <h2>Comentarios</h2>

                <c:if test="${not empty sessionScope.usuario and sessionScope.usuario.idUsuario eq proyecto.idCreador}">
                    <p class="owner-note" style="text-align:center; font-style:italic; color:#444; margin-bottom:15px;">
                        Estos son los comentarios de tu proyecto
                    </p>
                </c:if>

                <c:if test="${not empty sessionScope.usuario and sessionScope.usuario.idUsuario ne proyecto.idCreador}">
                    <c:choose>
                        <c:when test="${sessionScope.usuario.telefono != null and haDonado}">
                            <div class="comment-form-container">
                                <h3>Deja tu comentario</h3>
                                <form action="${pageContext.request.contextPath}/comment" method="post">
                                    <input type="hidden" name="idProyecto" value="${proyecto.idProyecto}">
                                    <div class="form-group">
                                        <textarea name="descripcion" rows="4" placeholder="Escribe tu mensaje de apoyo..." required></textarea>
                                    </div>
                                    <button type="submit" class="glow-btn-inverse">Publicar Comentario</button>
                                </form>
                            </div>
                        </c:when>
                        <c:when test="${not haDonado and sessionScope.usuario.telefono != null }">
                            <p class="comment-login-prompt" style="text-align:center; color:#a00; font-style:italic;">
                                ⚠️ Solo quienes han donado a este proyecto pueden comentar.
                            </p>
                        </c:when>
                    </c:choose>
                </c:if>

                <div class="comments-list">
                    <c:if test="${empty comentarios}">
                        <p style="text-align:center;">Aún no hay comentarios. ¡Sé el primero en dejar uno!</p>
                    </c:if>

                    <c:forEach var="comentario" items="${comentarios}">
                        <div class="comentario">
                            <p><b>${comentario.nombreUsuario}</b> comentó:</p>
                            <p>${comentario.descripcion}</p>
                            <span class="fecha">${comentario.fechaFormateada}</span>

                            <c:if test="${not empty sessionScope.usuario and sessionScope.usuario.telefono == null}">
                                <form action="${pageContext.request.contextPath}/disableComment" method="post" style="display:inline;">
                                    <input type="hidden" name="idComentario" value="${comentario.idComentario}" />
                                    <input type="hidden" name="idProyecto" value="${comentario.idProyecto}" />
                                    <br><br>
                                    <button type="submit" class="little-glow-btn-danger">Deshabilitar</button>
                                </form>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </div>
    </main>
    <jsp:include page="/views/fragments/footer.jspf" />
</body>
</html>
