package com.cac.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection connection;  
    public Conexion() {
        try {
            
            Class.forName("org.mariadb.jdbc.Driver");  

            
            this.connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3308/peliculas_java",  // URL de conexión JDBC para MySQL
                "nicoferreiro",  
                "Gatoperro1#"  
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); 
        } catch (SQLException e) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();  
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
