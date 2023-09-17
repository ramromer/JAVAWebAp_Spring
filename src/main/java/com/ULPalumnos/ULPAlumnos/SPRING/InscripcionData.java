/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ULPalumnos.ULPAlumnos.SPRING;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InscripcionData {

    private Connection con;
    private AlumnoData aluData;
    private MateriaData matData;

    public InscripcionData() {
        this.con = Conexion.getConexion();
        this.aluData = new AlumnoData();
        this.matData = new MateriaData();
    }

    public void guardarInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripcion (nota,idAlumno,idMateria)"
                + "VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setDouble(1, inscripcion.getNota());
            ps.setInt(2, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(2, inscripcion.getMateria().getIdMateria());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                inscripcion.setIdInscripcion(rs.getInt(1));
////                JOptionPane.showMessageDialog(null, "Incripcion Guardada");
            }
            ps.close();
        } catch (SQLException ex) {
////            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }

    }

    public List<Inscripcion> obtenerIncripciones() {
        String sql = "SELECT * FROM inscripcion";
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        try {
//            JOptionPane.showMessageDialog(null, "Conectando");

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getDouble("nota"));
                Alumno alu = aluData.buscarAlumno(rs.getInt("idAlumno"));
                inscripcion.setAlumno(alu);
                Materia mat = matData.buscarMateria(rs.getInt("idMateria"));
                inscripcion.setMateria(mat);

                inscripciones.add(inscripcion);

            }
            ps.close();

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion OI");
        }
        return inscripciones;
    }

    public List<Materia> obtenerMateriasCursadas(int id) {
        List<Materia> materias = new ArrayList<Materia>();

        try {
            String sql = "SELECT inscripcion.idMateria, nombre, año FROM inscripcion,"
                    + " materia WHERE inscripcion.idMateria = materia.idMateria\n"
                    + "AND inscripcion.idAlumno = ?;";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            Materia materia;

            while (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materias.add(materia);

            }
            ps.close();
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al obtener Inscripciones." + ex.getMessage());

        }

        return materias;
    }

    public List<Materia> obtenerMateriasNoCursadas(int id) {
        List<Materia> materias = new ArrayList<Materia>();

        try {
            String sql = "SELECT inscripcion.idMateria, nombre, año FROM inscripcion, materia\n"
                    + "WHERE inscripcion.idMateria = materia.idMateria\n"
                    + "AND inscripcion.idAlumno = 4\n"
                    + "AND materia.idMateria NOT IN (SELECT idMateria FROM inscripcion WHERE idAlumno = 4);";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            Materia materia;

            while (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materias.add(materia);

            }
            ps.close();
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al obtener Inscripciones." + ex.getMessage());

        }

        return materias;
    }

    public List<Inscripcion> obtenerIncripcionesPorAlumno(int id) {
        String sql = "SELECT * FROM inscripcion WHERE idAlumno = ?";

        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getDouble("nota"));
                Alumno alu = aluData.buscarAlumno(rs.getInt("idAlumno"));
                inscripcion.setAlumno(alu);
                Materia mat = matData.buscarMateria(rs.getInt("idMateria"));
                inscripcion.setMateria(mat);

                inscripciones.add(inscripcion);

            }
            ps.close();

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion OI");
        }
        return inscripciones;
    }

    public void borrarInscripcionMateriaAlumno(int idA, int idM) {

        String sql = "DELETE FROM inscripcion WHERE idAlumno=? and idMateria=?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idA);
            ps.setInt(2, idM);
            int rs = ps.executeUpdate();

            if (rs > 0) {
//                JOptionPane.showMessageDialog(null, "La incripcion fue eliminada");
            } else {
//                JOptionPane.showMessageDialog(null, "No existe la incripcion");
            }
            ps.close();
        } catch (SQLException ex) {
        }
//        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");

    }

    public void ActualizarNota(int idA, int idM, double nota) {
        String sql = "UPDATE inscripcion SET nota=? WHERE idAlumno=? and idMateria=?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idA);
            ps.setInt(3, idM);
            int rs = ps.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Se actualizo la nota");

            ps.close();

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al actualizar nota o conectar con la base de datos");

        }
    }
  public List<Alumno> obtenerAlumnosPorMateria(int idMateria) {
    List<Alumno> alumnos = new ArrayList<>();

    try {
        String sql = "SELECT alumno.idAlumno, dni, apellido, nombre, fechaNacimiento, estado FROM alumno, inscripcion "
                + "WHERE alumno.idAlumno = inscripcion.idAlumno "
                + "AND inscripcion.idMateria = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idMateria);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Alumno alumno = new Alumno();
            alumno.setIdAlumno(rs.getInt("idAlumno"));
            alumno.setDni(rs.getInt("dni"));
            alumno.setApellido(rs.getString("apellido"));
            alumno.setNombre(rs.getString("nombre"));
            alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
            alumno.setActivo(rs.getBoolean("estado"));

            alumnos.add(alumno);
        }

        ps.close();
    } catch (SQLException ex) {
//        JOptionPane.showMessageDialog(null, "Error al obtener alumnos por materia: " + ex.getMessage());
    }

    return alumnos;
}
}
