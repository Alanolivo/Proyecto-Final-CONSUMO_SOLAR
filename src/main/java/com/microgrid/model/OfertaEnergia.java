package com.microgrid.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OfertaEnergia implements Serializable {
    private int id;
    private int prosumidorId;
    private String nombreProsumidor;
    private double kwhDisponible;
    private double precioPorKwh;
    private String tipoOferta;
    private String estado;
    private Timestamp fechaPublicacion;

    public OfertaEnergia() {}

    public OfertaEnergia(int id, int prosumidorId, String nombreProsumidor, double kwhDisponible, double precioPorKwh, String tipoOferta, String estado, Timestamp fechaPublicacion) {
        this.id = id;
        this.prosumidorId = prosumidorId;
        this.nombreProsumidor = nombreProsumidor;
        this.kwhDisponible = kwhDisponible;
        this.precioPorKwh = precioPorKwh;
        this.tipoOferta = tipoOferta;
        this.estado = estado;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getProsumidorId() { return prosumidorId; }
    public void setProsumidorId(int prosumidorId) { this.prosumidorId = prosumidorId; }
    public String getNombreProsumidor() { return nombreProsumidor; }
    public void setNombreProsumidor(String nombreProsumidor) { this.nombreProsumidor = nombreProsumidor; }
    public double getKwhDisponible() { return kwhDisponible; }
    public void setKwhDisponible(double kwhDisponible) { this.kwhDisponible = kwhDisponible; }
    public double getPrecioPorKwh() { return precioPorKwh; }
    public void setPrecioPorKwh(double precioPorKwh) { this.precioPorKwh = precioPorKwh; }
    public String getTipoOferta() { return tipoOferta; }
    public void setTipoOferta(String tipoOferta) { this.tipoOferta = tipoOferta; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Timestamp getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(Timestamp fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
}
