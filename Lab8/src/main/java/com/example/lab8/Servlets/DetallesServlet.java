package com.example.lab8.Servlets;

import com.example.lab8.Beans.Pelicula;
import com.example.lab8.Daos.PeliculaDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetallesServlet", urlPatterns = {"/detalles"})
public class DetallesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int idPelicula = Integer.parseInt(idParam);
                PeliculaDao peliculaDao = new PeliculaDao();
                Pelicula pelicula = peliculaDao.obtenerPeliculaPorId(idPelicula);

                if (pelicula != null) {
                    request.setAttribute("pelicula", pelicula);
                    request.getRequestDispatcher("viewPelicula.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Película no encontrada");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de película no válido");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de película no proporcionado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
            String titulo = request.getParameter("titulo");
            String director = request.getParameter("director");
            int anoPublicacion = Integer.parseInt(request.getParameter("anoPublicacion"));
            double rating = Double.parseDouble(request.getParameter("rating"));
            double boxOffice = Double.parseDouble(request.getParameter("boxOffice"));

            PeliculaDao peliculaDao = new PeliculaDao();
            Pelicula pelicula = new Pelicula();
            pelicula.setIdPelicula(idPelicula);
            pelicula.setTitulo(titulo);
            pelicula.setDirector(director);
            pelicula.setAnoPublicacion(anoPublicacion);
            pelicula.setRating(rating);
            pelicula.setBoxOffice(boxOffice);

            peliculaDao.actualizarPelicula(pelicula);

            // Redirigir a la lista principal después de guardar
            response.sendRedirect("peliculas");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos de película no válidos");
        }
    }
}
