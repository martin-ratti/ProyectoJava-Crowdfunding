<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/fragments/header.jspf" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Categorías</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    
    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/categories.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
	
</head>
<body>
    <main class="categories-container">
        <h1 class="categories-title">Explora nuestras categorías</h1>

        <div class="categories-grid">
            <div class="category-card">
                <h2>Tecnología</h2>
                <p>Proyectos relacionados con innovación digital, desarrollo de software, aplicaciones móviles, inteligencia artificial y hardware que buscan transformar la vida cotidiana.</p>
            </div>
            <div class="category-card">
                <h2>Arte</h2>
                <p>Iniciativas artísticas como pintura, escultura, danza, teatro y fotografía que promueven la creatividad y la expresión cultural en diferentes formas.</p>
            </div>
            <div class="category-card">
                <h2>Música</h2>
                <p>Campañas de bandas, solistas o productores musicales para lanzar discos, organizar conciertos o financiar giras y festivales.</p>
            </div>

            <div class="category-card">
                <h2>Cine</h2>
                <p>Producción de cortometrajes, largometrajes, documentales o proyectos audiovisuales independientes que buscan difusión y apoyo comunitario.</p>
            </div>
            <div class="category-card">
                <h2>Diseño</h2>
                <p>Proyectos de diseño gráfico, ilustración, diseño industrial, UX/UI y otros enfoques creativos para mejorar productos y experiencias visuales.</p>
            </div>
            <div class="category-card">
                <h2>Moda</h2>
                <p>Colecciones de indumentaria, calzado y accesorios, incluyendo propuestas sostenibles y proyectos de diseñadores emergentes.</p>
            </div>

            <div class="category-card">
                <h2>Comida</h2>
                <p>Emprendimientos gastronómicos como restaurantes, food trucks, bebidas artesanales y nuevas propuestas culinarias innovadoras.</p>
            </div>
            <div class="category-card">
                <h2>Publicación</h2>
                <p>Proyectos editoriales como libros, cómics, revistas digitales o blogs que promueven la lectura, la escritura y la difusión del conocimiento.</p>
            </div>
            <div class="category-card">
                <h2>Juegos</h2>
                <p>Desarrollo de videojuegos, juegos de mesa, cartas y experiencias lúdicas que fomentan la diversión, la interacción social y la creatividad.</p>
            </div>

            <div class="category-card">
                <h2>Educación</h2>
                <p>Plataformas de e-learning, cursos, talleres, academias o proyectos que buscan mejorar el acceso a la educación y el aprendizaje continuo.</p>
            </div>
            <div class="category-card">
                <h2>Salud</h2>
                <p>Iniciativas enfocadas en bienestar, prevención, acceso a medicamentos, nuevas terapias o infraestructura médica accesible para todos.</p>
            </div>
            <div class="category-card">
                <h2>Deportes</h2>
                <p>Proyectos de clubes, academias, atletas y eventos deportivos que promueven la actividad física, la competencia y la vida saludable.</p>
            </div>

            <div class="category-card">
                <h2>Medio Ambiente</h2>
                <p>Propuestas relacionadas con reciclaje, energías renovables, protección de la naturaleza y proyectos para un desarrollo sostenible.</p>
            </div>
            <div class="category-card">
                <h2>Animales</h2>
                <p>Campañas de protección animal, refugios, adopciones, rescates y concientización sobre el cuidado y respeto hacia los animales.</p>
            </div>
            <div class="category-card">
                <h2>Comunidad</h2>
                <p>Proyectos sociales, culturales y comunitarios que fomentan la inclusión, la solidaridad y el desarrollo de entornos más equitativos.</p>
            </div>
        </div>
    </main>

    <%@ include file="/views/fragments/footer.jspf" %>
</body>
</html>
