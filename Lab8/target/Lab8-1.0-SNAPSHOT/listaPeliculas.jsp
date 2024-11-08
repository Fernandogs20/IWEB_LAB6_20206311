<%@ page import="com.example.lab8.Beans.Pelicula" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Películas</title>
</head>
<body>
<h2>Lista de Películas</h2>

<!-- Formulario de búsqueda -->
<form action="peliculas" method="get">
    <input type="hidden" name="action" value="buscar">
    <label for="nombre">Buscar por nombre:</label>
    <input type="text" id="nombre" name="nombre" placeholder="Ingrese el nombre de la película">
    <button type="submit">Buscar</button>
</form>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Director</th>
        <th>Año de Publicación</th>
        <th>Rating</th>
        <th>Box Office</th>
        <th>Género</th>
        <th>Actores</th>
        <th>Accionable</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Pelicula> peliculas = (List<Pelicula>) request.getAttribute("peliculas");
        if (peliculas != null && !peliculas.isEmpty()) {
            for (Pelicula pelicula : peliculas) {
    %>
    <tr>
        <td><%= pelicula.getIdPelicula() %></td>
        <td><a href="viewPelicula.jsp?id=<%= pelicula.getIdPelicula() %>"><%= pelicula.getTitulo() %></a></td>
        <td><%= pelicula.getDirector() %></td>
        <td><%= pelicula.getAnoPublicacion() %></td>
        <td><%= pelicula.getRating() %></td>
        <td><%= pelicula.getBoxOffice() %></td>
        <td><%= pelicula.getGenero().getNombre() %></td>
        <td><a href="viewActores.jsp?id=<%= pelicula.getIdPelicula() %>">Ver actores</a></td>
        <td><a href="listaPeliculas.jsp" onclick="confirmarEliminacion(<%= pelicula.getIdPelicula() %>)">Eliminar</a></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="9">No se encontraron películas.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<script type="text/javascript">
    function confirmarEliminacion(idPelicula) {
        if (confirm("¿Estás seguro de que deseas eliminar esta película?")) {
            window.location.href = "peliculas?action=eliminar&id=" + idPelicula;
        }
    }
</script>
</body>
</html>
