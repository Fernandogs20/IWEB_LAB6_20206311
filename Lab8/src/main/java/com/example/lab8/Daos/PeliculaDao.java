package com.example.lab8.Daos;
import com.example.lab8.Beans.Pelicula;
import com.example.lab8.Beans.Genero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDao extends DatabaseConnection {

    public List<Pelicula> obtenerTodasLasPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.idGenero, g.nombre " +
                "FROM Pelicula p INNER JOIN Genero g ON p.idGenero = g.idGenero " +
                "ORDER BY p.rating DESC, p.boxOffice DESC";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                Genero genero = new Genero();

                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                pelicula.setRating(rs.getDouble("rating"));
                pelicula.setBoxOffice(rs.getDouble("boxOffice"));
                genero.setIdGenero(rs.getInt("idGenero"));
                genero.setNombre(rs.getString("nombre"));
                pelicula.setGenero(genero);

                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas;
    }

    // Método para eliminar una película por ID
    public void eliminarPelicula(int idPelicula) {
        String sql = "DELETE FROM Pelicula WHERE idPelicula = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPelicula);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pelicula> buscarPeliculasPorNombre(String nombre) {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.idGenero, g.nombre " +
                "FROM Pelicula p INNER JOIN Genero g ON p.idGenero = g.idGenero " +
                "WHERE p.titulo LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nombre + "%"); // Permite buscar coincidencias parciales
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                Genero genero = new Genero();

                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                pelicula.setRating(rs.getDouble("rating"));
                pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                genero.setIdGenero(rs.getInt("idGenero"));
                genero.setNombre(rs.getString("nombre"));
                pelicula.setGenero(genero);

                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas;
    }
    public Pelicula obtenerPeliculaPorId(int idPelicula) {
        Pelicula pelicula = null;
        String sql = "SELECT p.idPelicula, p.titulo, p.director, p.anoPublicacion, p.rating, p.boxOffice, g.idGenero, g.nombre " +
                "FROM Pelicula p INNER JOIN Genero g ON p.idGenero = g.idGenero " +
                "WHERE p.idPelicula = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPelicula);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                pelicula = new Pelicula();
                Genero genero = new Genero();

                pelicula.setIdPelicula(rs.getInt("idPelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setAnoPublicacion(rs.getInt("anoPublicacion"));
                pelicula.setRating(rs.getDouble("rating"));
                pelicula.setBoxOffice(rs.getDouble("boxOffice"));

                genero.setIdGenero(rs.getInt("idGenero"));
                genero.setNombre(rs.getString("nombre"));
                pelicula.setGenero(genero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pelicula;
    }
    public void actualizarPelicula(Pelicula pelicula) {
        String sql = "UPDATE Pelicula SET titulo = ?, director = ?, anoPublicacion = ?, rating = ?, boxOffice = ? WHERE idPelicula = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pelicula.getTitulo());
            pstmt.setString(2, pelicula.getDirector());
            pstmt.setInt(3, pelicula.getAnoPublicacion());
            pstmt.setDouble(4, pelicula.getRating());
            pstmt.setDouble(5, pelicula.getBoxOffice());
            pstmt.setInt(6, pelicula.getIdPelicula());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}