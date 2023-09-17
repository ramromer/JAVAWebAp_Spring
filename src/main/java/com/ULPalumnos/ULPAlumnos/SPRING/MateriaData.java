package com.ULPalumnos.ULPAlumnos.SPRING;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MateriaData {

    private Connection con = null;

    public MateriaData() {
        con = Conexion.getConexion();

    }

    public void guardarMateria(Materia materia) {
        String sql = "INSERT INTO materia (nombre,año,estado)"
                + "VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isActivo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
//                JOptionPane.showMessageDialog(null, "Materia Guardado");
            }
            ps.close();
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }

    }

    public void modificarMateria(Materia materia) {
        String sql = "UPDATE materia SET nombre=?,año=?,"
                + "WHERE idMateria =?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setInt(3, materia.getIdMateria());
            int exito = ps.executeUpdate();
            if (exito == 1) {
//                JOptionPane.showMessageDialog(null, "Materia modificada");

            }
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");

        }
    }

    public void eliminarMateria(int id) {
        String sql = "UPDATE materia SET estado=0 WHERE idMateria=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
//                JOptionPane.showMessageDialog(null, "Materia Eliminado");

            }

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }

    }

    public Materia buscarMateria(int id) {
        String sql = "SELECT nombre,año,estado FROM materia WHERE idMateria =?";
        Materia materia = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setActivo(rs.getBoolean("estado"));
            } else {
//                JOptionPane.showMessageDialog(null, "No existe una Materia con esa ID");
            }
            ps.close();

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }
        return materia;
    }

    public ArrayList<Materia> listarMaterias() {
        String sql = "SELECT idMateria ,nombre,año FROM materia WHERE estado=1";
        ArrayList<Materia> materias = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setActivo(true);
                materias.add(materia);
            }
            ps.close();

        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }
        return materias;
    }

}
