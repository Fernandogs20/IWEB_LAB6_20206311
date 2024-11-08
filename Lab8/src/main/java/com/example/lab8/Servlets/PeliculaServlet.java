package com.example.lab8.Servlets;

import com.example.lab8.Beans.Pelicula;
import com.example.lab8.Daos.PeliculaDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PeliculaServlet", urlPatterns = {"/peliculas"})
public class PeliculaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        PeliculaDao peliculaDao = new PeliculaDao();

        switch (action) {
            case "lista":
                List<Pelicula> peliculas = peliculaDao.obtenerTodasLasPeliculas();
                request.setAttribute("peliculas", peliculas);
                request.getRequestDispatcher("listaPeliculas.jsp").forward(request, response);
                break;

            case "eliminar":
                String idParam = request.getParameter("id");
                if (idParam != null) {
                    try {
                        int idPelicula = Integer.parseInt(idParam);
                        peliculaDao.eliminarPelicula(idPelicula);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de película no válido");
                        return;
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de película no proporcionado");
                    return;
                }
                response.sendRedirect("peliculas");
                break;

            case "buscar":
                String nombre = request.getParameter("nombre");
                List<Pelicula> peliculasFiltradas = peliculaDao.buscarPeliculasPorNombre(nombre);
                request.setAttribute("peliculas", peliculasFiltradas);
                request.getRequestDispatcher("listaPeliculas.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("peliculas?action=lista");
                break;
        }
    }
}
