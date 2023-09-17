package com.ULPalumnos.ULPAlumnos.SPRING;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author emanuel y sus amigos
 */
public class Conexion {
    private static final String PORT= "3307";//System.getProperty("PORT");
    private static String URL = "jdbc:mysql://localhost:";
    private static final String DB="/universidad";
    private static final String USUARIO="root";//System.getProperty("USER");
    private static final String PASSWORD="";//System.getProperty("PASS");;
    private static Connection connection;

    private Conexion(){}
    public static Connection getConexion(){

        if (connection==null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                System.out.println(URL+PORT+DB);
                connection= DriverManager.getConnection(URL+PORT+DB,USUARIO,PASSWORD);
                System.out.println("Conectado");

            } catch (ClassNotFoundException ex) {
                System.out.println("Error al cargar los Driver");
            } catch (SQLException ex) {
                System.out.println("Error al conectar con la base de datos"+ex.getMessage());

            }


        }
        return connection;
    }


}