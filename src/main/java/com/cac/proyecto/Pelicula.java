package com.cac.proyecto;

//Clase Película: Representa el modelo de datos.
public class Pelicula {
    // Atributos de la clase Pelicula que representan las columnas de la tabla
    private int idPelicula;  // ID autoincremental de la película (int)
    private String titulo; // Título de la película (varchar)
    private String genero;   // Género de la película (varchar)
    private String duracion; // Duración de la película (varchar)
    private String imagen;   // URL de la imagen de la película (varchar)

    // Constructor vacío necesario para deserialización de JSON
    public Pelicula() {}

    // Constructor con parámetros para inicializar todos los atributos
    public Pelicula(int idPelicula,String titulo, String genero, String duracion, String imagen) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.genero = genero;
        this.duracion = duracion;
        this.imagen = imagen;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
