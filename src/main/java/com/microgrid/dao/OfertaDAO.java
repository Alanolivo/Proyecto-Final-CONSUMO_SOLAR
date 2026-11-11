package com.microgrid.dao;

import com.microgrid.model.OfertaEnergia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfertaDAO {

    public List<OfertaEnergia> listarOfertasAbiertas() {
        List<OfertaEnergia> lista = new ArrayList<>();
        String sql = "SELECT o.*, u.nombre AS prosumidor_nombre FROM ofertas_energia o " +
                     "JOIN usuarios u ON o.prosumidor_id = u.id " +
                     "WHERE o.estado = 'ABIERTA' ORDER BY o.fecha_publicacion DESC";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new OfertaEnergia(
                    rs.getInt("id"),
                    rs.getInt("prosumidor_id"),
                    rs.getString("prosumidor_nombre"),
                    rs.getDouble("kwh_disponible"),
                    rs.getDouble("precio_por_kwh"),
                    rs.getString("tipo_oferta"),
                    rs.getString("estado"),
                    rs.getTimestamp("fecha_publicacion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertarOferta(OfertaEnergia oferta) {
        String sql = "INSERT INTO ofertas_energia (prosumidor_id, kwh_disponible, precio_por_kwh, tipo_oferta, estado) VALUES (?, ?, ?, ?, 'ABIERTA')";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, oferta.getProsumidorId());
            ps.setDouble(2, oferta.getKwhDisponible());
            ps.setDouble(3, oferta.getPrecioPorKwh());
            ps.setString(4, oferta.getTipoOferta());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public OfertaEnergia obtenerPorId(int id, Connection con) throws SQLException {
        String sql = "SELECT o.*, u.nombre AS prosumidor_nombre FROM ofertas_energia o JOIN usuarios u ON o.prosumidor_id = u.id WHERE o.id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new OfertaEnergia(
                        rs.getInt("id"),
                        rs.getInt("prosumidor_id"),
                        rs.getString("prosumidor_nombre"),
                        rs.getDouble("kwh_disponible"),
                        rs.getDouble("precio_por_kwh"),
                        rs.getString("tipo_oferta"),
                        rs.getString("estado"),
                        rs.getTimestamp("fecha_publicacion")
                    );
                }
            }
        }
        return null;
    }
}
