package com.arena.rpg;

/**
 * Clase que representa un Arma dentro del sistema Arena RPG.
 * Participa en una Asociación Unidireccional (0..1) con la clase Personaje:
 * El Personaje conoce su arma equipada, pero el Arma desconoce a su portador.
 */
public class Arma {
    private String nombre;
    private int danioBase;

    public Arma(String nombre, int danioBase) {
        this.nombre = (nombre == null || nombre.trim().isEmpty()) ? "Arma Genérica" : nombre.trim();
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
        sb.append(nombre).append(" (Daño Base: +").append(danioBase).append(")");
        return sb.toString();
    }
}
