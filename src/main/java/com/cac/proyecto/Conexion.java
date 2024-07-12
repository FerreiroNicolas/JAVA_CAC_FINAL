package com.cac.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection connection;  // Objeto Connection para manejar la conexión a la base de datos

    // Constructor de la clase Conexion
    public Conexion() {
        try {
            // Paso 1: Cargar dinámicamente el driver de MySQL
            Class.forName("org.mariadb.jdbc.Driver");  // Cargar el driver de MariaDB

            // Paso 2: Establecer la conexión con la base de datos 'peliculas_cac_java' en localhost
            this.connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/peliculas_cac_java",  // URL de conexión JDBC para MySQL
                "nicoferreiro",  // Nombre de usuario de la base de datos (cambia según tu configuración)
                "Gatoperro1#"  // Contraseña de la base de datos (cambia según tu configuración)
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // Imprimir el error en caso de no encontrar el driver
        } catch (SQLException e) {
            e.printStackTrace();  // Imprimir el error en caso de problemas con la conexión a la base de datos
        }
    }

    // Método para obtener la conexión GETTER
    public Connection getConnection() {
        return connection;  // Devuelve el objeto Connection establecido
    }

    // Método para cerrar la conexión
    public void close() {
        try {
            // Verificar si la conexión no es nula y está abierta, entonces cerrarla
            if (connection != null && !connection.isClosed()) {
                connection.close();  // Cierra la conexión a la base de datos
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprimir el error en caso de problemas al cerrar la conexión
        }
    }
}



/*

 package com.cac.proyecto;
 
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;
 
 public class Conexion {
     private Connection connection;
 
     public Conexion() {
         try {
             // Cargar el driver de PostgreSQL
             Class.forName("org.postgresql.Driver");
 
             // Establecer la conexión con la base de datos
             this.connection = DriverManager.getConnection(
                 "jdbc:postgresql://localhost:5432/peliculas_cac_java", 
                 "nicoferreiro", 
                 "Gatoperro1#"
             );
             System.out.println("Conexión exitosa a la base de datos");
         } catch (ClassNotFoundException e) {
             System.out.println("Error al cargar el driver de PostgreSQL");
             e.printStackTrace();
         } catch (SQLException e) {
             System.out.println("Error al conectar a la base de datos");
             e.printStackTrace();
         }
     }
 
     public Connection getConnection() {
         return connection;
     }
 
     public void close() {
         try {
             if (connection != null && !connection.isClosed()) {
                 connection.close();
                 System.out.println("Conexión cerrada");
             }
         } catch (SQLException e) {
             System.out.println("Error al cerrar la conexión");
             e.printStackTrace();
         }
     }
 }
*/
