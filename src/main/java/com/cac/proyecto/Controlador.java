package com.cac.proyecto;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/peliculas")
public class Controlador extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        try {
            ObjectMapper mapper = new ObjectMapper();
            Pelicula pelicula = mapper.readValue(request.getInputStream(), Pelicula.class);

            String query = "INSERT INTO Peliculas (titulo, genero, duracion, imagen) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getGenero());
            statement.setString(3, pelicula.getDuracion());
            statement.setString(4, pelicula.getImagen());

            // Imprimir consulta SQL para depuración
            System.out.println("Query ejecutada: " + statement.toString());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Long idPeli = rs.getLong(1);

                response.setContentType("application/json");
                String json = mapper.writeValueAsString(idPeli);
                response.getWriter().write(json);
            }

            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            conexion.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        Conexion conexion = new Conexion();
        Connection conn = conexion.getConnection();

        try {
            String query = "SELECT * FROM Peliculas";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<Pelicula> peliculas = new ArrayList<>();

            while (resultSet.next()) {
                Pelicula pelicula = new Pelicula(
                    resultSet.getInt("id_pelicula"),
                    resultSet.getString("titulo"),
                    resultSet.getString("genero"),
                    resultSet.getString("duracion"),
                    resultSet.getString("imagen")
                );
                peliculas.add(pelicula);
            }

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(peliculas);

            // Imprimir JSON resultante para depuración
            System.out.println("JSON devuelto: " + json);

            response.setContentType("application/json");
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            conexion.close();
        }
    }
}
