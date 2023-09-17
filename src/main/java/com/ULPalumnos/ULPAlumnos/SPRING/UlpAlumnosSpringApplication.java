package com.ULPalumnos.ULPAlumnos.SPRING;

import java.sql.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UlpAlumnosSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(UlpAlumnosSpringApplication.class, args);
		Connection con=Conexion.getConexion();
	}

}
