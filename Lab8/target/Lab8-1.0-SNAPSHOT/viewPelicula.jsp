<%@ page import="com.example.lab8.Beans.Pelicula" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Película <%= request.getParameter("id") %></title>
</head>
<body>
<%
    Pelicula pelicula = (Pelicula) request.getAttribute("pelicula");
%>
<h2>Película <%= pelicula.getIdPelicula() %></h2>
<form action="detalles" method="post">
    <input type="hidden" name="idPelicula" value="<%= pelicula.getIdPelicula() %>">

    <label for="titulo">Título:</label>
    <input type="text" id="titulo" name="titulo" value="<%= pelicula.getTitulo() %>"><br><br>

    <label for="director">Director:</label>
    <input type="text" id="director" name="director" value="<%= pelicula.getDirector() %>"><br><br>

    <label for="anoPublicacion">Año Publicación:</label>
    <input type="number" id="anoPublicacion" name="anoPublicacion" value="<%= pelicula.getAnoPublicacion() %>"><br><br>

    <label for="rating">Rating:</label>
    <input type="number" id="rating" name="rating" step="0.1" value="<%= pelicula.getRating() %>"><br><br>

    <label for="boxOffice">Box Office:</label>
    <input type="number" id="boxOffice" name="boxOffice" step="0.01" value="<%= pelicula.getBoxOffice() %>"><br><br>

    <label>Actores:</label>
    <a href="viewActores.jsp?id=<%= pelicula.getIdPelicula() %>">Ver actores</a><br><br>

    <button type="submit">Guardar</button>
</form>
</body>
</html>
