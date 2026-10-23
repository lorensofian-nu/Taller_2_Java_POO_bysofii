package com.arena.rpg;

public class Arma {
    private String nombre;
    private int danioBase;

    public Arma(String nombre, int danioBase) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Arma Genérica Power Ranger" : nombre.trim();
        this.danioBase = Math.max(0, danioBase);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        }
    }

    public int getDanioBase() {
        return danioBase;
    }

    public void setDanioBase(int danioBase) {
        this.danioBase = Math.max(0, danioBase);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" (Daño Base: +").append(danioBase).append(" Power Ranger)");
        return sb.toString();
    }
}
